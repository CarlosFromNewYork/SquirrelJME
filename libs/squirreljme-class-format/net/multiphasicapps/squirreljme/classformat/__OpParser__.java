// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) Steven Gawroriski <steven@multiphasicapps.net>
//     Copyright (C) Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// See license.mkd for licensing and copyright information.
// ---------------------------------------------------------------------------

package net.multiphasicapps.squirreljme.classformat;

import java.io.IOException;
import java.util.Map;
import net.multiphasicapps.io.data.ExtendedDataInputStream;
import net.multiphasicapps.squirreljme.java.symbols.FieldSymbol;
import net.multiphasicapps.squirreljme.java.symbols.MethodSymbol;
import net.multiphasicapps.squirreljme.linkage.ClassFlags;
import net.multiphasicapps.squirreljme.linkage.FieldFlags;
import net.multiphasicapps.squirreljme.linkage.FieldLinkage;
import net.multiphasicapps.squirreljme.linkage.FieldReference;
import net.multiphasicapps.squirreljme.linkage.MethodFlags;
import net.multiphasicapps.squirreljme.linkage.MethodInvokeType;
import net.multiphasicapps.squirreljme.linkage.MethodLinkage;
import net.multiphasicapps.squirreljme.linkage.MethodReference;

/**
 * This performs the actual parsing of the opcodes and generates operations
 * for it.
 *
 * @since 2016/08/29
 */
final class __OpParser__
{
	/** Implicit next operation. */
	private static final int[] IMPLICIT_NEXT =
		new int[]{-1};
	
	/** The input operation data. */
	protected final ExtendedDataInputStream input;
	
	/** The class flags. */
	protected final ClassFlags classflags;
	
	/** The constant pool. */
	protected final ConstantPool pool;
	
	/** The code description writer. */
	protected final CodeDescriptionStream writer;
	
	/** The method this is in. */
	protected final MethodReference methodref;
	
	/** The stack map table. */
	private final Map<Integer, __SMTState__> _smt;
	
	/** The working stack map state. */
	private final __SMTState__ _smwork;
	
	/**
	 * Initializes the operation parser.
	 *
	 * @param __desc The code description output.
	 * @param __dis The input data source.
	 * @param __smt The stack map table.
	 * @param __cf The class flags.
	 * @param __pool The constant pool.
	 * @param __mr This method reference.
	 * @throws NullPointerException On null arguments.
	 * @since 2016/08/29
	 */
	__OpParser__(CodeDescriptionStream __desc,
		ExtendedDataInputStream __dis,
		Map<Integer, __SMTState__> __smt, ClassFlags __cf,
		ConstantPool __pool, MethodReference __mr)
		throws NullPointerException
	{
		// Check
		if (__dis == null || __smt == null || __cf == null || __pool == null ||
			__desc == null || __mr == null)
			throw new NullPointerException("NARG");
		
		// Set
		this.input = __dis;
		this.classflags = __cf;
		this._smt = __smt;
		this.pool = __pool;
		this.writer = __desc;
		this.methodref = __mr;
		
		// Set working state
		this._smwork = new __SMTState__(__smt.get(0));
	}
	
	/**
	 * Decodes operations and calls the method logic generator interface.
	 *
	 * @throws IOException On read errors.
	 * @throws ClassFormatException On decode errors.
	 * @since 2016/08/24
	 */
	void __decodeAll()
		throws IOException, ClassFormatException
	{
		// Get
		ExtendedDataInputStream input = this.input;
		Map<Integer, __SMTState__> smt = this._smt;
		__SMTState__ smwork = this._smwork;
		CodeDescriptionStream writer = this.writer;
		
		// Decode loop
		for (;;)
		{
			// Read
			int nowpos = (int)input.size();
			int code = input.read();
			
			// EOF?
			if (code < 0)
				break;
			
			// Wide? Read another
			if (code == ClassByteCodeIndex.WIDE)
				code = (code << 8) | input.readUnsignedByte();
			
			// If there is stack state for this position then set it
			__SMTState__ bias = smt.get(nowpos);
			if (bias != null)
				smwork.from(bias);
			
			// Report it
			writer.atInstruction(code, nowpos);
			
			// Decode single operation
			int[] jt = __decodeOne(code);
			if (jt == null)
				throw new RuntimeException("OOPS");
			
			// Handle jump targets
			// This verifies that for each jump target or implicit flow that
			// the resulting stack state matches the expected one
			int n = jt.length, inextpos = -1;
			for (int i = 0; i < n; i++)
			{
				// Get jump target, if it is implicit next then use the
				// following instruction address
				int jumpto = jt[i];
				boolean implicit;
				if ((implicit = (jumpto == -1)))
				{
					jumpto = (int)input.size();
					inextpos = jumpto;
				}
				
				// If there is no state, do not need to verify
				__SMTState__ intostate = smt.get(jumpto);
				if (intostate == null)
					continue;
				
				// Verify that they are correct
				if (true)
					throw new todo.TODO();
			}
			
			// Report end
			writer.endInstruction(code, nowpos, inextpos);
		}
		
		throw new todo.TODO();
	}
	
