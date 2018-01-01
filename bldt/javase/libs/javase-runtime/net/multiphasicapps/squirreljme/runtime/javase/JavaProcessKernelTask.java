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

import net.multiphasicapps.squirreljme.runtime.cldc.SystemTaskStatus;
import net.multiphasicapps.squirreljme.runtime.kernel.KernelTask;

/**
 * This represents a task which is provided by a standard process.
 *
 * @since 2017/12/11
 */
public final class JavaProcessKernelTask
	extends KernelTask
{
	/** The process to watch. */
	protected final Process process;
	
	/** Has the sub-process fully booted? */
	private volatile boolean _booted;
	
	/**
	 * Initializes the process task.
	 *
	 * @param __dx The task index.
	 * @since 2017/12/27
	 */
	public JavaProcessKernelTask(int __dx, Process __proc)
		throws NullPointerException
	{
		super(__dx);
		
		if (__proc == null)
			throw new NullPointerException("NARG");
		
		this.process = __proc;
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2017/12/27
	 */
	@Override
	protected int accessFlags()
	{
		int rv = 0;
		
		// If there is an exit value then set status according to failure or
		// success
		try
		{
			int ev = this.process.exitValue();
			if (this._booted)
				if (ev == 0)
					rv |= SystemTaskStatus.EXITED_REGULAR;
				else
					rv |= SystemTaskStatus.EXITED_FATAL;
			
			// If there is an exit code and the boot flag was never set then
			// start failed
			else
				rv |= SystemTaskStatus.START_FAILED;
		}
		
		// Has not terminated yet, so assume it is running
		catch (IllegalThreadStateException e)
		{
			// This flag is set when the client sends the boot response
			// to the kernel IPC
			if (this._booted)
				rv |= SystemTaskStatus.RUNNING;
			
			// Otherwise it sits at starting until that happens.
			else
				rv |= SystemTaskStatus.STARTING;
		}
		
		return rv;
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2017/12/27
	 */
	@Override
	protected long accessMetric(int __m)
	{
		throw new todo.TODO();
	}
}

