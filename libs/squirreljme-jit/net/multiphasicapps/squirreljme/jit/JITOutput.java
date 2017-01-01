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

import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import net.multiphasicapps.squirreljme.java.symbols.ClassNameSymbol;
import net.multiphasicapps.squirreljme.jit.base.JITConfig;
import net.multiphasicapps.squirreljme.jit.base.JITException;

/**
 * This interface is defined by anything that wishes to output from the JIT
 * into an either ready to execute region of code and also potentially a cached
 * form for writing to the disk.
 *
 * @since 2016/07/04
 */
public interface JITOutput
	extends AutoCloseable
{
	/**
	 * This begins a namespace which may contain resources and classes. All
	 * classes and resources within a namespace are combined within a single
	 * unit and share the same data tables.
	 *
	 * @param __ns The namespace to output.
	 * @param __os The stream to write namespace data to, this will be
	 * {@code null} if the output itself is shared.
	 * @throws JITException If starting a namespace could not be performed.
	 * @throws NullPointerException On null arguments, except for {@code __os}.
	 * @since 2016/07/06
	 */
	public abstract JITNamespaceWriter beginNamespace(String __ns,
		OutputStream __os)
		throws JITException, NullPointerException;
	
	/**
	 * {@inheritDoc}
	 * @since 2016/09/28
	 */
	@Override
	public abstract void close()
		throws JITException;
	
	/**
	 * Returns the configuration which is used to configure the JIT.
	 *
	 * @return The JIT configuration.
	 * @since 2016/08/13
	 */
	public abstract JITConfig config();
	
	/**
	 * This indicates that the output is to a single shared output stream.
	 *
	 * @param __os The stream for output to a shared target.
	 * @throws JITException If it could not be handled.
	 * @since 2016/09/28
	 */
	public abstract void sharedOutput(OutputStream __os)
		throws JITException;
}