	/**
	 * Decodes a single operation.
	 *
	 * @param __code The byte code to decode.
	 * @return The jump targets.
	 * @since 2016/08/26
	 */
	private int[] __decodeOne(int __code)
		throws IOException, ClassFormatException
	{
		// Get
		ExtendedDataInputStream input = this.input;
		
		// Depends
		switch (__code)
		{
				// Waste electrons
			case ClassByteCodeIndex.NOP:
				throw new todo.TODO();
			
				// Push null constant
			case ClassByteCodeIndex.ACONST_NULL:
				throw new todo.TODO();
			
				// Push int constant
			case ClassByteCodeIndex.ICONST_M1:
			case ClassByteCodeIndex.ICONST_0:
			case ClassByteCodeIndex.ICONST_1:
			case ClassByteCodeIndex.ICONST_2:
			case ClassByteCodeIndex.ICONST_3:
			case ClassByteCodeIndex.ICONST_4:
			case ClassByteCodeIndex.ICONST_5:
				throw new todo.TODO();
			
				// Push long constant
			case ClassByteCodeIndex.LCONST_0:
			case ClassByteCodeIndex.LCONST_1:
				throw new todo.TODO();
			
				// Push float constant
			case ClassByteCodeIndex.FCONST_0:
			case ClassByteCodeIndex.FCONST_1:
			case ClassByteCodeIndex.FCONST_2:
				throw new todo.TODO();
			
				// Push double constant
			case ClassByteCodeIndex.DCONST_0:
			case ClassByteCodeIndex.DCONST_1:
				throw new todo.TODO();
			
			case ClassByteCodeIndex.BIPUSH:
			case ClassByteCodeIndex.SIPUSH:
			case ClassByteCodeIndex.LDC:
			case ClassByteCodeIndex.LDC_W:
			case ClassByteCodeIndex.LDC2_W:
				throw new todo.TODO();
				
				// Push local int to the stack
			case ClassByteCodeIndex.ILOAD:
				return __executeLoad(StackMapType.INTEGER,
					input.readUnsignedByte());
				
				// Push local int to the stack (wide)
			case ClassByteCodeIndex.WIDE_ILOAD:
				return __executeLoad(StackMapType.INTEGER,
					input.readUnsignedShort());
				
				// Push local long to the stack
			case ClassByteCodeIndex.LLOAD:
				return __executeLoad(StackMapType.LONG,
					input.readUnsignedByte());
				
				// Push local long to the stack (wide)
			case ClassByteCodeIndex.WIDE_LLOAD:
				return __executeLoad(StackMapType.LONG,
					input.readUnsignedShort());
				
				// Push local float to the stack
			case ClassByteCodeIndex.FLOAD:
				return __executeLoad(StackMapType.FLOAT,
					input.readUnsignedByte());
				
				// Push local float to the stack (wide)
			case ClassByteCodeIndex.WIDE_FLOAD:
				return __executeLoad(StackMapType.FLOAT,
					input.readUnsignedShort());
			
				// Push local double to the stack
			case ClassByteCodeIndex.DLOAD:
				return __executeLoad(StackMapType.DOUBLE,
					input.readUnsignedByte());
				
				// Push local double to the stack (wide)
			case ClassByteCodeIndex.WIDE_DLOAD:
				return __executeLoad(StackMapType.DOUBLE,
					input.readUnsignedShort());
				
				// Push local reference to the stack
			case ClassByteCodeIndex.ALOAD:
				return __executeLoad(StackMapType.OBJECT,
					input.readUnsignedByte());
				
				// Push local reference to the stack (wide)
			case ClassByteCodeIndex.WIDE_ALOAD:
				return __executeLoad(StackMapType.OBJECT,
					input.readUnsignedShort());
			
				// Load int from local
			case ClassByteCodeIndex.ILOAD_0:
			case ClassByteCodeIndex.ILOAD_1:
			case ClassByteCodeIndex.ILOAD_2:
			case ClassByteCodeIndex.ILOAD_3:
				return __executeLoad(StackMapType.INTEGER,
					__code - ClassByteCodeIndex.ILOAD_0);
			
				// Load long from local
			case ClassByteCodeIndex.LLOAD_0:
			case ClassByteCodeIndex.LLOAD_1:
			case ClassByteCodeIndex.LLOAD_2:
			case ClassByteCodeIndex.LLOAD_3:
				return __executeLoad(StackMapType.LONG,
					__code - ClassByteCodeIndex.LLOAD_0);
			
				// Load float from local
			case ClassByteCodeIndex.FLOAD_0:
			case ClassByteCodeIndex.FLOAD_1:
			case ClassByteCodeIndex.FLOAD_2:
			case ClassByteCodeIndex.FLOAD_3:
				return __executeLoad(StackMapType.FLOAT,
					__code - ClassByteCodeIndex.FLOAD_0);
			
				// Load double from local
			case ClassByteCodeIndex.DLOAD_0:
			case ClassByteCodeIndex.DLOAD_1:
			case ClassByteCodeIndex.DLOAD_2:
			case ClassByteCodeIndex.DLOAD_3:
				return __executeLoad(StackMapType.DOUBLE,
					__code - ClassByteCodeIndex.DLOAD_0);
			
				// Load reference from local
			case ClassByteCodeIndex.ALOAD_0:
			case ClassByteCodeIndex.ALOAD_1:
			case ClassByteCodeIndex.ALOAD_2:
			case ClassByteCodeIndex.ALOAD_3:
				return __executeLoad(StackMapType.OBJECT,
					__code - ClassByteCodeIndex.ALOAD_0);
			
			case ClassByteCodeIndex.IALOAD:
			case ClassByteCodeIndex.LALOAD:
			case ClassByteCodeIndex.FALOAD:
			case ClassByteCodeIndex.DALOAD:
			case ClassByteCodeIndex.AALOAD:
			case ClassByteCodeIndex.BALOAD:
			case ClassByteCodeIndex.CALOAD:
			case ClassByteCodeIndex.SALOAD:
			case ClassByteCodeIndex.ISTORE:
			case ClassByteCodeIndex.LSTORE:
			case ClassByteCodeIndex.FSTORE:
			case ClassByteCodeIndex.DSTORE:
			case ClassByteCodeIndex.ASTORE:
				throw new todo.TODO();
			
				// Store int into local
			case ClassByteCodeIndex.ISTORE_0:
			case ClassByteCodeIndex.ISTORE_1:
			case ClassByteCodeIndex.ISTORE_2:
			case ClassByteCodeIndex.ISTORE_3:
				throw new todo.TODO();
			
				// Store long into local
			case ClassByteCodeIndex.LSTORE_0:
			case ClassByteCodeIndex.LSTORE_1:
			case ClassByteCodeIndex.LSTORE_2:
			case ClassByteCodeIndex.LSTORE_3:
				throw new todo.TODO();
			
				// Store float into local
			case ClassByteCodeIndex.FSTORE_0:
			case ClassByteCodeIndex.FSTORE_1:
			case ClassByteCodeIndex.FSTORE_2:
			case ClassByteCodeIndex.FSTORE_3:
				throw new todo.TODO();
			
				// Store double into local
			case ClassByteCodeIndex.DSTORE_0:
			case ClassByteCodeIndex.DSTORE_1:
			case ClassByteCodeIndex.DSTORE_2:
			case ClassByteCodeIndex.DSTORE_3:
				throw new todo.TODO();
			
				// Store object into local
			case ClassByteCodeIndex.ASTORE_0:
			case ClassByteCodeIndex.ASTORE_1:
			case ClassByteCodeIndex.ASTORE_2:
			case ClassByteCodeIndex.ASTORE_3:
				throw new todo.TODO();
				
				// Invoke interface method
			case ClassByteCodeIndex.INVOKEINTERFACE:
				return __executeInvoke(MethodInvokeType.INTERFACE,
					input.readUnsignedShort() | (input.readByte() & 0) |
					(input.readByte() & 0));
				
				// Invoke constructor or private method
			case ClassByteCodeIndex.INVOKESPECIAL:
				return __executeInvoke(MethodInvokeType.SPECIAL,
					input.readUnsignedShort());
				
				// Invoke static method
			case ClassByteCodeIndex.INVOKESTATIC:
				return __executeInvoke(MethodInvokeType.STATIC,
					input.readUnsignedShort());
				
				// Invoke virtual method
			case ClassByteCodeIndex.INVOKEVIRTUAL:
				return __executeInvoke(MethodInvokeType.VIRTUAL,
					input.readUnsignedShort());
			
				// {@squirreljme.error AY38 Defined operation cannot be
				// used in Java ME programs. (The operation)}
			case ClassByteCodeIndex.JSR:
			case ClassByteCodeIndex.JSR_W:
			case ClassByteCodeIndex.RET:
			case ClassByteCodeIndex.INVOKEDYNAMIC:
			case ClassByteCodeIndex.BREAKPOINT:
			case ClassByteCodeIndex.IMPDEP1:
			case ClassByteCodeIndex.IMPDEP2:
				throw new ClassFormatException(
					String.format("AY38 %d", __code));
			
			case ClassByteCodeIndex.IASTORE:
			case ClassByteCodeIndex.LASTORE:
			case ClassByteCodeIndex.FASTORE:
			case ClassByteCodeIndex.DASTORE:
			case ClassByteCodeIndex.AASTORE:
			case ClassByteCodeIndex.BASTORE:
			case ClassByteCodeIndex.CASTORE:
			case ClassByteCodeIndex.SASTORE:
			case ClassByteCodeIndex.POP:
			case ClassByteCodeIndex.POP2:
			case ClassByteCodeIndex.DUP:
			case ClassByteCodeIndex.DUP_X1:
			case ClassByteCodeIndex.DUP_X2:
			case ClassByteCodeIndex.DUP2:
			case ClassByteCodeIndex.DUP2_X1:
			case ClassByteCodeIndex.DUP2_X2:
			case ClassByteCodeIndex.SWAP:
			case ClassByteCodeIndex.IADD:
			case ClassByteCodeIndex.LADD:
			case ClassByteCodeIndex.FADD:
			case ClassByteCodeIndex.DADD:
			case ClassByteCodeIndex.ISUB:
			case ClassByteCodeIndex.LSUB:
			case ClassByteCodeIndex.FSUB:
			case ClassByteCodeIndex.DSUB:
			case ClassByteCodeIndex.IMUL:
			case ClassByteCodeIndex.LMUL:
			case ClassByteCodeIndex.FMUL:
			case ClassByteCodeIndex.DMUL:
			case ClassByteCodeIndex.IDIV:
			case ClassByteCodeIndex.LDIV:
			case ClassByteCodeIndex.FDIV:
			case ClassByteCodeIndex.DDIV:
			case ClassByteCodeIndex.IREM:
			case ClassByteCodeIndex.LREM:
			case ClassByteCodeIndex.FREM:
			case ClassByteCodeIndex.DREM:
			case ClassByteCodeIndex.INEG:
			case ClassByteCodeIndex.LNEG:
			case ClassByteCodeIndex.FNEG:
			case ClassByteCodeIndex.DNEG:
			case ClassByteCodeIndex.ISHL:
			case ClassByteCodeIndex.LSHL:
			case ClassByteCodeIndex.ISHR:
			case ClassByteCodeIndex.LSHR:
			case ClassByteCodeIndex.IUSHR:
			case ClassByteCodeIndex.LUSHR:
			case ClassByteCodeIndex.IAND:
			case ClassByteCodeIndex.LAND:
			case ClassByteCodeIndex.IOR:
			case ClassByteCodeIndex.LOR:
			case ClassByteCodeIndex.IXOR:
			case ClassByteCodeIndex.LXOR:
			case ClassByteCodeIndex.IINC:
			case ClassByteCodeIndex.I2L:
			case ClassByteCodeIndex.I2F:
			case ClassByteCodeIndex.I2D:
			case ClassByteCodeIndex.L2I:
			case ClassByteCodeIndex.L2F:
			case ClassByteCodeIndex.L2D:
			case ClassByteCodeIndex.F2I:
			case ClassByteCodeIndex.F2L:
			case ClassByteCodeIndex.F2D:
			case ClassByteCodeIndex.D2I:
			case ClassByteCodeIndex.D2L:
			case ClassByteCodeIndex.D2F:
			case ClassByteCodeIndex.I2B:
			case ClassByteCodeIndex.I2C:
			case ClassByteCodeIndex.I2S:
			case ClassByteCodeIndex.LCMP:
			case ClassByteCodeIndex.FCMPL:
			case ClassByteCodeIndex.FCMPG:
			case ClassByteCodeIndex.DCMPL:
			case ClassByteCodeIndex.DCMPG:
			case ClassByteCodeIndex.IFEQ:
			case ClassByteCodeIndex.IFNE:
			case ClassByteCodeIndex.IFLT:
			case ClassByteCodeIndex.IFGE:
			case ClassByteCodeIndex.IFGT:
			case ClassByteCodeIndex.IFLE:
			case ClassByteCodeIndex.IF_ICMPEQ:
			case ClassByteCodeIndex.IF_ICMPNE:
			case ClassByteCodeIndex.IF_ICMPLT:
			case ClassByteCodeIndex.IF_ICMPGE:
			case ClassByteCodeIndex.IF_ICMPGT:
			case ClassByteCodeIndex.IF_ICMPLE:
			case ClassByteCodeIndex.IF_ACMPEQ:
			case ClassByteCodeIndex.IF_ACMPNE:
			case ClassByteCodeIndex.GOTO:
			case ClassByteCodeIndex.TABLESWITCH:
			case ClassByteCodeIndex.LOOKUPSWITCH:
			case ClassByteCodeIndex.IRETURN:
			case ClassByteCodeIndex.LRETURN:
			case ClassByteCodeIndex.FRETURN:
			case ClassByteCodeIndex.DRETURN:
			case ClassByteCodeIndex.ARETURN:
			case ClassByteCodeIndex.RETURN:
			case ClassByteCodeIndex.GETSTATIC:
			case ClassByteCodeIndex.PUTSTATIC:
			case ClassByteCodeIndex.GETFIELD:
			case ClassByteCodeIndex.PUTFIELD:
			case ClassByteCodeIndex.NEW:
			case ClassByteCodeIndex.NEWARRAY:
			case ClassByteCodeIndex.ANEWARRAY:
			case ClassByteCodeIndex.ARRAYLENGTH:
			case ClassByteCodeIndex.ATHROW:
			case ClassByteCodeIndex.CHECKCAST:
			case ClassByteCodeIndex.INSTANCEOF:
			case ClassByteCodeIndex.MONITORENTER:
			case ClassByteCodeIndex.MONITOREXIT:
			case ClassByteCodeIndex.MULTIANEWARRAY:
			case ClassByteCodeIndex.IFNULL:
			case ClassByteCodeIndex.IFNONNULL:
			case ClassByteCodeIndex.GOTO_W:
			
				// {@squirreljme.error AY37 Unknown byte-code operation.
				// (The operation)}
			default:
				throw new ClassFormatException(String.format("AY37 %d", __code));
		}
	}
	
