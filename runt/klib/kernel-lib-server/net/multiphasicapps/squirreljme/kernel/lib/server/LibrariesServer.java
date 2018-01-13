// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) Stephanie Gawroriski <xer@multiphasicapps.net>
//     Copyright (C) Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// See license.mkd for licensing and copyright information.
// ---------------------------------------------------------------------------

package net.multiphasicapps.squirreljme.kernel.lib.server;

import net.multiphasicapps.squirreljme.kernel.lib.client.Library;
import net.multiphasicapps.squirreljme.kernel.lib.client.PacketTypes;
import net.multiphasicapps.squirreljme.kernel.packets.Packet;
import net.multiphasicapps.squirreljme.kernel.service.ServerInstance;
import net.multiphasicapps.squirreljme.kernel.service.ServicePacketStream;
import net.multiphasicapps.squirreljme.runtime.cldc.SystemTask;
import net.multiphasicapps.squirreljme.runtime.cldc.SystemTrustGroup;

/**
 * This manages communication between the client process and the library
 * server.
 *
 * @since 2018/01/05
 */
public final class LibrariesServer
	extends ServerInstance
{
	/** The class where permissions are checked against. */
	private static final String _PERMISSION_CLASS =
		"javax.microedition.swm.SWMPermission";
	
	/** The library server since libraries are managed in unison. */
	protected final LibrariesProvider provider;
	
	/**
	 * Initializes the library instance.
	 *
	 * @param __task The task which has the instance open.
	 * @param __stream The stream for communicating with the task.
	 * @param __p The provider for the actual library service.
	 * @throws NullPointerException On null arguments.
	 * @since 2018/01/05
	 */
	public LibrariesServer(SystemTask __task, ServicePacketStream __stream,
		LibrariesProvider __p)
		throws NullPointerException
	{
		super(__task, __stream);
		
		if (__p == null)
			throw new NullPointerException("NARG");
		
		this.provider = __p;
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2018/01/05
	 */
	@Override
	public Packet handlePacket(Packet __p)
		throws NullPointerException
	{
		if (__p == null)
			throw new NullPointerException("NARG");
		
		switch (__p.type())
		{
			case PacketTypes.LIST_PROGRAMS:
				return this.__list(__p);
			
				// {@squirreljme.error BC02 Unknown packet. (The packet)}
			default:
				throw new IllegalArgumentException(
					String.format("BC02 %s", __p));
		}
	}
	
	/**
	 * Returns the list of libraries which are available.
	 *
	 * @return The library list.
	 * @throws NullPointerException On null arguments.
	 * @since 2018/01/06
	 */
	private final Packet __list(Packet __p)
		throws NullPointerException
	{
		if (__p == null)
			throw new NullPointerException("NARG");
			
		SystemTask task = this.task;
		SystemTrustGroup mygroup = task.trustGroup();
		
		// Need permission to do this
		LibrariesAccessMode accessmode = __accessMode("manageSuite");
		
		// Read the library set
		Library[] libs = this.provider.list(__p.readInteger(0));
		
		// The response is just the library identifiers
		int n = libs.length;
		Packet rv = __p.respond();
		
		// Write the library indexes
		int counted = 0;
		for (int i = 0, o = 2; i < n; i++, o += 2)
		{
			Library lib = libs[i];
			SystemTrustGroup libgroup = lib.trustGroup();
			
			// If the library belongs to another group
			if (accessmode.isAccessible(mygroup, libgroup))
				rv.writeInteger(o, libs[i].index());
		}
		
		// Write library count
		rv.writeShort(0, Math.min(counted, 65535));
		
		return rv;
	}
	
	/**
	 * Returns the access mode for the current task which specifies which
	 * libraries it may access.
	 *
	 * @param __act The action to check.
	 * @return The permitted access mode.
	 * @throws NullPointerException On null arguments.
	 * @since 2019/01/11
	 */
	private final LibrariesAccessMode __accessMode(String __act)
		throws NullPointerException
	{
		if (__act == null)
			throw new NullPointerException("NARG");
		
		SystemTask task = this.task;
		
		// "crossClient" is the most important and it will for the most part
		// imply "client" because it would be rather pointless if a program
		// could modify other clients but not its own
		try
		{
			task.checkPermission(LibrariesServer._PERMISSION_CLASS,
				"crossClient", __act);
			
			return LibrariesAccessMode.ANY;
		}
		
		// Cannot access other clients, try in our same trust group
		catch (SecurityException e)
		{
			// This can also fail
			try
			{
				task.checkPermission(LibrariesServer._PERMISSION_CLASS,
					"client", __act);
				
				return LibrariesAccessMode.SAME_GROUP;
			}
			
			// No access at all
			catch (SecurityException f)
			{
				return LibrariesAccessMode.NONE;
			}
		}
	}
}
