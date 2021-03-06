// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) Stephanie Gawroriski <xer@multiphasicapps.net>
//     Copyright (C) Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// See license.mkd for licensing and copyright information.
// ---------------------------------------------------------------------------

package cc.squirreljme.runtime.cldc.system.type;

import cc.squirreljme.runtime.cldc.system.EasyCall;
import cc.squirreljme.runtime.cldc.system.SystemCall;

/**
 * This represents an enumerated type which is passed through system calls.
 * This class is intended to allow the enumerated type to be passed across
 * VMs.
 *
 * @since 2018/03/14
 */
public final class EnumType
{
	/** The class this enumeration is in. */
	protected final ClassType inclass;
	
	/** The ordinal of this enumeration. */
	protected final int ordinal;
	
	/** The name of the element. */
	protected final String name;
	
	/**
	 * Initializes the enumerated type.
	 *
	 * @param __c The class type of the enumeration.
	 * @param __dx The ordinal of the enumeration.
	 * @param __n The name of the value.
	 * @throws IllegalArgumentException If the index is negative.
	 * @throws NullPointerException On null arguments.
	 * @since 2018/03/14
	 */
	public EnumType(String __c, int __dx, String __n)
		throws IllegalArgumentException, NullPointerException
	{
		this(new ClassType(__c), __dx, __n);
	}
	
	/**
	 * Initializes the enumerated type.
	 *
	 * @param __c The class type of the enumeration.
	 * @param __dx The ordinal of the enumeration.
	 * @param __n The name of the value.
	 * @throws IllegalArgumentException If the index is negative.
	 * @throws NullPointerException On null arguments.
	 * @since 2018/03/14
	 */
	public EnumType(ClassType __c, int __dx, String __n)
		throws IllegalArgumentException, NullPointerException
	{
		// {@squirreljme.error ZZ0l An enumeration cannot have a negative
		// ordinal.}
		if (__dx < 0)
			throw new IllegalArgumentException("ZZ0l");
		if (__c == null || __n == null)
			throw new NullPointerException("NARG");
		
		this.inclass = __c;
		this.ordinal = __dx;
		this.name = __n;
	}
	
	/**
	 * Returns this enumeration as the given class type.
	 *
	 * @param <E> The type of enumeration to return.
	 * @param __cl The type of enumeration to return.
	 * @return The enumerated value.
	 * @throws ClassCastException If the class type is not correct.
	 * @throws IllegalArgumentException If the specified enumeration constant
	 * is not valid.
	 * @throws NullPointerException On null arguments.
	 * @since 2018/03/16
	 */
	public final <E extends Enum<E>> E asEnum(Class<E> __cl)
		throws ClassCastException, IllegalArgumentException,
			NullPointerException
	{
		if (__cl == null)
			throw new NullPointerException("NARG");
		
		// {@squirreljme.error ZZ0r Cannot cast the enumeration to the given
		// class because it does not match. (The enumeration class; The passed
		// class)}
		ClassType inclass = this.inclass;
		if (!inclass.name().equals(__cl.getName()))
			throw new ClassCastException(String.format("ZZ0r %s %s",
				inclass, __cl));
		
		// See if there is a direct ordinal match
		String name = this.name;
		E[] vals = SystemCall.EASY.<E>classEnumConstants(__cl);
		int ordinal = this.ordinal, n = vals.length;
		if (ordinal >= 0 && ordinal < n)
		{
			E rv = vals[ordinal];
			if (name.equals(rv.name()))
				return rv;
		}
		
		// Find the enumeration constants
		return SystemCall.EASY.<E>classEnumValueOf(__cl, name);
	}
	
	/**
	 * Returns the class type of the enumeration.
	 *
	 * @return The class type of the enumeration.
	 * @since 2018/03/14
	 */
	public final ClassType classType()
	{
		return this.inclass;
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2018/03/14
	 */
	@Override
	public final boolean equals(Object __o)
	{
		throw new todo.TODO();
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2018/03/14
	 */
	@Override
	public final int hashCode()
	{
		throw new todo.TODO();
	}
	
	/**
	 * Returns the name of the enumeration.
	 *
	 * @return The enumeration name.
	 * @since 2018/03/14
	 */
	public final String name()
	{
		return this.name;
	}
	
	/**
	 * Returns the ordinal of the enumeration.
	 *
	 * @return The enumeration ordinal.
	 * @since 2018/03/14
	 */
	public final int ordinal()
	{
		return this.ordinal;
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2018/03/14
	 */
	@Override
	public final String toString()
	{
		throw new todo.TODO();
	}
}

