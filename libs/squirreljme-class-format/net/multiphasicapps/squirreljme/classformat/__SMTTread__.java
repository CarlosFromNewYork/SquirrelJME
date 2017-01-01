// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) Steven Gawroriski <steven@multiphasicapps.net>
//     Copyright (C) Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// See license.mkd for licensing and copyright information.
// ---------------------------------------------------------------------------

package net.multiphasicapps.squirreljme.classformat;

import java.util.AbstractList;
import java.util.RandomAccess;

/**
 * This is the base class for stack and local verification type storage.
 *
 * @since 2016/08/28
 */
abstract class __SMTTread__
	extends AbstractList<StackMapType>
	implements RandomAccess
{
	/** The number of entries in the tread. */
	protected final int count;
	
	/** The variable storage area. */
	protected final StackMapType[] storage;
	
	/**
	 * Initializes the base tread.
	 *
	 * @param __n The number of entries.
	 * @since 2016/05/12
	 */
	__SMTTread__(int __n)
	{
		// Initialize
		this.count = __n;
		this.storage = new StackMapType[__n];
	}
	
	/**
	 * Initializes the basic tread information from another tread.
	 *
	 * @param __t The tread to base off.
	 * @throws NullPointerException On null arguments.
	 * @since 2016/08/29
	 */
	protected __SMTTread__(__SMTTread__ __t)
		throws NullPointerException
	{
		// Check
		if (__t == null)
			throw new NullPointerException("NARG");
		
		// Set
		int n = __t.count;
		this.count = n;
		
		// Copy storage
		StackMapType[] from = __t.storage;
		StackMapType[] storage = new StackMapType[n];
		this.storage = storage;
		for (int i = 0; i < n; i++)
			storage[i] = from[i];
	}
	
	/**
	 * Copies state from another state.
	 *
	 * @param __o The state to copy from.
	 * @throws NullPointerException On null arguments.
	 * @since 2016/09/04
	 */
	public void from(__SMTTread__ __o)
		throws NullPointerException
	{
		// Check
		if (__o == null)
			throw new NullPointerException("NARG");
		
		// Copy all variables
		int n = this.count;
		for (int i = 0; i < n; i++)
			set(i, __o.get(i));
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2016/05/12
	 */
	@Override
	public final StackMapType get(int __i)
	{
		// Always make sure there is a value here
		StackMapType rv = storage[__i];
		if (rv == null)
			return StackMapType.NOTHING;
		return rv;
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2016/08/29
	 */
	@Override
	public final StackMapType set(int __i, StackMapType __t)
	{
		// Check
		if (__t == null)
			throw new NullPointerException("NARG");
		
		StackMapType[] storage = this.storage;
		StackMapType rv = storage[__i];
		storage[__i] = __t;
		return rv;
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2016/05/12
	 */
	@Override
	public final int size()
	{
		return count;
	}
}

