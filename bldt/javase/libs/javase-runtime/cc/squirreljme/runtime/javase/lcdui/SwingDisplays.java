// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) Stephanie Gawroriski <xer@multiphasicapps.net>
//     Copyright (C) Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// See license.mkd for licensing and copyright information.
// ---------------------------------------------------------------------------

package cc.squirreljme.runtime.javase.lcdui;

import cc.squirreljme.runtime.lcdui.server.LcdDisplay;
import cc.squirreljme.runtime.lcdui.server.LcdDisplays;

/**
 * This provides a single display that uses the Swing widget system for
 * displaying graphics.
 *
 * @since 2018/03/17
 */
public class SwingDisplays
	extends LcdDisplays
{
	/**
	 * {@inheritDoc}
	 * @since 2018/03/17
	 */
	@Override
	protected LcdDisplay[] internalQueryDisplays(LcdDisplay[] __k)
		throws NullPointerException
	{
		if (__k == null)
			throw new NullPointerException("NARG");
		
		// Swing only uses a single display which is shared among all
		// programs
		if (__k.length == 0)
			return new LcdDisplay[]{new SwingDisplay(0)};
		return __k;
	}
}
