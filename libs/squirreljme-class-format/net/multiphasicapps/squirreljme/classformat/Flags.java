// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) 2013-2016 Steven Gawroriski <steven@multiphasicapps.net>
//     Copyright (C) 2013-2016 Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// For more information see license.mkd.
// ---------------------------------------------------------------------------

package net.multiphasicapps.squirreljme.classformat;

import java.util.AbstractSet;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import net.multiphasicapps.util.unmodifiable.UnmodifiableSet;

/**
 * This is the base class for all flag collections.
 *
 * @param <F> The flag type.
 * @since 2016/04/23
 */
public abstract class Flags<F extends Flag>
	extends AbstractSet<F>
{
	/** The class type to use. */
	protected final Class<F> cast;
	
	/** The set ordinals. */
	protected final int setbits;
	
	/** The slower access set. */
	protected final Set<F> flags;
	
	/**
	 * Initializes the flag set.
	 *
	 * @param __cl The class type of the flag.
	 * @param __fl The input flags.
	 * @throws NullPointerException On null arguments.
	 * @since 2016/04/23
	 */
	Flags(Class<F> __cl, F[] __fl)
		throws NullPointerException
	{
		this(__cl, Arrays.<F>asList(__fl));
	}
	
	/**
	 * Initializes the flag set.
	 *
	 * @param __cl The class type of the flag.
	 * @param __fl The input flags.
	 * @throws NullPointerException On null arguments.
	 * @since 2016/04/23
	 */
	Flags(Class<F> __cl, Iterable<F> __fl)
	{
		// Check
		if (__cl == null || __fl == null)
			throw new NullPointerException("NARG");
		
		// Set
		cast = __cl;
		
		// Go through all input flags
		Set<F> to = new HashSet<>();
		int bits = 0;
		for (F f : __fl)
		{
			// Get ordinal
			int o = __cl.cast(f).ordinal();
			
			// Set it
			bits |= (1 << o);
			
			// Add to flag set
			to.add(f);
		}
		
		// Lock in
		setbits = bits;
		flags = UnmodifiableSet.<F>of(to);
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2016/04/23
	 */
	@Override
	public final boolean contains(Object __o)
	{
		// Quick bit check?
		if (cast.isInstance(__o))
			return 0 != (setbits & (1 << (((Flag)__o).ordinal())));
		
		// Fallback
		return flags.contains(__o);
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2016/04/23
	 */
	@Override
	public final Iterator<F> iterator()
	{
		return flags.iterator();
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2016/04/23
	 */
	@Override
	public final int size()
	{
		return flags.size();
	}
}
