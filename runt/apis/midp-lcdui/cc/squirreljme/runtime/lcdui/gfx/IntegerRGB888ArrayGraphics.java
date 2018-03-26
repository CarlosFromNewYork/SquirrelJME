// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) Stephanie Gawroriski <xer@multiphasicapps.net>
//     Copyright (C) Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// See license.mkd for licensing and copyright information.
// ---------------------------------------------------------------------------

package cc.squirreljme.runtime.lcdui.gfx;

/**
 * 32-bit RGB 888 Graphics.
 *
 * @since 2018/03/25
 */
public final class IntegerRGB888ArrayGraphics
	extends AbstractRGBArrayGraphics
{
	/** The array buffer. */
	protected final int[] buffer;
	
	/**
	 * Initializes the graphics.
	 *
	 * @param __buf The buffer.
	 * @param __w The image width.
	 * @param __h The image height.
	 * @param __p The image pitch.
	 * @param __o The buffer offset.
	 * @throws IllegalArgumentException If the input parameters are not
	 * correct.
	 * @throws NullPointerException On null arguments.
	 * @since 2018/03/25
	 */
	public IntegerRGB888ArrayGraphics(int[] __buf,
		int __w, int __h, int __p, int __o)
		throws IllegalArgumentException, NullPointerException
	{
		super(__w, __h, __p, __o, __buf.length, 1, false,
			0b00000000_00000000__00000000_00000000,
			0b00000000_11111111__00000000_00000000,
			0b00000000_00000000__11111111_00000000,
			0b00000000_00000000__00000000_11111111);
		
		if (__buf == null)
			throw new NullPointerException("NARG");
		
		this.buffer = __buf;
		
		// Use default settings
		this.resetParameters(true);
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2018/03/25
	 */
	@Override
	protected final void internalFillRectBlend(int __x, int __y, int __ex,
		int __ey, int __w, int __h)
	{
		int[] buffer = this.buffer;
		
		int pac = this.paintcolor,
			sa = this.paintalpha,
			na = (sa ^ 0xFF),
			srb = ((pac & 0xFF00FF) * sa),
			sgg = ((pac & 0x00FF00) * sa);
		
		// Blend each color
		int mod = 0;
		boolean did = false;
		for (int y = __y; y < __ey; y++)
			for (int dest = offset + (y * pitch) + __x, pex = dest + __w;
				dest < pex; dest++)
			{
				int dcc = buffer[dest];
				
				// Quick
				int xrb = srb + (((dcc & 0xFF00FF) * na) >>> 8),
					xgg = sgg + (((dcc & 0x00FF00) * na) >>> 8);
				int ra = ((xrb & 0xFF00FF) | (xgg & 0x00FF00));
				
				// Slower
				int sr = (pac >>> 16) & 0xFF,
					sg = (pac >>> 8) & 0xFF,
					sb = (pac) & 0xFF,
					dr = (dcc >>> 16) & 0xFF,
					dg = (dcc >>> 8) & 0xFF,
					db = (dcc) & 0xFF;
				int xr = ((sr * sa) + (dr * na)) / 255,
					xg = ((sg * sa) + (dg * na)) / 255,
					xb = ((sb * sa) + (db * na)) / 255;
				int rb = (xr << 16) | (xg << 8) | xb;
				
				// Identity
				int rc = __blend(pac | 0xFF000000, dcc | 0xFF000000, 0xFF,
					sa) & 0xFFFFFF;
				
				
				buffer[dest] = (mod == 0 ? ra : (mod == 1 ? rb : rc));
				mod++;
				if (mod >= 3)
					mod = 0;
				
				if (!did && (ra != rb || ra != rc))
				{
					did = true;
					System.err.printf("DEBUG -- %02x%06x > ff%06x == (ra=%06x, rb=%06x, rc=%06x)%n",
						sa, pac, dcc, ra, rb, rc);
				}
			}
		/*
		buffer[dest] = (__blend(pac, dpp, 0xFF000000, a)) & 0xFFFFFF
		
		int sa = (((__src >> 24) & 0xFF) * __alpha) / 255;
		
		// Split into RGB
		int sr = (__src >> 16) & 0xFF,
			sg = (__src >> 8) & 0xFF,
			sb = (__src) & 0xFF,
			da = ((__dest >> 24) & 0xFF) | __bor,
			dr = (__dest >> 16) & 0xFF,
			dg = (__dest >> 8) & 0xFF,
			db = (__dest) & 0xFF;
	
		// Difference of alpha values
		int qq = 255 - sa;
		
		// Perform blending
		int xa = (sa + da - ((sa * da) / 255)) | __bor,
			xr = ((sr * sa) + (dr * qq)) / 255,
			xg = ((sg * sa) + (dg * qq)) / 255,
			xb = ((sb * sa) + (db * qq)) / 255;
	
		// Recompile
		return (xa << 24) | (xr << 16) | (xg << 8) | xb;
		*/
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2018/03/25
	 */
	@Override
	protected final void internalFillRectSolid(int __x, int __y, int __ex,
		int __ey, int __w, int __h)
	{
		int b = this.paintcolor;
		int[] buffer = this.buffer;
		int pitch = this.pitch,
			offset = this.offset;
		
		// Just a simple color fill
		for (int y = __y; y < __ey; y++)
			for (int dest = offset + (y * pitch) + __x, pex = dest + __w;
				dest < pex; dest++)
				buffer[dest] = b;
	}
	
	/**
	 * Blends two colors.
	 *
	 * @param __src The source color.
	 * @param __dest The destination color.
	 * @param __bor The blended OR value on the destination.
	 * @param __alpha Alpha value which modifies the source.
	 * @return The resulting blended color.
	 * @since 2017/02/12
	 */
	private static final int __blend(int __src, int __dest, int __bor,
		int __alpha)
	{
		int sa = (((__src >> 24) & 0xFF) * __alpha) / 255;
		
		// Split into RGB
		int sr = (__src >> 16) & 0xFF,
			sg = (__src >> 8) & 0xFF,
			sb = (__src) & 0xFF,
			da = ((__dest >> 24) & 0xFF) | __bor,
			dr = (__dest >> 16) & 0xFF,
			dg = (__dest >> 8) & 0xFF,
			db = (__dest) & 0xFF;
	
		// Difference of alpha values
		int qq = 255 - sa;
		
		// Perform blending
		int xa = ((sa + da - ((sa * da) / 255)) | __bor) & 0xFF,
			xr = (((sr * sa) + (dr * qq)) / 255) & 0xFF,
			xg = (((sg * sa) + (dg * qq)) / 255) & 0xFF,
			xb = (((sb * sa) + (db * qq)) / 255) & 0xFF;
	
		// Recompile
		return (xa << 24) | (xr << 16) | (xg << 8) | xb;
	}
}

