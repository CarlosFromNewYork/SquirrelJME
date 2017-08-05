// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) Stephanie Gawroriski <xer@multiphasicapps.net>
//     Copyright (C) Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// See license.mkd for licensing and copyright information.
// ---------------------------------------------------------------------------

package net.multiphasicapps.squirreljme.jit.java;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import net.multiphasicapps.squirreljme.jit.JITException;

/**
 * This represents a single instruction within the byte code.
 *
 * @since 2017/05/18
 */
public final class Instruction
{
	/** The instruction address. */
	protected final int address;
	
	/** The instruction operation. */
	protected final int op;
	
	/** Does this method naturally flow? */
	protected final boolean naturalflow;
	
	/** Instruction arguments. */
	private final Object[] _args;
	
	/** Jump targets for this instruction. */
	private final JumpTarget[] _jumptargets;
	
	/** String representation of the operation. */
	private volatile Reference<String> _string;
	
	/**
	 * Initializes the instruction information.
	 *
	 * @param __code The instruction bytes.
	 * @param __pool The constant pool.
	 * @param __a The instruction address.
	 * @param __eh Exception handler table.
	 * @throws NullPointerException On null arguments.
	 * @since 2017/05/18
	 */
	Instruction(byte[] __code, Pool __pool, int __a,
		ExceptionHandlerTable __eh)
		throws NullPointerException
	{
		// Check
		if (__code == null || __pool == null)
			throw new NullPointerException("NARG");
		
		// Read operation here
		int op = (__code[__a] & 0xFF),
			argbase = __a + 1;
		if (op == InstructionIndex.WIDE)
		{
			op = (op << 8) | (__code[__a + 1] & 0xFF);
			argbase++;
		}
		this.op = op;
		this.address = __a;
		
		// Depends on the operation
		Object[] args;
		boolean naturalflow;
		switch (op)
		{
				// No arguments and does not flow naturally
			case InstructionIndex.ATHROW:
			case InstructionIndex.ARETURN:
			case InstructionIndex.DRETURN:
			case InstructionIndex.FRETURN:
			case InstructionIndex.IRETURN:
			case InstructionIndex.LRETURN:
			case InstructionIndex.RETURN:
				args = new Object[0];
				naturalflow = false;
				break;
			
				// Operands with no arguments, natural flow
			case InstructionIndex.AALOAD:
			case InstructionIndex.AASTORE:
			case InstructionIndex.ACONST_NULL:
			case InstructionIndex.ALOAD_0:
			case InstructionIndex.ALOAD_1:
			case InstructionIndex.ALOAD_2:
			case InstructionIndex.ALOAD_3:
			case InstructionIndex.ARRAYLENGTH:
			case InstructionIndex.ASTORE_0:
			case InstructionIndex.ASTORE_1:
			case InstructionIndex.ASTORE_2:
			case InstructionIndex.ASTORE_3:
			case InstructionIndex.BALOAD:
			case InstructionIndex.BASTORE:
			case InstructionIndex.CALOAD:
			case InstructionIndex.CASTORE:
			case InstructionIndex.D2F:
			case InstructionIndex.D2I:
			case InstructionIndex.D2L:
			case InstructionIndex.DADD:
			case InstructionIndex.DALOAD:
			case InstructionIndex.DASTORE:
			case InstructionIndex.DCMPG:
			case InstructionIndex.DCMPL:
			case InstructionIndex.DCONST_0:
			case InstructionIndex.DCONST_1:
			case InstructionIndex.DDIV:
			case InstructionIndex.DLOAD_0:
			case InstructionIndex.DLOAD_1:
			case InstructionIndex.DLOAD_2:
			case InstructionIndex.DLOAD_3:
			case InstructionIndex.DMUL:
			case InstructionIndex.DNEG:
			case InstructionIndex.DREM:
			case InstructionIndex.DSTORE_0:
			case InstructionIndex.DSTORE_1:
			case InstructionIndex.DSTORE_2:
			case InstructionIndex.DSTORE_3:
			case InstructionIndex.DSUB:
			case InstructionIndex.DUP:
			case InstructionIndex.DUP2:
			case InstructionIndex.DUP2_X1:
			case InstructionIndex.DUP2_X2:
			case InstructionIndex.DUP_X1:
			case InstructionIndex.DUP_X2:
			case InstructionIndex.F2D:
			case InstructionIndex.F2I:
			case InstructionIndex.F2L:
			case InstructionIndex.FADD:
			case InstructionIndex.FALOAD:
			case InstructionIndex.FASTORE:
			case InstructionIndex.FCMPG:
			case InstructionIndex.FCMPL:
			case InstructionIndex.FCONST_0:
			case InstructionIndex.FCONST_1:
			case InstructionIndex.FCONST_2:
			case InstructionIndex.FDIV:
			case InstructionIndex.FLOAD_0:
			case InstructionIndex.FLOAD_1:
			case InstructionIndex.FLOAD_2:
			case InstructionIndex.FLOAD_3:
			case InstructionIndex.FMUL:
			case InstructionIndex.FNEG:
			case InstructionIndex.FREM:
			case InstructionIndex.FSTORE_0:
			case InstructionIndex.FSTORE_1:
			case InstructionIndex.FSTORE_2:
			case InstructionIndex.FSTORE_3:
			case InstructionIndex.FSUB:
			case InstructionIndex.I2B:
			case InstructionIndex.I2C:
			case InstructionIndex.I2D:
			case InstructionIndex.I2F:
			case InstructionIndex.I2L:
			case InstructionIndex.I2S:
			case InstructionIndex.IADD:
			case InstructionIndex.IALOAD:
			case InstructionIndex.IAND:
			case InstructionIndex.IASTORE:
			case InstructionIndex.ICONST_0:
			case InstructionIndex.ICONST_1:
			case InstructionIndex.ICONST_2:
			case InstructionIndex.ICONST_3:
			case InstructionIndex.ICONST_4:
			case InstructionIndex.ICONST_5:
			case InstructionIndex.ICONST_M1:
			case InstructionIndex.IDIV:
			case InstructionIndex.ILOAD_0:
			case InstructionIndex.ILOAD_1:
			case InstructionIndex.ILOAD_2:
			case InstructionIndex.ILOAD_3:
			case InstructionIndex.IMUL:
			case InstructionIndex.INEG:
			case InstructionIndex.IOR:
			case InstructionIndex.IREM:
			case InstructionIndex.ISHL:
			case InstructionIndex.ISHR:
			case InstructionIndex.ISTORE_0:
			case InstructionIndex.ISTORE_1:
			case InstructionIndex.ISTORE_2:
			case InstructionIndex.ISTORE_3:
			case InstructionIndex.ISUB:
			case InstructionIndex.IUSHR:
			case InstructionIndex.IXOR:
			case InstructionIndex.L2D:
			case InstructionIndex.L2F:
			case InstructionIndex.L2I:
			case InstructionIndex.LADD:
			case InstructionIndex.LALOAD:
			case InstructionIndex.LAND:
			case InstructionIndex.LASTORE:
			case InstructionIndex.LCMP:
			case InstructionIndex.LCONST_0:
			case InstructionIndex.LCONST_1:
			case InstructionIndex.LDIV:
			case InstructionIndex.LLOAD_0:
			case InstructionIndex.LLOAD_1:
			case InstructionIndex.LLOAD_2:
			case InstructionIndex.LLOAD_3:
			case InstructionIndex.LMUL:
			case InstructionIndex.LNEG:
			case InstructionIndex.LOR:
			case InstructionIndex.LREM:
			case InstructionIndex.LSHL:
			case InstructionIndex.LSHR:
			case InstructionIndex.LSTORE_0:
			case InstructionIndex.LSTORE_1:
			case InstructionIndex.LSTORE_2:
			case InstructionIndex.LSTORE_3:
			case InstructionIndex.LSUB:
			case InstructionIndex.LUSHR:
			case InstructionIndex.LXOR:
			case InstructionIndex.MONITORENTER:
			case InstructionIndex.MONITOREXIT:
			case InstructionIndex.NOP:
			case InstructionIndex.POP:
			case InstructionIndex.POP2:
			case InstructionIndex.SALOAD:
			case InstructionIndex.SASTORE:
			case InstructionIndex.SWAP:
				args = new Object[0];
				naturalflow = true;
				break;
				
				// Method invocations
			case InstructionIndex.INVOKEINTERFACE:
			case InstructionIndex.INVOKESPECIAL:
			case InstructionIndex.INVOKESTATIC:
			case InstructionIndex.INVOKEVIRTUAL:
				naturalflow = true;
				
				// Reference is in the constant pool
				MethodReference mr = __pool.<MethodReference>require(
					MethodReference.class,
					__readUnsignedShort(__code, argbase));
				
				// {@squirreljme.error JI1o Invocation of method did not
				// have the matching interface/not-interface attribute.
				// (The operation; The address; The method reference)}
				if (mr.isInterface() !=
					(op == InstructionIndex.INVOKEINTERFACE))
					throw new JITException(String.format("JI1o %d %d %s",
						op, __a, mr));
				
				args = new Object[]{mr};
				break;
				
				// Allocate (but do not construct) instance of new object
			case InstructionIndex.NEW:
				naturalflow = true;
				args = new Object[]{__pool.<ClassName>require(ClassName.class,
					__readUnsignedShort(__code, argbase))};
				break;
				
				// {@squirreljme.error JI1p The operation at the specified
				// address is not supported yet. (The operation; The name of
				// the operation; The address it is at)}
			default:
				throw new RuntimeException(String.format("JI1p %d %s %d",
					op, InstructionMnemonics.toString(op), __a));
		}
		
		// Set
		this._args = args;
		this.naturalflow = naturalflow;
		
		// Copy jump targets
		List<JumpTarget> jt = new ArrayList<>();
		for (Object o : args)
			if (o instanceof JumpTarget)
				jt.add((JumpTarget)o);
		this._jumptargets = jt.<JumpTarget>toArray(
			new JumpTarget[jt.size()]);
	}
	
