// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) Stephanie Gawroriski <xer@multiphasicapps.net>
//     Copyright (C) Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// See license.mkd for licensing and copyright information.
// ---------------------------------------------------------------------------

package cc.squirreljme.runtime.cldc.debug;

import java.util.Objects;

/**
 * This represents a single entry within the call stack. This is used for
 * debugging purporses to determine where code has thrown an exception.
 *
 * @since 2018/02/21
 */
public final class CallTraceElement
{
	/** The class name. */
	protected final String classname;
	
	/** The method name. */
	protected final String methodname;
	
	/** The method descriptor. */
	protected final String methoddescriptor;
	
	/** The execution pointer of the address. */
	protected final long address;
	
	/**
	 * Initializes an empty call trace element.
	 *
	 * @since 2018/02/21
	 */
	public CallTraceElement()
	{
		this(null, null, null, -1);
	}
	
	/**
	 * Initializes a call trace element.
	 *
	 * @param __cl The class name.
	 * @param __mn The method name.
	 * @since 2018/02/21
	 */
	public CallTraceElement(String __cl, String __mn)
	{
		this(__cl, __mn, null, -1);
	}
	
	/**
	 * Initializes a call trace element.
	 *
	 * @param __cl The class name.
	 * @param __mn The method name.
	 * @param __md The method descriptor.
	 * @since 2018/02/21
	 */
	public CallTraceElement(String __cl, String __mn, String __md)
	{
		this(__cl, __mn, __md, -1);
	}
	
	/**
	 * Initializes a call trace element.
	 *
	 * @param __cl The class name.
	 * @param __mn The method name.
	 * @param __md The method descriptor.
	 * @param __addr The address the method executes at.
	 * @since 2018/02/21
	 */
	public CallTraceElement(String __cl, String __mn, String __md, long __addr)
	{
		this.classname = __cl;
		this.methodname = __mn;
		this.methoddescriptor = __md;
		this.address = __addr;
	}
	
	/**
	 * Returns the address of the element.
	 *
	 * @return The element address.
	 * @since 2018/03/15
	 */
	public final long address()
	{
		return this.address;
	}
	
	/**
	 * Returns the name of the associated class.
	 *
	 * @return The associated class.
	 * @since 2018/03/15
	 */
	public final String className()
	{
		return this.classname;
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2018/02/21
	 */
	@Override
	public final boolean equals(Object __o)
	{
		if (__o == this)
			return true;
		
		if (!(__o instanceof CallTraceElement))
			return false;
		
		CallTraceElement o = (CallTraceElement)__o;
		return Objects.equals(this.classname, o.classname) &&
			Objects.equals(this.methodname, o.methodname) &&
			Objects.equals(this.methoddescriptor, o.methoddescriptor) &&
			this.address == o.address;
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2018/02/21
	 */
	@Override
	public final int hashCode()
	{
		long address = this.address;
		return Objects.hashCode(this.classname) ^
			Objects.hashCode(this.methodname) ^
			Objects.hashCode(this.methoddescriptor) ^
			(int)((address >>> 32) | address);
	}
	
	/**
	 * Returns the descriptor of the method.
	 *
	 * @return The method descriptor.
	 * @since 2018/03/15
	 */
	public final String methodDescriptor()
	{
		return this.methoddescriptor;
	}
	
	/**
	 * Returns the name of the method.
	 *
	 * @return The method name.
	 * @since 2018/03/15
	 */
	public final String methodName()
	{
		return this.methodname;
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
	
	/**
	 * Decodes the given byte sequence to call trace elements.
	 *
	 * @param __b The array to decode.
	 * @param __o The offset into the array.
	 * @param __l The length of the input data.
	 * @return The resulting call trace element.
	 * @throws ArrayIndexOutOfBoundsException If the offset and/or length are
	 * negative or exceed the array bounds.
	 * @throws NullPointerException On null arguments.
	 * @since 2018/03/14
	 */
	public static final CallTraceElement[] decode(byte[] __b, int __o, int __l)
		throws ArrayIndexOutOfBoundsException, NullPointerException
	{
		if (__b == null)
			throw new NullPointerException("NARG");
		if (__o < 0 || __l < 0 || (__o + __l) > __b.length)
			throw new ArrayIndexOutOfBoundsException("IOOB");
		
		throw new todo.TODO();
	}
	
	/**
	 * Encodes all of the specified call trace elements to a byte array and
	 * returns it.
	 *
	 * @param __e The elements to encode
	 * @return The byte array containing encoded call trace information.
	 * @since 2018/03/14
	 */
	public static final byte[] encode(CallTraceElement... __el)
	{
		// Decode each sequence.
		__el = (__el == null ? new CallTraceElement[0] : __el.clone());
		for (int i = 0, n = __el.length; i < n; i++)
		{
			CallTraceElement e = __el[i];
			
			throw new todo.TODO();
		}
		
		throw new todo.TODO();
	}
}

