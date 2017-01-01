// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) Steven Gawroriski <steven@multiphasicapps.net>
//     Copyright (C) Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// See license.mkd for licensing and copyright information.
// ---------------------------------------------------------------------------

package net.multiphasicapps.squirreljme.jit.basic;

import java.io.IOException;
import net.multiphasicapps.io.data.ExtendedDataOutputStream;
import net.multiphasicapps.squirreljme.jit.base.JITException;
import net.multiphasicapps.squirreljme.jit.JITResourceWriter;

/**
 * This writes resources to the output namespace.
 *
 * @since 2016/09/11
 */
@Deprecated
public class BasicResourceWriter
	extends __BaseWriter__
	implements JITResourceWriter
{
	/** The resource information in the contents table. */
	private final __Resource__ _resourceindex;
	
	/**
	 * Initializes the resource writer.
	 *
	 * @param __nsw The owning namespace writer.
	 * @throws NullPointerException On null arguments.
	 * @since 2016/09/11
	 */
	BasicResourceWriter(BasicNamespaceWriter __nsw, __Resource__ __rc)
		throws NullPointerException
	{
		super(__nsw, __rc);
		
		// Set
		this._resourceindex = __rc;
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2016/09/11
	 */
	@Override
	public void close()
		throws JITException
	{
		// Close the output
		try
		{
			this.output.close();
		}
		
		// {@squirreljme.error BV07 Could not close the resource output.}
		catch (IOException e)
		{
			throw new JITException("BV07", e);
		}
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2016/09/11
	 */
	@Override
	public void write(byte[] __b, int __o, int __l)
		throws IndexOutOfBoundsException, JITException, NullPointerException
	{
		// Close the output
		try
		{
			this.output.write(__b, __o, __l);
		}
		
		// {@squirreljme.error BV08 Could not write to the output.}
		catch (IOException e)
		{
			throw new JITException("BV08", e);
		}
	}
}

