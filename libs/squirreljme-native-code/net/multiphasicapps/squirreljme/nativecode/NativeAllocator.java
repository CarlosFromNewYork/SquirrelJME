// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) 2013-2016 Steven Gawroriski <steven@multiphasicapps.net>
//     Copyright (C) 2013-2016 Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// For more information see license.mkd.
// ---------------------------------------------------------------------------

package net.multiphasicapps.squirreljme.nativecode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import net.multiphasicapps.util.msd.MultiSetDeque;

/**
 * This is a class which is used to allocate and manage native registers
 * which are available for a given CPU.
 *
 * @since 2016/08/30
 */
public class NativeAllocator
{
	/** The ABI used. */
	protected final NativeABI abi;
	
	/** The multi-set deque, used for removal. */
	final MultiSetDeque<NativeRegister> _msd;
	
	/** Saved int register queue. */
	final Deque<NativeRegister> _savedintq;
	
	/** Saved float register queue. */
	final Deque<NativeRegister> _savedfloatq;
	
	/** Temporary int register queue. */
	final Deque<NativeRegister> _tempintq;
	
	/** Temporary float register queue. */
	final Deque<NativeRegister> _tempfloatq;
	
	/** The current allocations that are being used. */
	final Set<NativeAllocation> _allocs =
		new LinkedHashSet<>();
	
	/** The current size of the stack. */
	volatile int _stacksize;
	
	/**
	 * Initializes the register allocator using the specified ABI.
	 *
	 * @param __abi The ABI used on the target system.
	 * @throws NullPointerException On null arguments.
	 * @since 2016/09/01
	 */
	public NativeAllocator(NativeABI __abi)
		throws NullPointerException
	{
		// Check
		if (__abi == null)
			throw new NullPointerException("NARG");
		
		// Set
		this.abi = __abi;
		
		// Setup queues
		MultiSetDeque<NativeRegister> msd = new MultiSetDeque<>();
		Deque<NativeRegister> savedintq = msd.subDeque();
		Deque<NativeRegister> savedfloatq = msd.subDeque();
		Deque<NativeRegister> tempintq = msd.subDeque();
		Deque<NativeRegister> tempfloatq = msd.subDeque();
		this._msd = msd;
		this._savedintq = savedintq;
		this._savedfloatq = savedfloatq;
		this._tempintq = tempintq;
		this._tempfloatq = tempfloatq;
		
		// Add all registers to the queues
		savedintq.addAll(__abi.saved(NativeRegisterKind.INTEGER));
		savedfloatq.addAll(__abi.saved(NativeRegisterKind.FLOAT));
		tempintq.addAll(__abi.temporary(NativeRegisterKind.INTEGER));
		tempfloatq.addAll(__abi.temporary(NativeRegisterKind.FLOAT));
	}
	
	/**
	 * Returns the ABI that is used for this.
	 *
	 * @return The used ABI.
	 * @since 2016/09/03
	 */
	public final NativeABI abi()
	{
		return this.abi;
	}
	
