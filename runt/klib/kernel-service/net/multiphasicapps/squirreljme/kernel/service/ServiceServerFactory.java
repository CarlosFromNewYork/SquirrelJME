// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) Stephanie Gawroriski <xer@multiphasicapps.net>
//     Copyright (C) Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// See license.mkd for licensing and copyright information.
// ---------------------------------------------------------------------------

package net.multiphasicapps.squirreljme.kernel.service;

/**
 * This class is used to initialize service servers which are used as a host
 * interface when interacting with client services.
 *
 * The kernel uses this with the service loader to initialize services.
 *
 * @since 2018/01/03
 */
public interface ServiceServerFactory
{
	/**
	 * Creates the service server which will be used by clients.
	 *
	 * @return The newly created service server.
	 * @since 2018/01/03
	 */
	public abstract ServiceServer createServer();
}
