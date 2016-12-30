// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) 2013-2016 Steven Gawroriski <steven@multiphasicapps.net>
//     Copyright (C) 2013-2016 Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// For more information see license.mkd.
// ---------------------------------------------------------------------------

package net.multiphasicapps.squirreljme.exe.elf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import net.multiphasicapps.io.data.ExtendedDataOutputStream;
import net.multiphasicapps.squirreljme.nativecode.base.NativeEndianess;
import net.multiphasicapps.squirreljme.jit.base.JITException;
import net.multiphasicapps.util.datadeque.ByteDeque;

/**
 * This contains the sequences which are used to output the specified ELF.
 *
 * @since 2016/08/16
 */
class __Sequences__
	implements Iterable<__Sequences__.__Sequence__>
{
	/** The owning ELF. */
	protected final ELFOutput owner;

	/** The sequence list. */
	protected final List<__Sequence__> seq =
		new ArrayList<>();
	
	/** The elf header. */
	protected final __Header__ header;
	
	/** The program headers. */
	protected final __Programs__ programs;
	
	/** The section headers. */
	protected final __Sections__ sections;
	
	/**
	 * Makes the write sequence list.
	 *
	 * @param __eo The output ELF to sequence.
	 * @throws NullPointerException On null arguments.
	 * @since 2016/08/17
	 */
	__Sequences__(ELFOutput __eo)
		throws NullPointerException
	{
		// Check
		if (__eo == null)
			throw new NullPointerException("NARG");
		
		// Set
		this.owner = __eo;
		
		// The target list
		List<__Sequence__> rv = this.seq;
	
		// Identification
		rv.add(new __Identification__());
		
		// The header
		__Header__ h = new __Header__();
		this.header = h;
		rv.add(h);
		
		// Initialize program headers (and any real code areas)
		__Programs__ programs = new __Programs__();
		this.programs = programs;
		
		// Initialize sections
		__Sections__ sections = new __Sections__();
		this.sections = sections;
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2016/08/16
	 */
	@Override
	public Iterator<__Sequences__.__Sequence__> iterator()
	{
		return this.seq.iterator();
	}
	
	/**
	 * This represents a single sequence which is used to determine how an ELF
	 * is to be written.
	 *
	 * @since 2016/08/16
	 */
	abstract class __Sequence__
	{
		/** The position where this sequence is located. */
		volatile int _at =
			-1;
	
		/** The size of the sequence. */
		volatile int _size =
			-1;
	
		/**
		 * Internal only.
		 *
		 * @since 2016/08/16
		 */
		private __Sequence__()
		{
		}
		
		/**
		 * Writes the given sequence.
		 *
		 * @param __dos The sequence to write.
		 * @throws IOException On write errors.
		 * @since 2016/08/16
		 */
		abstract void __write(ExtendedDataOutputStream __dos)
			throws IOException;
	}
	
	/**
	 * The header contains more details about the ELF.
	 *
	 * @since 2016/08/16
	 */
	final class __Header__
		extends __Sequence__
	{
		/** The position of the program header. */
		volatile int _pheadoff;
		
		/** The position of the section header. */
		
		/**
		 * Initializes the header.
		 *
		 * @since 2016/08/16
		 */
		private __Header__()
		{
			// Starts after the identification
			this._at = 16;
			
			// The size depends on the bit count
			switch (__Sequences__.this.owner._wordsize)
			{
					// 32-bit
				case 32:
					this._size = 36;
					break;
			
					// 64-bit
				case 64:
					this._size = 48;
					break;
			
					// Default
				default:
					throw new RuntimeException("OOPS");
			}
		}
		
		/**
		 * {@inheritDoc}
		 * @since 2016/08/16
		 */
		@Override
		void __write(ExtendedDataOutputStream __dos)
			throws IOException
		{
			// Get
			ELFOutput eo = __Sequences__.this.owner;
			int bits = eo._wordsize;
			__Programs__ programs = __Sequences__.this.programs;
			__Sections__ sections = __Sequences__.this.sections;
			
			// Write the elf type
			__dos.writeShort(eo._type.identifier());
			
			// And the machine
			__dos.writeShort(eo._machine);
			
			// Only version 1 of ELF is written
			__dos.writeInt(1);
			
			// Need to find the entry point of the program which is somewhere
			boolean foundep = false;
			ELFProgram gotfl = null;
			__Program__ gotflp = null;
			long entrypoint = 0;
			for (ELFProgram p : eo._programs)
			{
				// First load, use that in case
				if (gotfl == null && (p._type.identifier() ==
					ELFStandardProgramType.LOAD.identifier()))
					for (__Sequence__ q : __Sequences__.this.seq)
						if (q instanceof __Program__ &&
							((__Program__)q)._program == p)
						{
							gotfl = p;
							gotflp = (__Program__)q;
							break;
						}
				
				// Use the given entry point
				if (p._useentrypoint)
				{
					foundep = true;
					entrypoint = eo._baseaddr + p._entrypoint;
					break;
				}
			}
			
			// If not found, then just use the first loaded segment and if
			// that does not exist then use the base address
			if (!foundep)
				entrypoint = (gotfl == null ? eo._baseaddr :
					(gotfl._useloadaddr ? gotfl._loadaddr :
					eo._baseaddr + gotflp._at));
			
			// Write the entry point, program header offset, and program header
			// size
			int phoff = programs._at, shoff = sections._at;
			int numsections = sections._entcount;
			switch (bits)
			{
					// 32-bit
				case 32:
					__dos.writeInt((int)entrypoint);
					__dos.writeInt(phoff);
					__dos.writeInt((numsections == 0 ? 0 : shoff));
					break;
					
					// 64-bit
				case 64:
					__dos.writeLong(entrypoint);
					__dos.writeLong(phoff & 0xFFFF_FFFFL);
					__dos.writeLong((numsections == 0 ? 0 :
						(shoff & 0xFFFF_FFFFL)));
					break;
				
					// Unknown
				default:
					throw new RuntimeException("OOPS");
			}
			
			// Write flags
			__dos.writeInt(eo._flags);
			
			// Write the size of this header
			// Also include the identification area, otherwise the ELF becomes
			// invalid
			__dos.writeShort(16 + this._size);
			
			// Write program header size and count
			__dos.writeShort(programs._entsize);
			__dos.writeShort(programs._entcount);
			
			// And for the section headers also
			__dos.writeShort(sections._entsize);
			__dos.writeShort(numsections);
			
			// The section which contains the strings
			__dos.writeShort(sections._stringsect);
		}
	}
	
	/**
	 * The ELF identification header.
	 *
	 * @since 2016/08/16
	 */
	final class __Identification__
		extends __Sequence__
	{
		/**
		 * Internal only.
		 *
		 * @since 2016/08/16
		 */
		private __Identification__()
		{
			// Always at the start and the same size
			this._at = 0;
			this._size = 16;
		}
		
		/**
		 * {@inheritDoc}
		 * @since 2016/08/16
		 */
		@Override
		void __write(ExtendedDataOutputStream __dos)
			throws IOException
		{
			// Get the ELF
			ELFOutput eo = __Sequences__.this.owner;
			int bits = eo._wordsize;
			
			// Magic number
			__dos.writeByte(0x7F);
			__dos.writeByte('E');
			__dos.writeByte('L');
			__dos.writeByte('F');
			
			// Write the ELF class
			switch (bits)
			{
					// 32-bit
				case 32:
					__dos.writeByte(1);
					break;
					
					// 64-bit
				case 64:
					__dos.writeByte(1);
					break;
				
					// Unknown
				default:
					throw new RuntimeException("NARG");
			}
			
			// Write the endianess
			NativeEndianess end = owner._endianess;
			switch (end)
			{
					// Big
				case BIG:
					__dos.writeByte(2);
					break;
					
					// Little
				case LITTLE:
					__dos.writeByte(1);
					break;
				
					// Unknown
				default:
					throw new RuntimeException("NARG");
			}
			
			// Always version 1
			__dos.writeByte(1);
			
			// Write the OS ABI
			__dos.writeByte(eo._osabi);
			
			// Write the OS ABI specific part (padding)
			__dos.write(eo._padding, 0, 8);
		}
	}
	
	/**
	 * This is single program within the program header.
	 *
	 * @since 2016/08/16
	 */
	final class __Program__
		extends __Sequence__
	{
		/** The wrapped program. */
		final ELFProgram _program;
		
		/**
		 * Initializes the program.
		 *
		 * @param __addr The address the program should be at.
		 * @param __last The program before this one.
		 * @param __prg The program to represent.
		 * @param __i The program index.
		 * @throws NullPointerException On null arguments, except for
		 * {@code __last}.
		 * @since 2016/08/16
		 */
		private __Program__(int __addr, __Program__ __last, ELFProgram __prg,
			int __i)
			throws NullPointerException
		{
			// Check
			if (__prg == null)
				throw new NullPointerException("NARG");
			
			// Set
			this._program = __prg;
			
			// Add self to the end of the program list
			List<__Sequences__.__Sequence__> seqs = __Sequences__.this.seq;
			seqs.add(this);
			
			// Make sure address is aligned to the given alignment
			int align = (int)__prg._align;
			
			// Set address and size
			int amask = align - 1;
			this._at = ((__addr + amask) & (~amask));
			this._size = __prg._length;
		}
		
		/**
		 * {@inheritDoc}
		 * @since 2016/08/16
		 */
		@Override
		void __write(ExtendedDataOutputStream __dos)
			throws IOException
		{
			// Write it here
			ELFProgram program = this._program;
			
			// Either uses an array or a deque
			ByteDeque dq = program._dataq;
			if (dq != null)
				dq.writeTo(__dos);
			
			// Use array
			else
				__dos.write(program._dataa);
		}
	}
	
	/**
	 * This represents the program headers in the ELF.
	 *
	 * @since 2016/08/16
	 */
	final class __Programs__
		extends __Sequence__
	{
		/** The entry size. */
		final int _entsize;
		
		/** Subprograms. */
		final List<__Program__> _subprogs =
			new ArrayList<>();
		
		/** The entry count. */
		volatile int _entcount =
			-1;
		
		/**
		 * Initializes the program headers.
		 *
		 * @since 2016/08/16
		 */
		private __Programs__()
		{
			// The size depends on the bit count
			ELFOutput owner = __Sequences__.this.owner;
			int entsize;
			switch (owner._wordsize)
			{
					// 32-bit
				case 32:
					this._entsize = (entsize = 32);
					break;
			
					// 64-bit
				case 64:
					this._entsize = (entsize = 56);
					break;
			
					// Default
				default:
					throw new RuntimeException("OOPS");
			}
			
			// Add to sequence list since it has to be placed before the about
			// to follow code areas
			List<__Sequences__.__Sequence__> seqs = __Sequences__.this.seq;
			__Sequence__ justbefore = seqs.get(seqs.size() - 1);
			seqs.add(this);
			
			// Get ELF programs to generate headers for
			List<ELFProgram> programs = owner._programs;
			int n = programs.size();
			this._entcount = n;
			
			// Start program headers right where the older data ends
			int base = justbefore._at + justbefore._size;
			this._at = base;
			
			// Size is every entry
			this._size = entsize * n;
			
			// Add locations of each area
			// Start directly following the program header table
			int now = base + (entsize * n);
			__Program__ last = null;
			List<__Program__> subprogs = this._subprogs;
			for (int i = 0; i < n; i++)
			{
				// Get program here
				ELFProgram prg = programs.get(i);
				
				// Create new target program
				__Program__ p = new __Program__(now, last, prg, i);
				last = p;
				subprogs.add(p);
				
				// Set position of the program and make the next program
				// follow this one
				now = ((p._at + p._size + 3) & (~3));
			}
		}
		
		/**
		 * {@inheritDoc}
		 * @since 2016/08/16
		 */
		@Override
		void __write(ExtendedDataOutputStream __dos)
			throws IOException
		{
			// Need the word size since both things differ
			ELFOutput owner = __Sequences__.this.owner;
			int bits = owner._wordsize;
			long base = owner._baseaddr;
			
			// Write the header information
			int entsize;
			List<__Program__> subprogs = this._subprogs;
			int n = subprogs.size();
			for (int i = 0; i < n; i++)
			{
				// Storage
				ELFProgramType type;
				long offset, loadaddr, physaddr, filesize, memsize, align;
				int flags;
				
				// Get
				__Program__ sub = subprogs.get(i);
				ELFProgram elf = sub._program;
				
				// Details
				type = elf._type;
				offset = (sub._at & 0xFFFFFFFFL);
				
				// Physical address, which is not always needed
				physaddr = elf._physaddr;
				
				// Use custom specified load address?
				if (elf._useloadaddr)
					loadaddr = elf._loadaddr;
				
				// Relative from base otherwise
				else
					loadaddr = base + offset;
				
				// Use the program size
				filesize = elf._length;
				
				// Extra bytes of memory to potentially use
				memsize = filesize + elf._extramem;
				
				// Alignment
				align = elf._align;
				
				// Flags
				flags = 0;
				for (ELFProgramFlag x : elf._flags)
					flags |= x.mask();
				
				// Same every time
				__dos.writeInt(type.identifier());
				
				// Depends on the word size
				switch (bits)
				{
						// 32-bit
					case 32:
						// {@squirreljme.error AX0d The amount of memory to
						// allocate exceeds 32-bits.}
						if (memsize < 0 || memsize > 0xFFFF_FFFFL)
							throw new JITException("AX0d");
						
						__dos.writeInt((int)offset);
						__dos.writeInt((int)loadaddr);
						__dos.writeInt((int)physaddr);
						__dos.writeInt((int)filesize);
						__dos.writeInt((int)memsize);
						__dos.writeInt(flags);
						__dos.writeInt((int)align);
						break;
						
						// 64-bit
					case 64:
						__dos.writeInt(flags);
						__dos.writeLong(offset);
						__dos.writeLong(loadaddr);
						__dos.writeLong(physaddr);
						__dos.writeLong(filesize);
						__dos.writeLong(memsize);
						__dos.writeLong(align);
						break;
					
						// Unknown
					default:
						throw new RuntimeException("OOPS");
				}
			}
		}
	}
	
	/**
	 * This represents the sections in the ELF.
	 *
	 * @since 2016/08/16
	 */
	final class __Sections__
		extends __Sequence__
	{
		/** The entry size. */
		final int _entsize;
		
		/** The entry count. */
		volatile int _entcount =
			-1;
		
		/** The index containing the string table. */
		volatile int _stringsect =
			-1;
		
		/**
		 * Initializes the sections.
		 *
		 * @since 2016/08/16
		 */
		private __Sections__()
		{
			// The size depends on the bit count
			int entsize;
			switch (__Sequences__.this.owner._wordsize)
			{
					// 32-bit
				case 32:
					this._entsize = (entsize = 40);
					break;
			
					// 64-bit
				case 64:
					this._entsize = (entsize = 64);
					break;
			
					// Default
				default:
					throw new RuntimeException("OOPS");
			}
			
			// Add self
			List<__Sequences__.__Sequence__> seqs = __Sequences__.this.seq;
			__Sequence__ justbefore = seqs.get(seqs.size() - 1);
			seqs.add(this);
			
			// Start right after the last program area
			this._at = (justbefore._at + justbefore._size + 7) & (~7);
			
			// No sections
			System.err.println("TODO -- Add support for sections.");
			int n = 1;
			this._size = entsize * n;
			this._entcount = n;
			this._stringsect = 0;
		}
		
		/**
		 * {@inheritDoc}
		 * @since 2016/08/16
		 */
		@Override
		void __write(ExtendedDataOutputStream __dos)
			throws IOException
		{
			// Write all sections
			int n = this._entcount;
			for (int i = 0; i < n; i++)
			{
				// Write null entry
				if (i == 0)
				{
					int q = this._entsize;
					for (int j = 0; j < q; j++)
						__dos.writeByte(0);
					continue;
				}
				
				throw new Error("TODO");
			}
		}
	}
}