	/**
	 * Attempts to allocate the given type using a specified set of registers.
	 *
	 * @param __pref The type of preference when allocating registers.
	 * @param __at The type of allocation to perform.
	 * @param __rt The register to allocate.
	 * @param __pure Perform a pure allocation, that is if registers are used
	 * then all of them must fit into registers, otherwise it would be placed
	 * on the stack.
	 * @throws NativeAllocationMustSpillException If allocating only registers
	 * and there are not enough registers available to store a value of the
	 * given type.
	 * @throws NullPointerException On null arguments, except for
	 * {@code __pref} if {@code __at} does not use any registers.
	 * @since 2016/09/18
	 */
	public final NativeAllocation allocate(NativeAllocationPreference __pref,
		NativeAllocationType __at, NativeRegisterType __rt, boolean __pure)
		throws NativeAllocationMustSpillException, NullPointerException
	{
		// Check
		if (__at == null || __rt == null ||
			(__at.claimRegisters() && __pref == null))
			throw new NullPointerException("NARG");
		
		// If requesting a pure allocation, only when the allocation type is
		// BOTH will there be a potential value splicing.
		if (__pure && __at == NativeAllocationType.BOTH)
		{
			// Try allocating in registers
			try
			{
				return allocate(__pref, NativeAllocationType.REGISTER, __rt,
					true);
			}
			
			// Could not fit, spill to stack
			catch (NativeAllocationMustSpillException e)
			{
				return allocate(__pref, NativeAllocationType.STACK, __rt,
					true);
			}
		}
		
		// Performing some allocation types?
		boolean usereg = __at.claimRegisters();
		boolean usestack = __at.claimStack();
		
		// If prefering any allocation type, then a recursive call can be made
		// to this method.
		if (usereg && __pref == NativeAllocationPreference.ANY)
		{
			// Try temporary registers first
			try
			{
				return allocate(NativeAllocationPreference.ONLY_TEMPORARY,
					__at, __rt, __pure);
			}
			
			// If that fails, go for saved ones
			catch (NativeAllocationMustSpillException e)
			{
				return allocate(NativeAllocationPreference.ONLY_SAVED,
					__at, __rt, __pure);
			}
		}
		
		// ABI needed for register sizes
		NativeABI abi = this.abi;
		
		// Allocating from registers?
		Set<NativeRegister> regclaim = new LinkedHashSet<>();
		int bytesleft = __rt.bytes();
		Deque<NativeRegister> queue = null;
		if (usereg)
		{
			// Get queues to source registers from
			boolean wantsaved = (__pref == NativeAllocationPreference.
				ONLY_SAVED);
			if (__rt.isFloat())
				queue = (wantsaved ? this._savedfloatq : this._tempfloatq);
			else
				queue = (wantsaved ? this._savedintq : this._tempfloatq);
			
			// Go through the register queue and attempt to claim the
			// registers
			for (NativeRegister r : queue)
			{
				NativeRegisterType t = abi.registerType(r);
				
				// Consider this register
				regclaim.add(r);
				bytesleft -= t.bytes();
				
				// Claimed all of the required registers?
				if (bytesleft <= 0)
					break;
			}
		}
		
		// Needed to determine the stack location
		Set<NativeAllocation> allocs = this._allocs;
		
		// Need the stack layout to find out how to place items
		NativeStackFrameLayout nsl = abi.stackLayout();
	
		// Bytes remaining for allocation?
		int stackpos = -1, stacklen = 0;
		if (bytesleft > 0)
		{
			// Allocating from the stack?
			if (usestack)
			{
				// Determine the highest stack position that is available
				int hipos = stackSize();
				
				// Align values stored on the stack to a specific alignment
				// so CPUs can read them easier
				int svalign = nsl.stackValueAlignment();
				int axp;
				if (svalign != 1 && (axp = (hipos % svalign)) != 0)
					hipos = hipos + (svalign - axp);
				
				// Use that position
				stackpos = hipos;
				stacklen = bytesleft;
			}
			
			// {@squirreljme.error AR06 Could not store a value of the
			// specified type in registers because there is not enough free
			// register space. (The type to allocate; The number of bytes
			// needed for allocation)}
			else
				throw new NativeAllocationMustSpillException(
					String.format("AR06 %s %d", __rt, bytesleft));
		}
		
		// Setup allocation
		NativeAllocation rv = new NativeAllocation(stackpos, stacklen, __rt,
			regclaim.<NativeRegister>toArray(
			new NativeRegister[regclaim.size()]));
		
		// The registers were grabbed from the queue so remove them
		for (NativeRegister r : regclaim)
			queue.remove(r);
		
		// Remember it
		allocs.add(rv);
		
		// Return it
		return rv;
	}
	
