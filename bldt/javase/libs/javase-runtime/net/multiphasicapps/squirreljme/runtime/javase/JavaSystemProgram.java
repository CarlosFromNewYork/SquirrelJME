// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) Stephanie Gawroriski <xer@multiphasicapps.net>
//     Copyright (C) Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// See license.mkd for licensing and copyright information.
// ---------------------------------------------------------------------------

package net.multiphasicapps.squirreljme.runtime.javase;

import java.io.InputStream;
import java.nio.file.Paths;
import net.multiphasicapps.squirreljme.runtime.cldc.SystemProgramType;
import net.multiphasicapps.squirreljme.runtime.kernel.KernelProgram;

/**
 * This is a program which represents the system itself.
 *
 * @since 2017/12/27
 */
public class JavaSystemProgram
	extends JavaProgram
{
	/**
	 * Initializes the system program.
	 *
	 * @since 2017/12/27
	 */
	public JavaSystemProgram()
	{
		super(0, Paths.get(System.getProperty(
			"net.multiphasicapps.squirreljme.runtime.javase.bootpath",
			"sjmeboot.jar")));
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2017/12/31
	 */
	@Override
	protected String accessControlGet(String __k)
		throws NullPointerException
	{
		if (__k == null)
			throw new NullPointerException("NARG");
		
		return null;
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2017/12/31
	 */
	@Override
	protected void accessControlSet(String __k, String __v)
		throws NullPointerException
	{
		if (__k == null)
			throw new NullPointerException("NARG");
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2017/12/28
	 */
	@Override
	protected InputStream accessLoadResource(String __name)
		throws NullPointerException
	{
		if (__name == null)
			throw new NullPointerException("NARG");
		
		if (__name.equals("META-INF/MANIFEST.MF"))
			return JavaSystemProgram.class.getResourceAsStream("SYSTEM.MF");
		return null;
	}
}

