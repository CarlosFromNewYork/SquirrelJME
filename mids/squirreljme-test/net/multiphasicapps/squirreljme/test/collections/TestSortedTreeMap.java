// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) Steven Gawroriski <steven@multiphasicapps.net>
//     Copyright (C) Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// See license.mkd for licensing and copyright information.
// ---------------------------------------------------------------------------

package net.multiphasicapps.squirreljme.test.collections;

import java.util.Map;
import net.multiphasicapps.squirreljme.test.TestResult;
import net.multiphasicapps.util.sorted.SortedTreeMap;

/**
 * This tests the sorted tree map to see if it operates correctly.
 *
 * @since 2017/03/28
 */
public class TestSortedTreeMap
	extends __BaseMap__
{
	/**
	 * Initializes the test.
	 *
	 * @since 2017/03/28
	 */
	public TestSortedTreeMap()
	{
		super(true, new SortedTreeMap<String, String>());
	}
}
