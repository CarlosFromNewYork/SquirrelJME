// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) Steven Gawroriski <steven@multiphasicapps.net>
//     Copyright (C) Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// See license.mkd for licensing and copyright information.
// ---------------------------------------------------------------------------

package net.multiphasicapps.squirreljme.jit.bin;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * This class contains all of the dynamics which are used in any of the sections
 * to refer to information (such as method addresses) which are not yet
 * available. This class manages those dynamic requests for information.
 *
 * @since 2017/06/15
 */
public class Dynamics
{
	/** The linker state which contains the dynamic table.  */
	protected final Reference<LinkerState> linkerstate;
	
	/**
	 * Initializes the dynamic table.
	 *
	 * @param __ls The reference to the owning linker state.
	 * @throws NullPointerException On null arguments.
	 * @since 2017/06/15
	 */
	Dynamics(Reference<LinkerState> __ls)
		throws NullPointerException
	{
		// Check
		if (__ls == null)
			throw new NullPointerException("NARG");
		
		// Set
		this.linkerstate = __ls;
		
		throw new todo.TODO();
	}
}
