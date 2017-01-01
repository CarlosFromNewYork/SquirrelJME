// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) Steven Gawroriski <steven@multiphasicapps.net>
//     Copyright (C) Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// See license.mkd for licensing and copyright information.
// ---------------------------------------------------------------------------

package net.multiphasicapps.squirreljme.jit.base;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is used to build instances of {@link JITConfig} which is used
 * to configure the JIT compiler system.
 *
 * @since 2016/09/10
 */
public class JITConfigBuilder
{
	/** Internal lock. */
	protected final Object lock =
		new Object();
	
	/** Properties that are associated with the JIT, ones to configure it. */
	final Map<String, String> _properties =
		new HashMap<>();
	
	/**
	 * Builds the configuration to use.
	 *
	 * @return The resultant configuration.
	 * @throws JITException If the configuration is not valid.
	 * @since 2016/09/10
	 */
	public final JITConfig build()
		throws JITException
	{
		// Lock
		synchronized (this.lock)
		{
			return new JITConfig(this);
		}
	}
	
	/**
	 * Sets the property to the given class name.
	 *
	 * @param __k The property to set.
	 * @param __cl The class name to use.
	 * @throws NullPointerException On null arguments.
	 * @since 2016/09/28
	 */
	public final void setClass(String __k, Class<?> __cl)
		throws NullPointerException
	{
		// Check
		if (__k == null || __cl == null)
			throw new NullPointerException("NARG");
		
		// Forward
		setProperty(__k, __cl.getName());
	}
	
	/**
	 * Sets the given property for the JIT.
	 *
	 * @param __k The key to use.
	 * @param __v The value to use.
	 * @throws NullPointerException On null arguments.
	 * @since 2016/09/10
	 */
	public final void setProperty(String __k, String __v)
		throws NullPointerException
	{
		// Check
		if (__k == null || __v == null)
			throw new NullPointerException("NARG");
		
		// Lock
		synchronized (this.lock)
		{
			this._properties.put(__k, __v);
		}
	}
	
	/**
	 * Sets the triplet which is used to determine the target to JIT for.
	 *
	 * @param __t The triplet to set.
	 * @throws NullPointerException On null arguments.
	 * @since 2016/09/10
	 */
	public final void setTriplet(JITTriplet __t)
		throws NullPointerException
	{
		// Check
		if (__t == null)
			throw new NullPointerException("NARG");
		
		// Just set the property
		setProperty(JITConfig.TRIPLET_PROPERTY, __t.toString());
	}
	
	/**
	 * Returns the triplet which has currently been set in the configuration.
	 *
	 * @return The currently set triplet or {@code null} if none has been
	 * set.
	 * @since 2016/09/15
	 */
	public final JITTriplet triplet()
	{
		// Get property
		String prop;
		synchronized (this.lock)
		{
			prop = this._properties.get(JITConfig.TRIPLET_PROPERTY);
		}
		
		// None set
		if (prop == null)
			return null;
		
		// Create it
		return new JITTriplet(prop);
	}
}

