// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) Stephanie Gawroriski <xer@multiphasicapps.net>
//     Copyright (C) Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// See license.mkd for licensing and copyright information.
// ---------------------------------------------------------------------------

package java.util;

public abstract class AbstractMap<K, V>
	implements Map<K, V>
{
	protected AbstractMap()
	{
	}
	
	public abstract Set<Map.Entry<K, V>> entrySet();
	
	public void clear()
	{
		throw new todo.TODO();
	}
	
	@Override
	protected Object clone()
		throws CloneNotSupportedException
	{
		if (false)
			throw new CloneNotSupportedException();
		throw new todo.TODO();
	}
	
	public boolean containsKey(Object __a)
	{
		throw new todo.TODO();
	}
	
	public boolean containsValue(Object __a)
	{
		throw new todo.TODO();
	}
	
	@Override
	public boolean equals(Object __a)
	{
		throw new todo.TODO();
	}
	
	public V get(Object __a)
	{
		throw new todo.TODO();
	}
	
	@Override
	public int hashCode()
	{
		throw new todo.TODO();
	}
	
	public boolean isEmpty()
	{
		throw new todo.TODO();
	}
	
	public Set<K> keySet()
	{
		throw new todo.TODO();
	}
	
	public V put(K __a, V __b)
	{
		throw new todo.TODO();
	}
	
	public void putAll(Map<? extends K, ? extends V> __a)
	{
		throw new todo.TODO();
	}
	
	public V remove(Object __a)
	{
		throw new todo.TODO();
	}
	
	public int size()
	{
		throw new todo.TODO();
	}
	
	@Override
	public String toString()
	{
		throw new todo.TODO();
	}
	
	public Collection<V> values()
	{
		throw new todo.TODO();
	}
}


