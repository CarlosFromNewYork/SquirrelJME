// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) Stephanie Gawroriski <xer@multiphasicapps.net>
//     Copyright (C) Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// See license.mkd for licensing and copyright information.
// ---------------------------------------------------------------------------

package cc.squirreljme.runtime.lcdui.server;

import java.util.HashMap;
import java.util.Map;
import net.multiphasicapps.collections.SortedTreeMap;

/**
 * This class is the base manager for displays which are available for the
 * LCDUI subsystem which is used to contain displayables to be shown to the
 * user.
 *
 * @since 2018/03/17
 */
public abstract class LcdDisplays
{
	/** Displays which are currently available. */
	private final Map<Integer, LcdDisplay> _displays =
		new SortedTreeMap<>();
	
	/**
	 * Internally queries the displays which are present.
	 *
	 * @param __k An array containing the displays which are currently known
	 * about, this may be used to determine if any need to be re-initialized.
	 * @return An array containing the displays that are present.
	 * @throws NullPointerException On null arguments.
	 * @since 2018/03/17
	 */
	protected abstract LcdDisplay[] internalQueryDisplays(LcdDisplay[] __k)
		throws NullPointerException;
	
	/**
	 * Queries all of the displays which are available for usage.
	 *
	 * @return The displays which are available.
	 * @since 2018/03/17
	 */
	public final LcdDisplay[] queryDisplays()
	{
		// Lock displays 
		Map<Integer, LcdDisplay> displays = this._displays;
		
		// Get the displays which are known to the definition so that
		// lookup does not create any duplicates ones potentially
		LcdDisplay[] known = displays.values().<LcdDisplay>toArray(
			new LcdDisplay[displays.size()]);
		
		// Query all native displays
		LcdDisplay[] active = this.internalQueryDisplays(known);
		
		// Cache all displays by their index
		for (LcdDisplay d : active)
		{
			Integer dx = d.index();
			
			// If a display is not known about then make it known
			if (!displays.containsKey(dx))
				displays.put(dx, d);
		}
		
		// Return every display which is known about
		return displays.values().<LcdDisplay>toArray(
			new LcdDisplay[displays.size()]);
	}
}
