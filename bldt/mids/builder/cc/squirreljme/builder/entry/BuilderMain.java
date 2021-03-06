// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) Stephanie Gawroriski <xer@multiphasicapps.net>
//     Copyright (C) Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// See license.mkd for licensing and copyright information.
// ---------------------------------------------------------------------------

package cc.squirreljme.builder.entry;

import cc.squirreljme.builder.support.BuilderFactory;
import java.io.PrintStream;

/**
 * Main entry point for the builder.
 *
 * @since 2017/11/09
 */
public class BuilderMain
{
	/**
	 * Main entry point.
	 *
	 * @param __args Program arguments.
	 * @since 2017/11/09
	 */
	public static void main(String... __args)
	{
		// Print some basic information about the build system
		PrintStream out = System.err;
		out.println("SquirrelJME Build System");
		
		// Space
		out.println();
		
		// Run the builder
		new BuilderFactory(__args).run();
	}
}

