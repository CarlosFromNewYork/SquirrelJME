// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) 2013-2016 Steven Gawroriski <steven@multiphasicapps.net>
//     Copyright (C) 2013-2016 Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// For more information see license.mkd.
// ---------------------------------------------------------------------------

package net.multiphasicapps.squirreljme.nativecode.powerpc;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import net.multiphasicapps.io.data.DataEndianess;
import net.multiphasicapps.io.data.ExtendedDataOutputStream;
import net.multiphasicapps.squirreljme.nativecode.NativeCodeException;
import net.multiphasicapps.squirreljme.nativecode.NativeCodeWriterOptions;
import net.multiphasicapps.squirreljme.nativecode.NativeRegister;
import net.multiphasicapps.squirreljme.nativecode.risc.RISCInstructionOutput;

/**
 * This is an output stream which writes PowerPC machine code.
 *
 * @since 2016/09/21
 */
public class PowerPCOutputStream
	extends ExtendedDataOutputStream
	implements RISCInstructionOutput<PowerPCRegister>
{
	/** The options used for the output. */
	protected final NativeCodeWriterOptions options;
	
	/**
	 * Initializes the machine code output stream.
	 *
	 * @param __o The options used for code generation.
	 * @param __os The output stream where bytes are written to.
	 * @throws NullPointerException On null arguments.
	 * @since 2016/09/21
	 */
	public PowerPCOutputStream(NativeCodeWriterOptions __o, OutputStream __os)
		throws NullPointerException
	{
		super(__os);
		
		// Check
		if (__o == null)
			throw new NullPointerException("NARG");
		
		// Set
		this.options = __o;
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2016/09/25
	 */
	@Override
	public void integerAddImmediate(PowerPCRegister __src, long __imm,
		PowerPCRegister __dest)
		throws IOException, NativeCodeException, NullPointerException
	{
		throw new Error("TODO");
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2016/09/25
	 */
	@Override
	public PowerPCRegister ofInteger(NativeRegister __r)
		throws NativeCodeException, NullPointerException
	{
		// Check
		if (__r == null)
			throw new NullPointerException("NARG");
		
		// {@squirreljme.error BT02 The input register is not a PowerPC register.
		// (The register)}
		if (!(__r instanceof PowerPCRegister))
			throw new NativeCodeException(String.format("BT02 %s", __r));
		
		// {@squirreljme.error BT03 The specified register is a not an integer
		// register. (The register)}
		PowerPCRegister rv = (PowerPCRegister)__r;
		if (!rv.isInteger())
			throw new NativeCodeException(String.format("BT03 %s", rv));
		
		// Ok
		return rv;
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2016/09/26
	 */
	@Override
	public void registerLoad(int __b, PowerPCRegister __base, int __off,
		PowerPCRegister __dest)
		throws IOException, NativeCodeException, NullPointerException
	{
		throw new Error("TODO");
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2016/09/25
	 */
	@Override
	public void registerStore(int __b, PowerPCRegister __src,
		PowerPCRegister __base, int __off)
		throws IOException, NativeCodeException, NullPointerException
	{
		throw new Error("TODO");
	}
}
