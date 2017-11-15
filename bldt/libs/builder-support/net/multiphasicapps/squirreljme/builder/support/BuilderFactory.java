// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) Stephanie Gawroriski <xer@multiphasicapps.net>
//     Copyright (C) Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// See license.mkd for licensing and copyright information.
// ---------------------------------------------------------------------------

package net.multiphasicapps.squirreljme.builder.support;

/**
 * This is a factory which can invoke the build system using a common set
 * of input arguments.
 *
 * @since 2017/11/09
 */
public class BuilderFactory
	implements Runnable
{
	/** The command to execute. */
	protected final String command;
	
	/** Arguments to the builder command. */
	private final String[] _args;
	
	/**
	 * Initializes the build factory.
	 *
	 * @param __args Program arguments.
	 * @throws IllegalArgumentException If the factory arguments are missing
	 * the primary command.
	 * @since 2017/11/09
	 */
	public BuilderFactory(String... __args)
		throws IllegalArgumentException
	{
		// Copy arguments so they are not messed up
		__args = (__args != null ? __args.clone() : new String[0]);
		
		// 
		this.command = null;
		if (true)
			throw new todo.TODO();
		
		this._args = __args;
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2017/11/09
	 */
	@Override
	public void run()
	{
		throw new todo.TODO();
	}
}
