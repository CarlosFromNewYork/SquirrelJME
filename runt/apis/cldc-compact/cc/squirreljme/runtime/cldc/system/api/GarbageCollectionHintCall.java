// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) Stephanie Gawroriski <xer@multiphasicapps.net>
//     Copyright (C) Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// See license.mkd for licensing and copyright information.
// ---------------------------------------------------------------------------

package cc.squirreljme.runtime.cldc.system.api;

import cc.squirreljme.runtime.cldc.system.SystemFunction;

/**
 * Interface for {@link SystemFunction#GARBAGE_COLLECTION_HINT}.
 *
 * @since 2018/03/14
 */
public interface GarbageCollectionHintCall
	extends Call
{
	/**
	 * Hint that garbage collection should be performed.
	 *
	 * @since 2018/03/01
	 */
	public abstract void garbageCollectionHint();
}

