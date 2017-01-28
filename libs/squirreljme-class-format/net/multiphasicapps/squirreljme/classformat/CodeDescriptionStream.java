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

import net.multiphasicapps.squirreljme.linkage.MethodLinkage;

/**
 * This interface is called during the parsing of the code attribute within
 * a method.
 *
 * @since 2016/09/09
 */
public interface CodeDescriptionStream
{
	/**
	 * This is called when the instruction parse pointer is at the given
	 * position.
	 *
	 * @param __code The operation that is about to be decoded.
	 * @param __pos The position of the given operation.
	 * @since 2016/09/09
	 */
	public abstract void atInstruction(int __code, int __pos);
	
	/**
	 * This specifies the length of the program.
	 *
	 * @param __n The number of bytes the byte code is.
	 * @since 2016/09/16
	 */
	public abstract void codeLength(int __n);
	
	/**
	 * Copies the value of one variable to another.
	 *
	 * @param __type The type of value to copy.
	 * @param __from The source variable.
	 * @param __to The destination variable.
	 * @throws NullPointerException On null arguments.
	 * @since 2016/09/16
	 */
	public abstract void copy(StackMapType __type, CodeVariable __from,
		CodeVariable __to)
		throws NullPointerException;
	
	/**
	 * Invokes the given method using the specified linkage and invocation
	 * type.
	 *
	 * @param __link The method linkage.
	 * @param __d The depth of the Java stack when all arguments have been
	 * popped and no return value has been pushed.
	 * @param __rv The variable to obtain the return value.
	 * @param __rvt The type of return value returned.
	 * @param __cargs The input arguments to the method, the code variables
	 * which are used.
	 * @param __targ The stack map types for the input arguments.
	 * @throws NullPointerException On null arguments, except for {@code __rv}
	 * if there is no return value.
	 * @since 2016/09/16
	 */
	public abstract void invokeMethod(MethodLinkage __link, int __d,
		CodeVariable __rv, StackMapType __rvt, CodeVariable[] __cargs,
		StackMapType[] __targ)
		throws NullPointerException;
	
	/**
	 * This is called when the jump targets in the code parser are known. This
	 * may be used by the code being given this information to determine if
	 * state may need to be stored for a given position.
	 *
	 * @param __t The instructional jump targets.
	 * @throws NullPointerException On null arguments.
	 * @since 2016/09/09
	 */
	public abstract void jumpTargets(int[] __t)
		throws NullPointerException;
	
	/**
	 * This is called when the number of variables that are stored on the
	 * stack and the local variables is known.
	 *
	 * @param __ms The number of stack variables.
	 * @param __ml The number of local variables.
	 * @since 2016/09/09
	 */
	public abstract void variableCounts(int __ms, int __ml);
	
	/**
	 * This specifies the types of values that are used for each local variable
	 * and stack slots. This may be used by a class parser to determine the
	 * best means of allocating storage for these values.
	 *
	 * Each value in the array is a bitfield representing the ordinal of
	 * {@link StackMapType}.
	 *
	 * @param __l The types used for local variables.
	 * @param __s The types used for stack variables.
	 * @since 2016/09/27
	 */
	public abstract void variableTypes(byte[] __l, byte[] __s);
}

