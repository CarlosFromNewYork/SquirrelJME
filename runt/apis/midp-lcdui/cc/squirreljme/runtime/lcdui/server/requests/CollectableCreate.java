// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) Stephanie Gawroriski <xer@multiphasicapps.net>
//     Copyright (C) Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// See license.mkd for licensing and copyright information.
// ---------------------------------------------------------------------------

package cc.squirreljme.runtime.lcdui.server.requests;

import cc.squirreljme.runtime.lcdui.CollectableType;
import cc.squirreljme.runtime.lcdui.LcdFunction;
import cc.squirreljme.runtime.lcdui.server.LcdCollectable;
import cc.squirreljme.runtime.lcdui.server.LcdRequest;
import cc.squirreljme.runtime.lcdui.server.LcdServer;

/**
 * Creates a new collectable.
 *
 * @since 2018/03/23
 */
public class CollectableCreate
	extends LcdRequest
{
	/** The type of collectable to create. */
	protected final CollectableType type;
	
	/**
	 * Initializes the request.
	 *
	 * @param __sv The calling server.
	 * @throws IllegalStateException If the created widget is a display.
	 * @throws NullPointerException On null arguments.
	 * @since 2018/03/23
	 */
	public CollectableCreate(LcdServer __sv, CollectableType __type)
		throws IllegalStateException, NullPointerException
	{
		super(__sv, LcdFunction.COLLECTABLE_CREATE);
		
		if (__type == null)
			throw new NullPointerException("NARG");
		
		// {@squirreljme.error EB28 Display heads cannot be created using the
		// create collectable function.}
		if (__type == CollectableType.DISPLAY_HEAD)
			throw new IllegalStateException("EB28");
		
		this.type = __type;
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2018/03/23
	 */
	@Override
	protected final Object invoke()
	{
		return this.server.createCollectable(this.type).handle();
	}
}

