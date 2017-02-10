// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) Steven Gawroriski <steven@multiphasicapps.net>
//     Copyright (C) Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// See license.mkd for licensing and copyright information.
// ---------------------------------------------------------------------------

package net.multiphasicapps.squirreljme.build.host.javase;

import java.awt.BasicStroke;
import java.awt.Color;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.Text;
import net.multiphasicapps.squirreljme.lcdui.BasicGraphics;

/**
 * This wraps the AWT graphics to the one the LCDUI code uses and needs to
 * draw with.
 *
 * @since 2017/02/08
 */
public class AWTGraphicsAdapter
	extends BasicGraphics
{
	/** Dotted line. */
	private static final BasicStroke _DOTTED_STROKE =
		new BasicStroke(1.0F, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
			0.0F, new float[]{1.0F}, 0.0F);
	
	/** Solid line. */
	private static final BasicStroke _SOLID_STROKE =
		new BasicStroke(1.0F, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
	
	/** Wrapped AWT graphics (where things go to). */
	volatile java.awt.Graphics2D _awt;
	
	/**
	 * {@inheritDoc}
	 * @since 2017/02/10
	 */
	@Override
	protected void primitiveHorizontalLine(int __x, int __y,
		int __w, int __color, boolean __dotted, boolean __blend)
	{
		throw new Error("TODO");
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2017/02/10
	 */
	@Override
	protected void primitiveLine(int __x1, int __y1, int __x2,
		int __y2, int __color, boolean __dotted, boolean __blend)
	{
		throw new Error("TODO");
	}
}

