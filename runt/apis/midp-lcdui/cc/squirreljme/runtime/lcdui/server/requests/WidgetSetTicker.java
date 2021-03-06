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

import cc.squirreljme.runtime.cldc.system.type.VoidType;
import cc.squirreljme.runtime.lcdui.LcdFunction;
import cc.squirreljme.runtime.lcdui.server.LcdRequest;
import cc.squirreljme.runtime.lcdui.server.LcdServer;
import cc.squirreljme.runtime.lcdui.server.LcdTicker;
import cc.squirreljme.runtime.lcdui.server.LcdWidget;

/**
 * Sets the ticker of a widget.
 *
 * @since 2018/03/26
 */
public class WidgetSetTicker
	extends LcdRequest
{
	/** The widget to set the ticker for. */
	protected final LcdWidget widget;
	
	/** The ticker to set. */
	protected final LcdTicker ticker;
	
	/**
	 * Initializes the request.
	 *
	 * @param __sv The calling server.
	 * @param __w The widget to set the ticker for.
	 * @param __t The ticker to set.
	 * @throws NullPointerException On null arguments, except for {@code __t}.
	 * @since 2018/03/26
	 */
	public WidgetSetTicker(LcdServer __sv, LcdWidget __w, LcdTicker __t)
		throws NullPointerException
	{
		super(__sv, LcdFunction.WIDGET_SET_TITLE);
		
		if (__w == null)
			throw new NullPointerException("NARG");
		
		this.widget = __w;
		this.ticker = __t;
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2018/03/26
	 */
	@Override
	protected final Object invoke()
	{
		this.widget.setTicker(this.ticker);
		return VoidType.INSTANCE;
	}
}