	/**
	 * Goes through the input arguments and creates allocations for all of
	 * the input allocation values based on their types. Allocations may
	 * optionally be stored.
	 *
	 * @param <X> The type of special value used for input.
	 * @param __store If {@code true} then allocations are stored in this
	 * allocator, otherwise {@code false} will return the allocations which
	 * would have been used without modifying the state.
	 * @param __t The types of value to store.
	 * @return The allocations for all input arguments, this array will be of
	 * the same size as the input.
	 * @throws NullPointerException On null arguments.
	 * @since 2016/09/25
	 */
	public final <X> NativeArgumentOutput<X>[] argumentAllocate(
		boolean __store, NativeArgumentInput<X>[] __t)
		throws NullPointerException
	{
		// Check
		if (__t == null)
			throw new NullPointerException("NARG");
		
		// Ignore if empty
		int n;
		if ((n = __t.length) <= 0)
			return NativeArgumentOutput.<X>allocateArray(0);
		
		// Allocations will always match the input argument size
		NativeArgumentOutput<X>[] rv =
			NativeArgumentOutput.<X>allocateArray(n);
		
		// Use only argument registers
		NativeABI abi = this.abi;
		MultiSetDeque<NativeRegister> msd = new MultiSetDeque<>();
		Deque<NativeRegister> ain = msd.subDeque(abi.arguments(
			NativeRegisterKind.INTEGER));
		Deque<NativeRegister> afl = msd.subDeque(abi.arguments(
			NativeRegisterKind.FLOAT));
		
		// Registers that consist of temporary argument sets, since on 32-bit
		// systems for example a 64-bit input will take twice the amount of
		// registers. Also considering on 8-bit systems a 32-bit value will
		// consume 4 registers.
		List<NativeRegister> rawr = new ArrayList<>();
		
		// Current stack position
		int stackbase = 0;
		
		// Allocations that exist
		Set<NativeAllocation> allocs = (__store ? this._allocs : null);
		
		// Go through all arguments
		for (int i = 0; i < n; i++)
		{
			NativeArgumentInput<X> nai = __t[i];
			NativeRegisterType t = nai.type();
			
			// Clear the fill list
			rawr.clear();
			
			// Number of bytes to store fore
			int bytesleft = t.bytes();
			
			// Need to allocate all bytes which make up the value
			while (bytesleft > 0)
			{
				// Try to grab a register
				boolean isfloat = t.isFloat();
				NativeRegister maybe = (isfloat ? afl.pollFirst() :
					ain.pollFirst());
				
				// No registers left to claim
				if (maybe == null)
					break;
				
				// Need to get the type of value for the number of bytes it
				// uses
				NativeRegisterType mt = (isfloat ? abi.floatType(maybe) :
					abi.intType(maybe));
				int used = mt.bytes();
				
				// Add register to be used.
				rawr.add(maybe);
				
				// The register has been allocated for arguments so remove
				// from any saved/temporary queues
				__removeRegister(maybe);
				
				// Used that many bytes
				bytesleft -= used;
			}
			
			// No registers available, push to the stack
			int stackpos, stacklen;
			if (bytesleft > 0)
			{
				// Consume all remaining bytes
				stackpos = stackbase;
				stacklen = bytesleft;
				
				// Increase the base to store more values
				stackbase += stacklen;
			}
			
			// Not stored on the stack
			else
			{
				stackpos = -1;
				stacklen = 0;
			}
			
			// Create and store allocation in the resultant value
			NativeAllocation na = new NativeAllocation(stackpos, stacklen, t,
				rawr.<NativeRegister>toArray(new NativeRegister[rawr.size()]));
			rv[i] = nai.output(na);
			
			// Remember this allocation, but only if it is requested
			if (__store)
				allocs.add(na);
		}
		
		// Return all of the allocations
		return rv;
	}
	
	/**
	 * Records the current state of the allocator.
	 *
	 * @return The allocator state.
	 * @since 2016/09/03
	 */
	public final NativeAllocatorState recordState()
	{
		return new NativeAllocatorState(this);
	}
	
	/**
	 * Returns the current stack length.
	 *
	 * @return The stack length.
	 * @since 2016/09/25
	 */
	public final int stackSize()
	{
		// Calculate highest stack position
		int hipos = 0;
		for (NativeAllocation a : this._allocs)
		{
			// Ignore unused stack positions
			int ap = a.stackPosition();
			if (ap < 0)
				continue;
			
			// Determine the end position
			int al = a.stackLength();
			int at = ap + al;
			
			// Use the higher position
			if (at > hipos)
				hipos = at;
		}
		
		// Return it
		return hipos;
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2016/09/03
	 */
	@Override
	public final String toString()
	{
		return recordState().toString();
	}
	
	/**
	 * Removes the specified register from all of the queues.
	 *
	 * @param __r The register to remove.
	 * @throws NullPointerException On null arguments.
	 * @since 2016/09/15
	 */
	public void __removeRegister(NativeRegister __r)
		throws NullPointerException
	{
		// Check
		if (__r == null)
			throw new NullPointerException("NARG");
		
		// Remove it
		this._msd.remove(__r);
	}
}