	/**
	 * Executes an invoke of a method.
	 *
	 * @param __type The type of invocation performed.
	 * @param __pid The pool index with the method reference.
	 * @return Jump targets.
	 * @throws NullPointerException On null arguments.
	 * @since 2016/09/05
	 */
	private int[] __executeInvoke(MethodInvokeType __type, int __pid)
		throws NullPointerException
	{
		// Check
		if (__type == null)
			throw new NullPointerException("NARG");
		
		// Get pool reference
		MethodReference ref = this.pool.get(__pid).<MethodReference>
			get(true, MethodReference.class);
		
		// Debug
		System.err.printf("DEBUG -- Invoke %s %d %s%n", __type, __pid, ref);
		
		// Is this an instance invocation?
		boolean isinstance = __type.isInstance();
		
		// Get the stack layout, which is needed for verifcation
		__SMTState__ smwork = this._smwork;
		__SMTStack__ stack = smwork._stack;
		
		// Count the number of stack elements to count and pass to the method
		MethodSymbol sym = ref.methodType();
		int na = sym.argumentCount();
		int xc = (isinstance ? 1 : 0),
			vi = xc;
		for (int i = 0; i < na; i++)
		{
			if (StackMapType.bySymbol(sym.get(i)).isWide())
				xc += 2;
			else
				xc++;
			
			// Increase normal count
			vi++;
		}
		
		// {@squirreljme.error AY3y Stack underflow during invocation of
		// method.}
		int top = stack.top(), at = top - 1, end = top - xc;
		if (end < 0)
			throw new ClassFormatException("AY3y");
		
		// Stack positions and types
		CodeVariable[] vargs = new CodeVariable[vi];
		StackMapType[] st = new StackMapType[vi];
		
		// Fill types and check that they are valid
		int write = vi - 1, popcount = 0;
		for (int i = na - 1; i >= 0; i--)
		{
			// Map it
			StackMapType map = stack.get(at);
			StackMapType is = StackMapType.bySymbol(sym.get(i));
			
			// Check for top
			if (is.isWide())
			{
				// {@squirreljme.error AY0k Expected a TOP to be on the stack.
				// (The actual value on the stack)}
				if (map != StackMapType.TOP)
					throw new ClassFormatException(String.format("AY0k %s",
						map));
				
				// Get the next lowest entry
				map = stack.get(--at);
				
				// This gets popped
				popcount++;
			}
			
			// {@squirreljme.error AY0p Did not expect the specified type on
			// the stack. (The actual type; The expected type)}
			if (map != is)
				throw new ClassFormatException(String.format("AY0p %s", map,
					is));
			
			// Map it
			vargs[write] = CodeVariable.of(true, at--);
			st[write--] = map;
			
			// And it gets popped
			popcount++;
		}
		
		// Check if instance is correct
		if (isinstance)
		{
			// Map it
			StackMapType map = stack.get(at);
			
			// {@squirreljme.error AY3z Expected an object to be the instance
			// variable. (The actual type)}
			if (map != StackMapType.OBJECT)
				throw new ClassFormatException(String.format("AY3z %s", map));
			
			// Store
			vargs[write] = CodeVariable.of(true, at--);
			st[write--] = map;
			
			// Increase the pop count
			popcount++;
		}
		
		// Handle return value if any
		FieldSymbol rv = sym.returnValue();
		int rvi;
		StackMapType rvs;
		if (rv != null)
		{
			rvs = StackMapType.bySymbol(rv);
			rvi = end;
			
			// {@squirreljme.error AY40 Stack overflow writing return value.}
			if (rvi >= stack.size())
				throw new ClassFormatException("AY40");
		}
		
		// No return value
		else
		{
			rvs = null;
			rvi = -1;
		}
		
		// Pop all stack values
		stack.__pop(this, popcount);
		
		// Send in the call
		this.writer.invokeMethod(new MethodLinkage(this.methodref, ref,
			__type), end, (rv != null ? (popcount > 0 ? vargs[0] :
			CodeVariable.of(true, end)) : null), rvs, vargs, st);
		
		// Push return value if there is one
		if (rv != null)
			stack.push(rvs);
		
		// Implicit next
		return IMPLICIT_NEXT;
	}
	
	/**
	 * Load from local variable and push to the top of the stack.
	 *
	 * @param __t The type of value to push.
	 * @param __from The variable to load from.
	 * @return Jump targets.
	 * @throws NullPointerException On null arguments.
	 * @since 2016/08/27
	 */
	private int[] __executeLoad(StackMapType __t, int __from)
		throws NullPointerException
	{
		// Check
		if (__t == null)
			throw new NullPointerException("NARG");
		
		// Get
		__SMTState__ smwork = this._smwork;
		__SMTLocals__ locals = smwork._locals;
		
		// {@squirreljme.error AY3w Attempt to push a local variable to the
		// stack of a different type. (The local variable index; The type that
		// the variable was; The expected type)}
		StackMapType was = locals.get(__from);
		if (was != __t)
			throw new ClassFormatException(String.format("AY3w %d %s %s",
				__from, was, __t));
		
		// Cache it on the stack
		__SMTStack__ stack = smwork._stack;
		int top = stack.top();
		stack.push(was);
		
		// Send copy operation
		this.writer.copy(__t, CodeVariable.of(false, __from),
			CodeVariable.of(true, top));
		
		// Implicit next
		return IMPLICIT_NEXT;
	}
}

