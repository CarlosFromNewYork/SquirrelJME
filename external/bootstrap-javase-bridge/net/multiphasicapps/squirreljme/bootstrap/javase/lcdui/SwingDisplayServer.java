// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) 2013-2016 Steven Gawroriski <steven@multiphasicapps.net>
//     Copyright (C) 2013-2016 Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// For more information see license.mkd.
// ---------------------------------------------------------------------------

package net.multiphasicapps.squirreljme.bootstrap.javase.lcdui;

import java.io.IOException;
import javax.microedition.io.StreamConnection;
import net.multiphasicapps.squirreljme.lcdui.server.DisplayClient;
import net.multiphasicapps.squirreljme.lcdui.server.DisplayServer;

/**
 * This implements the display server which uses Swing as its backing
 * widget system.
 *
 * @since 2016/10/11
 */
public class SwingDisplayServer
	extends DisplayServer
{
	/**
	 * Initializes the swing display server.
	 *
	 * @throws IOException On read/write errors.
	 * @since 2016/10/11
	 */
	public SwingDisplayServer()
		throws IOException
	{
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2016/10/15
	 */
	@Override
	protected DisplayClient createClient(StreamConnection __sc)
		throws IOException, NullPointerException
	{
		return new SwingDisplayClient(this, __sc);
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2016/10/11
	 */
	@Override
	protected void modifyThread(Thread __t)
	{
		// Make the display server thread a daemon so it is not considered
		// as a user thread
		if (__t != null)
			__t.setDaemon(true);
	}
}

