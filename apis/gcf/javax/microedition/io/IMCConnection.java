// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) 2013-2016 Steven Gawroriski <steven@multiphasicapps.net>
//     Copyright (C) 2013-2016 Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// For more information see license.mkd.
// ---------------------------------------------------------------------------

package javax.microedition.io;

import javax.microedition.midlet.MIDletIdentity;

public interface IMCConnection
	extends StreamConnection
{
	public abstract MIDletIdentity getRemoteIdentity();
	
	public abstract String getRequestedServerVersion();
	
	public abstract String getServerName();
}