	/**
	 * Returns the address of this instruction.
	 *
	 * @return The instruction address.
	 * @since 2017/05/20
	 */
	public int address()
	{
		return this.address;
	}
	
	/**
	 * Returns the argument for this given instruction.
	 *
	 * @param <T> The type of argument to get.
	 * @param __i The index of the argument.
	 * @param __cl The class to cast to.
	 * @return The argument as the given class.
	 * @throws ClassCastException If the class is not castable.
	 * @throws IndexOutOfBoundsException If the argument index is not
	 * within bounds.
	 * @throws NullPointerException On null arguments.
	 * @since 2017/05/20
	 */
	public <T> T argument(int __i, Class<T> __cl)
		throws ClassCastException, IndexOutOfBoundsException,
			NullPointerException
	{
		// Check
		if (__cl == null)
			throw new NullPointerException("NARG");
		
		return __cl.cast(this._args[__i]);
	}
	
	/**
	 * The number of arguments the instruction takes.
	 *
	 * @return The argument count.
	 * @since 2017/05/20
	 */
	public int count()
	{
		return this._args.length;
	}
	
	/**
	 * Returns {@code true} if the instruction has natural flow.
	 *
	 * @return The instruction flows to the next naturally.
	 * @since 2017/05/20
	 */
	public boolean hasNaturalFlow()
	{
		return this.naturalflow;
	}
	
