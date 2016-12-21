// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) 2013-2016 Steven Gawroriski <steven@multiphasicapps.net>
//     Copyright (C) 2013-2016 Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// For more information see license.mkd.
// ---------------------------------------------------------------------------

package jdk.dio;

import java.security.Permission;
import java.security.PermissionCollection;

public abstract class DevicePermission
	extends Permission
{
	public static final String OPEN =
		"open";
	
	public static final String POWER_MANAGE =
		"powermanage";
	
	public DevicePermission(String __a)
	{
		super((String)null);
		throw new Error("TODO");
	}
	
	public DevicePermission(String __a, String __b)
	{
		super((String)null);
		throw new Error("TODO");
	}
	
	public boolean equals(Object __a)
	{
		throw new Error("TODO");
	}
	
	public String getActions()
	{
		throw new Error("TODO");
	}
	
	public int hashCode()
	{
		throw new Error("TODO");
	}
	
	public boolean implies(Permission __a)
	{
		throw new Error("TODO");
	}
	
	public PermissionCollection newPermissionCollection()
	{
		throw new Error("TODO");
	}
	
	@Override
	public String toString()
	{
		throw new Error("TODO");
	}
}

