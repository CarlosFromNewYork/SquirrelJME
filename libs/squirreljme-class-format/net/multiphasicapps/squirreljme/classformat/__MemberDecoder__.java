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

import java.io.DataInputStream;
import java.io.IOException;
import net.multiphasicapps.squirreljme.linkage.ClassFlags;

/**
 * This is the base class for members to decode.
 *
 * @since 2016/08/27
 */
abstract class __MemberDecoder__
	extends __HasAttributes__
{
	/** The output class writer. */
	protected final ClassDescriptionStream classwriter;
	
	/** The input stream. */
	protected final DataInputStream input;
	
	/** The constant pool. */
	protected final ConstantPool pool;
	
	/** The owning class flags. */
	final ClassFlags _classflags;
	
	/**
	 * Initializes the base decoder.
	 *
	 * @param __cw The class writer to write to.
	 * @param __di The data input stream for the class file.
	 * @param __pool The constant pool.
	 * @param __cf The owning class flags.
	 * @throws NullPointerException On null arguments.
	 * @since 2016/08/18
	 */
	__MemberDecoder__(ClassDescriptionStream __cw, DataInputStream __di,
		ConstantPool __pool, ClassFlags __cf)
		throws NullPointerException
	{
		// Check
		if (__cw == null || __di == null || __pool == null || __cf == null)
			throw new NullPointerException("NARG");
		
		// Set
		this.classwriter = __cw;
		this.input = __di;
		this.pool = __pool;
		this._classflags = __cf;
	}
	 
	/**
	 * Decodes the actual member data.
	 *
	 * @throws IOException On read errors.
	 * @since 2016/08/27
	 */
	abstract void __decode()
		throws IOException;
}

