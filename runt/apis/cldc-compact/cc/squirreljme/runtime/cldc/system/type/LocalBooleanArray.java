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

/**
 * Wraps a boolean array.
 *
 * @since 2018/03/04
 */
public class LocalBooleanArray
	implements BooleanArray, LocalArray
{
	/** The boolean array to read/write. */
	protected final boolean[] array;
	
	/**
	 * Initializes the local boolean array.
	 *
	 * @param __a The array to wrap.
	 * @throws NullPointerException On null arguments.
	 * @since 2018/03/01
	 */
	public LocalBooleanArray(boolean[] __a)
		throws NullPointerException
	{
		if (__a == null)
			throw new NullPointerException("NARG");
		
		this.array = __a;
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2018/03/04
	 */
	@Override
	public final boolean get(int __i)
		throws ArrayIndexOutOfBoundsException
	{
		boolean[] array = this.array;
		if (__i < 0 || __i >= array.length)
			throw new ArrayIndexOutOfBoundsException("IOOB");
		
		return array[__i];
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2018/03/04
	 */
	@Override
	public final void get(int __i, boolean[] __v, int __o, int __l)
		throws ArrayIndexOutOfBoundsException, NullPointerException
	{
		if (__v == null)
			throw new NullPointerException("NARG");
		
		boolean[] array = this.array;
		if (__o < 0 || __l < 0 || (__o + __l) > __v.length ||
			__i < 0 || (__i + __l) > array.length)
			throw new ArrayIndexOutOfBoundsException("IOOB");
		
		for (int c = 0, i = __i, o = __o; c < __l; c++, i++, o++)
			__v[o] = array[i];
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2018/03/04
	 */
	@Override
	public final int length()
	{
		return this.array.length;
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2018/03/04
	 */
	@Override
	public final boolean[] localArray()
	{
		return this.array;
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2018/03/04
	 */
	@Override
	public final void set(int __i, boolean __v)
		throws ArrayIndexOutOfBoundsException
	{
		boolean[] array = this.array;
		if (__i < 0 || __i >= array.length)
			throw new ArrayIndexOutOfBoundsException("IOOB");
		
		array[__i] = __v;
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2018/03/04
	 */
	@Override
	public final void set(int __i, boolean[] __v, int __o, int __l)
		throws ArrayIndexOutOfBoundsException, NullPointerException
	{
		if (__v == null)
			throw new NullPointerException("NARG");
		
		boolean[] array = this.array;
		if (__o < 0 || __l < 0 || (__o + __l) > __v.length ||
			__i < 0 || (__i + __l) > array.length)
			throw new ArrayIndexOutOfBoundsException("IOOB");
		
		for (int c = 0, i = __i, o = __o; c < __l; c++, i++, o++)
			array[i] = __v[o];
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2018/03/18
	 */
	@Override
	public final ArrayType type()
	{
		return ArrayType.BOOLEAN;
	}
}

