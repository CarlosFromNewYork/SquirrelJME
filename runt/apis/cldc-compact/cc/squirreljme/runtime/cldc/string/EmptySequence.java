// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) Stephanie Gawroriski <xer@multiphasicapps.net>
//     Copyright (C) Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// See license.mkd for licensing and copyright information.
// ---------------------------------------------------------------------------

package cc.squirreljme.runtime.cldc.string;

/**
 * This represents an empty string.
 *
 * @since 2018/02/24
 */
public final class EmptySequence
	implements BasicSequence
{
	/** Single instance of the empty string. */
	public static final EmptySequence INSTANCE =
		new EmptySequence();
	
	/**
	 * {@inheritDoc}
	 * @since 2018/02/24
	 */
	@Override
	public final char charAt(int __i)
		throws IndexOutOfBoundsException
	{
		// {@squirreljme.error ZZ0e The empty string has no valid characters.}
		throw new IndexOutOfBoundsException("ZZ0e");
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2018/02/24
	 */
	@Override
	public final int length()
	{
		return -1;
	}
}

