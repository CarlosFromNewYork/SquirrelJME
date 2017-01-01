// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) Steven Gawroriski <steven@multiphasicapps.net>
//     Copyright (C) Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// See license.mkd for licensing and copyright information.
// ---------------------------------------------------------------------------

package net.multiphasicapps.squirreljme.builder.linux.powerpc;

import java.io.IOException;
import net.multiphasicapps.squirreljme.builder.BuildConfig;
import net.multiphasicapps.squirreljme.builder.linux.LinuxBuildInstance;
import net.multiphasicapps.squirreljme.builder.TargetNotSupportedException;
import net.multiphasicapps.squirreljme.emulator.EmulatorConfig;
import net.multiphasicapps.squirreljme.jit.base.JITTriplet;
import net.multiphasicapps.squirreljme.jit.basic.BasicOutputFactory;
import net.multiphasicapps.squirreljme.jit.base.JITConfig;
import net.multiphasicapps.squirreljme.jit.base.JITConfigBuilder;
import net.multiphasicapps.squirreljme.nativecode.powerpc.PowerPCABI;
import net.multiphasicapps.squirreljme.nativecode.powerpc.PowerPCWriterFactory;

/**
 * This is the build instance for Linux PowerPC systems.
 *
 * @since 2016/09/02
 */
public class LinuxPowerPCBuildInstance
	extends LinuxBuildInstance
{
	/**
	 * Initializes the build instance.
	 *
	 * @param __conf The build configuration.
	 * @since 2016/09/02
	 */
	public LinuxPowerPCBuildInstance(BuildConfig __conf)
	{
		super(__conf, "powerpc");
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2016/09/03
	 */
	@Override
	protected void configureLinuxEmulator(EmulatorConfig __conf)
		throws IOException, NullPointerException
	{
		// Check
		if (__conf == null)
			throw new NullPointerException("NARG");
		
		throw new Error("TODO");
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2016/09/02
	 */
	@Override
	protected void modifyOutputConfig(JITConfigBuilder __conf)
	{
		// Add base Linux changes first
		super.modifyOutputConfig(__conf);
		
		// Add the native code generator to use
		__conf.setProperty(BasicOutputFactory.NATIVE_CODE_WRITER_PROPERTY,
			PowerPCWriterFactory.class.getName());
		
		// And the ABI
		__conf.setProperty(BasicOutputFactory.NATIVE_ABI_PROPERTY,
			__conf.triplet().operatingSystemVariant());
		__conf.setProperty(BasicOutputFactory.NATIVE_ABI_FACTORY_PROPERTY,
			PowerPCABI.class.getName());
	}
}

