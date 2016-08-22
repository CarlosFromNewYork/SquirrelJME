// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) 2013-2016 Steven Gawroriski <steven@multiphasicapps.net>
//     Copyright (C) 2013-2016 Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// For more information see license.mkd.
// ---------------------------------------------------------------------------

package net.multiphasicapps.squirreljme.jit.mips;

import java.io.OutputStream;
import net.multiphasicapps.squirreljme.jit.base.JITException;
import net.multiphasicapps.squirreljme.jit.generic.GenericMethodWriter;
import net.multiphasicapps.squirreljme.jit.JITOutputConfig;

/**
 * This is a method writer which is able to generate MIPS machine code.
 *
 * @since 2016/08/19
 */
public class MIPSMethodWriter
	extends GenericMethodWriter
{
	/**
	 * Initializes the MIPS machine code generator.
	 *
	 * @param __conf The configuration used.
	 * @param __os Where bytes are written to.
	 * @since 2016/08/21
	 */
	public MIPSMethodWriter(JITOutputConfig.Immutable __conf,
		OutputStream __os)
	{
		super(__conf, __os);
		
		throw new Error("TODO");
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2016/08/19
	 */
	@Override
	public void close()
		throws JITException
	{
		if (true)
			throw new Error("TODO");
		
		// Close the base writer
		super.close();
	}
}

