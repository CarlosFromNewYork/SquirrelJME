// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) Steven Gawroriski <steven@multiphasicapps.net>
//     Copyright (C) Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// See license.mkd for licensing and copyright information.
// ---------------------------------------------------------------------------

package net.multiphasicapps.squirreljme.jit;

import net.multiphasicapps.squirreljme.classformat.CodeDescriptionStream;
import net.multiphasicapps.squirreljme.classformat.StackMapType;
import net.multiphasicapps.squirreljme.jit.base.JITException;

/**
 * The implementation of this class is given raw instructional data which
 * determines which operations are used in the byte code of a method.
 *
 * @since 2016/09/10
 */
public interface JITCodeWriter
	extends AutoCloseable, CodeDescriptionStream
{
	/**
	 * {@inheritDoc}
	 * @since 2016/09/10
	 */
	@Override
	public abstract void close()
		throws JITException;
	
	/**
	 * Primes the argument methods, essentially the input used for the method
	 * as it varies between methods. If the method is an instance method
	 * then the first argument will be {@link StackMapType#OBJECT}.
	 *
	 * All of the input arguments map to virtual registers starting from
	 * {@code 0}.
	 *
	 * Any {@code null} elements in the type array specify that the area is
	 * not used and would be the top of a {@code float} or {@code double}.
	 *
	 * @param __t The variable types which are used in input.
	 * @throws JITException If the arguments could not be mapped.
	 * @throws NullPointerException On null arguments.
	 * @since 2016/08/29
	 */
	public abstract void primeArguments(StackMapType[] __t)
		throws JITException, NullPointerException;
}

