// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) Steven Gawroriski <steven@multiphasicapps.net>
//     Copyright (C) Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// See license.mkd for licensing and copyright information.
// ---------------------------------------------------------------------------

package net.multiphasicapps.squirreljme.nativecode.mips;

import net.multiphasicapps.squirreljme.nativecode.base.NativeFloatType;
import net.multiphasicapps.squirreljme.nativecode.base.NativeTarget;
import net.multiphasicapps.squirreljme.nativecode.NativeABI;
import net.multiphasicapps.squirreljme.nativecode.NativeABIBuilder;
import net.multiphasicapps.squirreljme.nativecode.NativeABIProvider;
import net.multiphasicapps.squirreljme.nativecode.NativeCodeException;
import net.multiphasicapps.squirreljme.nativecode.NativeRegisterFloatType;
import net.multiphasicapps.squirreljme.nativecode.NativeRegisterIntegerType;
import net.multiphasicapps.squirreljme.nativecode.NativeStackDirection;
import net.multiphasicapps.squirreljme.nativecode.NativeStackFrameLayout;
import net.multiphasicapps.squirreljme.nativecode.
	NativeStackFrameLayoutBuilder;

/**
 * These are common ABIs that may be used on MIPS based systems.
 *
 * @since 2016/09/01
 */
public class MIPSABI
	implements NativeABIProvider
{
	/**
	 * {@inheritDoc}
	 * @since 2016/09/15
	 */
	@Override
	public NativeABI byName(String __n, NativeTarget __t)
		throws NativeCodeException, NullPointerException
	{
		// Check
		if (__n == null || __t == null)
			throw new NullPointerException("NARG");
		
		// Depends
		switch (__n)
		{
				// Embedded ABI
			case "eabi":
				return MIPSABI.eabi(__t);
			
				// {@squirreljme.error AW03 Unknown ABI. (The ABI)}
			default:
				throw new NativeCodeException(String.format("AW03 %s", __n));
		}
	}
	
	/**
	 * This returns the EABI ABI definition for MIPS systems.
	 *
	 * This ABI is described at
	 * {@link http://www.cygwin.com/ml/binutils/2003-06/msg00436.html}.
	 *
	 * @param __t The native target
	 * @return The ABI.
	 * @throws NullPointerException On null arguments.
	 * @since 2016/09/01
	 */
	public static NativeABI eabi(NativeTarget __t)
		throws NullPointerException
	{
		// Check
		if (__t == null)
			throw new NullPointerException("NARG");
		
		// Setup
		NativeABIBuilder ab = new NativeABIBuilder();
		NativeStackFrameLayoutBuilder sb = new NativeStackFrameLayoutBuilder();
		
		// Set target
		ab.nativeTarget(__t);
		
		// Align stack values to 4 bytes for easier reading
		sb.stackValueAlignment(4);
		
		// Floating point?
		NativeFloatType floattype = __t.floatingPoint();
		boolean hasfloat = floattype.isAnyHardware();
		
		// Add integer registers
		NativeRegisterIntegerType rtint =
			NativeABIBuilder.intRegisterType(__t.bits());
		for (int i = 0; i <= 31; i++)
			ab.addRegister(MIPSRegister.of(false, i), rtint);
		
		// Floating point?
		if (hasfloat)
		{
			// Determine the floating point size
			NativeRegisterFloatType flt =
				NativeABIBuilder.floatRegisterType(floattype);
			
			// Add them
			for (int i = 0; i <= 31; i++)
				ab.addRegister(MIPSRegister.of(true, i), flt);
		}
		
		// Register 1 becomes the scratch register
		ab.addScratch(MIPSRegister.of(false, 1));
		
		// Use library links using the global pointer
		ab.special(MIPSRegister.of(false, 28));
		
		// Stack grows down
		sb.stackRegister(MIPSRegister.of(false, 29));
		sb.frameRegister(MIPSRegister.of(false, 30));
		sb.stackAlignment(8);
		sb.stackDirection(NativeStackDirection.HIGH_TO_LOW);
	
		// Arguments
		for (int i = 4; i <= 11; i++)
			ab.addArgument(MIPSRegister.of(false, i));
		
		// Return value
		for (int i = 2; i <= 3; i++)
			ab.addResult(MIPSRegister.of(false, i));
	
		// Temporary
		for (int i = 2; i <= 15; i++)
			ab.addTemporary(MIPSRegister.of(false, i));
		for (int i = 24; i <= 25; i++)
			ab.addTemporary(MIPSRegister.of(false, i));
	
		// Saved registers
		for (int i = 16; i <= 23; i++)
			ab.addSaved(MIPSRegister.of(false, i));
		
		// Floating point?
		if (hasfloat)
		{
			// Arguments
			for (int i = 12; i <= 19; i++)
				ab.addArgument(MIPSRegister.of(true, i));
			
			// Return value
			for (int i = 0; i <= 1; i++)
				ab.addResult(MIPSRegister.of(true, i));
			
			// Temporary, make all registers temporary so that anything
			// that is used, becomes caller saved
			// Except #2, for scratch purposes
			for (int i = 0; i <= 31; i++)
				if (i != 2)
					ab.addTemporary(MIPSRegister.of(true, i));
			
			// Floating point register to shall act as scratch
			ab.addScratch(MIPSRegister.of(true, 2));
		}
		
		// Add the stack layout
		ab.stackLayout(sb.build());
		
		// Build
		return ab.build();
	}
}

