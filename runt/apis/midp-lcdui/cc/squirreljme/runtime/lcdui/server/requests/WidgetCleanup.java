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

import cc.squirreljme.runtime.lcdui.LcdFunction;
import cc.squirreljme.runtime.lcdui.server.LcdRequest;
import cc.squirreljme.runtime.lcdui.server.LcdServer;
import cc.squirreljme.runtime.lcdui.server.LcdWidget;

/**
 * Cleans up after a widget.
 *
 * @since 2018/03/23
 */
public class WidgetCleanup
	extends LcdRequest
{
	/** The widget to cleanup. */
	protected final LcdWidget cleanup;
	
	/**
	 * Initializes the request.
	 *
	 * @param __sv The calling server.
	 * @param __cl The widget to clean up.
	 * @throws NullPointerException On null arguments.
	 * @since 2018/03/23
	 */
	public WidgetCleanup(LcdServer __sv, LcdWidget __cl)
		throws NullPointerException
	{
		super(__sv, LcdFunction.WIDGET_CLEANUP);
		
		if (__cl == null)
			throw new NullPointerException("NARG");
		
		this.cleanup = __cl;
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2018/03/23
	 */
	@Override
	protected final Object invoke()
	{
		throw new todo.TODO();
	}
}
