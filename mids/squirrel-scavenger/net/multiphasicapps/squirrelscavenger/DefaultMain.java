// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) Steven Gawroriski <steven@multiphasicapps.net>
//     Copyright (C) Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// See license.mkd for licensing and copyright information.
// ---------------------------------------------------------------------------

package net.multiphasicapps.squirrelscavenger;

import java.nio.file.Paths;
import net.multiphasicapps.squirrelscavenger.game.FrameTimer;
import net.multiphasicapps.squirrelscavenger.game.Game;
import net.multiphasicapps.squirrelscavenger.game.player.Controller;
import net.multiphasicapps.squirrelscavenger.gui.GUI;
import net.multiphasicapps.squirrelscavenger.gui.lcdui.LCDUIGUI;
import net.multiphasicapps.squirrelscavenger.gui.lui.LUIGUI;

/**
 * This is the classical main entry point.
 *
 * @since 2016/10/06
 */
public class DefaultMain
{
	/**
	 * Main entry point.
	 *
	 * @param __args Program arguments.
	 * @since 2016/10/06
	 */
	public static void main(String... __args)
	{
		// Force to exist
		if (__args == null)
			__args = new String[0];
		
		// Initialize the game
		Game g = g = new Game(0L, Paths.get(System.getProperty("user.dir")));
		
		// Use console based interface or the GUI one?
		GUI lg;
		if (__args.length > 0 && "-c".equals(__args[0]))
			lg = new LUIGUI();
		
		// Default to the LCD UI or otherwise on unknown arguments
		else
			lg = new LCDUIGUI(null);
		
		// Setup controllers
		for (int i = 0; i < Integer.MAX_VALUE; i++)
		{
			Controller c = lg.localController(i);
			
			// No local players anymore
			if (c == null)
				break;
			
			// Add player and set controller
			g.addPlayer().setController(c);
		}
		
		// Execute loop
		new FrameTimer(g, lg).run();
	}
}

