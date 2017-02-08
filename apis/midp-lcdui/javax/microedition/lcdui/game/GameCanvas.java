// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) Steven Gawroriski <steven@multiphasicapps.net>
//     Copyright (C) Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// See license.mkd for licensing and copyright information.
// ---------------------------------------------------------------------------

package javax.microedition.lcdui.game;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public abstract class GameCanvas
	extends Canvas
{
	public static final int DOWN_PRESSED =
		64;
	
	public static final int FIRE_PRESSED =
		256;
	
	public static final int GAME_A_PRESSED =
		512;
	
	public static final int GAME_B_PRESSED =
		1024;
	
	public static final int GAME_C_PRESSED =
		2048;
	
	public static final int GAME_D_PRESSED =
		4096;
	
	public static final int LEFT_PRESSED =
		4;
	
	public static final int RIGHT_PRESSED =
		32;
	
	public static final int UP_PRESSED =
		2;
	
	/** Are game keys being suppressed?. */
	private volatile boolean _suppressgamekeys;
	
	/** Is the buffer preserved after a flush? */
	private volatile boolean _preservebuffer;
	
	/** The image to draw onto as a double buffer. */
	private volatile Image _image;
	
	/**
	 * Initializes the game canvas.
	 *
	 * The buffer is preserved by default.
	 *
	 * @param __supke If {@code true} then game key events are suppressed.
	 * @since 2016/10/08
	 */
	protected GameCanvas(boolean __supke)
	{
		this(__supke, true);
	}
	
	/**
	 * Initializes the game canvas.
	 *
	 * It may be an optimization if key events are suppressed if a canvas is
	 * only needed to draw and not receive user input. The only key events
	 * that are suppressed are game keys.
	 *
	 * @param __supke If {@code true} then game key events are suppressed.
	 * @param __preservebuf If {@code true} then the buffer is preserved after
	 * a flush, otherwise if {@code false} the buffer data will be undefined.
	 * @see Canvas
	 * @since 2016/10/08
	 */
	protected GameCanvas(boolean __supke, boolean __preservebuf)
	{
		// Set
		this._suppressgamekeys = __supke;
		this._preservebuffer = __preservebuf;
	}
	
	public void flushGraphics()
	{
		throw new Error("TODO");
	}
	
	public void flushGraphics(int __a, int __b, int __c, int __d)
	{
		throw new Error("TODO");
	}
	
	/**
	 * This returns the off-screen buffer that is used by the game canvas to
	 * draw.
	 *
	 * @return The graphics object for the off-screen buffer.
	 * @since 2016/10/10
	 */
	protected Graphics getGraphics()
	{
		throw new Error("TODO");
	}
	
	public int getKeyStates()
	{
		throw new Error("TODO");
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2016/10/10
	 */
	@Override
	public void paint(Graphics __a)
	{
		// The default implementation of this method just takes the offscreen
		// buffer and renders it to the canvas
		Image image = this._image;
		if (image == null)
			return;
		
		throw new Error("TODO");
	}
}


