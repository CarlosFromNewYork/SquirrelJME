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
import java.util.List;
import java.util.Set;
import net.multiphasicapps.squirreljme.build.projects.ProjectManager;
import net.multiphasicapps.squirreljme.kernel.InvalidSuiteException;
import net.multiphasicapps.squirreljme.kernel.KernelLaunchParameters;
import net.multiphasicapps.squirreljme.kernel.KernelSuiteManager;
import net.multiphasicapps.squirreljme.kernel.KernelThreadListener;
import net.multiphasicapps.squirreljme.kernel.KernelThreadManager;
import net.multiphasicapps.squirreljme.kernel.SystemInstalledSuites;
import net.multiphasicapps.squirreljme.kernel.UserInstalledSuites;
import net.multiphasicapps.squirreljme.suiteid.APIConfiguration;
import net.multiphasicapps.squirreljme.suiteid.APIProfile;
import net.multiphasicapps.squirreljme.suiteid.APIStandard;

/**
 * This is the base class for the interpreter kernel managers, since the two
 * kernel managers will generally share some code while have some other
 * differences this is used to keep the common code together.
 *
 * @since 2016/11/02
 */
public abstract class AbstractKernelManager
	implements KernelLaunchParameters, KernelSuiteManager, KernelThreadManager
{
	/** Internal lock. */
	protected final Object lock =
		new Object();
	
	/** The owning auto interpreter. */
	protected final AutoInterpreter autointerpreter;
	
	/** The thread listener to use. */
	private volatile KernelThreadListener _threadlistener;
	
	/** System suites. */
	private volatile SystemInstalledSuites _syssuites;
	
	/**
	 * Initializes the base abstract kernel manager.
	 *
	 * @param __ai The auto interpreter owning this.
	 * @throws NullPointerException On null arguments.
	 * @since 2016/11/02
	 */
	public AbstractKernelManager(AutoInterpreter __ai)
		throws NullPointerException
	{
		// Check
		if (__ai == null)
			throw new NullPointerException("NARG");
		
		// Set
		this.autointerpreter = __ai;
	}
	
	/**
	 * Returns the launch parameters to actually use.
	 *
	 * @return The launch parameters to use.
	 * @since 2016/10/07
	 */
	protected abstract KernelLaunchParameters internalLaunchParameters();
	
	/**
	 * {@inheritDoc}
	 * @since 2016/11/08
	 */
	@Override
	public final String[] getCommandLine()
	{
		return internalLaunchParameters().getCommandLine();
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2016/11/08
	 */
	@Override
	public final String getSystemProperty(String __k)
	{
		return internalLaunchParameters().getSystemProperty(__k);
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2016/11/02
	 */
	@Override
	public final void setThreadListener(KernelThreadListener __t)
		throws NullPointerException
	{
		// Check
		if (__t == null)
			throw new NullPointerException("NARG");
		
		// Set
		this._threadlistener = __t;
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2016/12/16
	 */
	@Override
	public final SystemInstalledSuites systemSuites()
	{
		// Lock
		synchronized (this.lock)
		{
			SystemInstalledSuites rv = this._syssuites;
			if (rv == null)
				this._syssuites = (rv = new InterpreterSystemSuites(
					this.autointerpreter, internalLaunchParameters()));
			return rv;
		}
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2016/12/16
	 */
	@Override
	public final UserInstalledSuites userSuites()
	{
		throw new Error("TODO");
	}
}
