// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) Steven Gawroriski <steven@multiphasicapps.net>
//     Copyright (C) Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// See license.mkd for licensing and copyright information.
// ---------------------------------------------------------------------------

package net.multiphasicapps.squirreljme.build.interpreter;

import java.io.InputStream;
import net.multiphasicapps.squirreljme.build.projects.ProjectBinary;
import net.multiphasicapps.squirreljme.kernel.ExecutableClass;
import net.multiphasicapps.squirreljme.kernel.ExecutableLoadException;
import net.multiphasicapps.squirreljme.kernel.SuiteDataAccessor;

/**
 * This is used to access the classes and resources in a project via a
 * uniform interface.
 *
 * @since 2017/01/03
 */
class __ProjectAccessor__
	extends SuiteDataAccessor
{
	/** The project to wrap. */
	protected final ProjectBinary project;
	
	/**
	 * Initializes the project accessor.
	 *
	 * @param __p The project to wrap.
	 * @throws NullPointerException On null arguments.
	 * @since 2017/01/03
	 */
	__ProjectAccessor__(ProjectBinary __p)
		throws NullPointerException
	{
		// Check
		if (__p == null)
			throw new NullPointerException("NARG");
		
		// Set
		this.project = __p;
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2017/01/03
	 */
	@Override
	public ExecutableClass loadClass(String __name)
		throws ExecutableLoadException, NullPointerException
	{
		// Check
		if (__name == null)
			throw new NullPointerException("NARG");
		
		throw new Error("TODO");
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2017/01/03
	 */
	@Override
	public InputStream loadResource(String __name)
		throws NullPointerException
	{
		// Check
		if (__name == null)
			throw new NullPointerException("NARG");
		
		throw new Error("TODO");
	}
}
