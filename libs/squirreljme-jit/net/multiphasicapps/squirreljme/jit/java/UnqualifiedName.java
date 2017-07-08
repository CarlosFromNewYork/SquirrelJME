// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) Steven Gawroriski <xer@multiphasicapps.net>
//     Copyright (C) Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// See license.mkd for licensing and copyright information.
// ---------------------------------------------------------------------------

package net.multiphasicapps.squirreljme.jit.java;

/**
 * This is a fragment of a class name .
 *
 * @since 2017/07/07
 */
public final class UnqualifiedName
	extends Identifier
{
	/**
	 * Initializes the fragment.
	 *
	 * @param __s The fragment.
	 * @since 2017/07/07
	 */
	public UnqualifiedName(String __s)
	{
		super(__s);
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2017/07/07
	 */
	@Override
	public boolean equals(Object __o)
	{
		return (__o instanceof UnqualifiedName) && super.equals(__o);
	}
}