	/**
	 * Returns the jump targets for this instruction.
	 *
	 * @return The jump targets for this instruction.
	 * @since 2017/05/18
	 */
	public JumpTarget[] jumpTargets()
	{
		return this._jumptargets.clone();
	}
	
	/**
	 * Returns the operation that this performs.
	 *
	 * @return The operation.
	 * @since 2017/05/20
	 */
	public int operation()
	{
		return op;
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2017/05/20
	 */
	@Override
	public String toString()
	{
		Reference<String> ref = this._string;
		String rv;
		
		if (ref == null || null == (rv = ref.get()))
		{
			// Address first
			StringBuilder sb = new StringBuilder("@");
			sb.append(this.address);
			
			// Then the operation
			sb.append('#');
			sb.append(InstructionMnemonics.toString(this.op));
			
			// Add marker if it flows naturally
			if (this.naturalflow)
				sb.append('~');
			
			// Then the arguments
			sb.append(":[");
			Object[] args = this._args;
			for (int i = 0, n = args.length; i < n; i++)
			{
				if (i > 0)
					sb.append(", ");
				sb.append(args[i]);
			}
			sb.append(']');
			
			// Cache
			this._string = new WeakReference<>((rv = sb.toString()));
		}
		
		return rv;
	}
	
	/**
	 * Reads an unsigned short from the specified array.
	 *
	 * @param __a The array to read from.
	 * @param __o The offset to read from.
	 * @return The read value.
	 * @throws JITException If the offset exceeds the bounds of the given
	 * array.
	 * @throws NullPointerException On null arguments.
	 * @since 2017/07/15
	 */
	private static final int __readUnsignedShort(byte[] __a, int __o)
		throws JITException, NullPointerException
	{
		// Check
		if (__a == null)
			throw new NullPointerException("NARG");
		
		// {@squirreljme.error JI1q Illegal read off the end of the instruction
		// array. (The offset; The length of the code array)}
		if (__o < 0 || __o + 1 >= __a.length)
			throw new JITException(String.format("JI1q %d %d", __o,
				__a.length));
		
		return ((__a[__o] & 0xFF) << 8) | (__a[__o + 1] & 0xFF);
	}
}
