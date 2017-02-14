// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) Steven Gawroriski <steven@multiphasicapps.net>
//     Copyright (C) Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// See license.mkd for licensing and copyright information.
// ---------------------------------------------------------------------------

package net.multiphasicapps.squirreljme.lcdui;

/**
 * This is used on connectors which are capable of receiving pointer events.
 *
 * @since 2017/02/12
 */
public interface PointerEventCapableConnector
{
	/**
	 * Informs the canvas that a pointer event has occurred.
	 *
	 * @param __t The type of event.
	 * @param __x The X coordinate of the pointer.
	 * @param __y The Y coordinate of the pointer.
	 * @throws NullPointerException On null arguments.
	 * @since 2017/02/12
	 */
	public abstract void pointerEvent(PointerEventType __t, int __x, int __y)
		throws NullPointerException;
}
