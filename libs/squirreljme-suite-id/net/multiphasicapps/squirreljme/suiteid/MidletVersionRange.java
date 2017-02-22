// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) Steven Gawroriski <steven@multiphasicapps.net>
//     Copyright (C) Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// See license.mkd for licensing and copyright information.
// ---------------------------------------------------------------------------

package net.multiphasicapps.squirreljme.suiteid;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * This is used to handle version ranges that may be used for dependencies.
 *
 * Version ranges are inclusive.
 *
 * @since 2017/02/22
 */
public final class MidletVersionRange
{
	/** The starting range, inclusive. */
	protected final MidletVersion from;
	
	/** Tne ending range, inclusive. */
	protected final MidletVersion to;
	
	/** String representation. */
	private volatile Reference<String> _string;
	
	/**
	 * Initializes the range inclusively between the two given versions.
	 *
	 * @param __from The source version.
	 * @param __to The destination version.
	 * @throws NullPointerException On null arguments.
	 * @since 2017/02/22
	 */
	public MidletVersionRange(MidletVersion __from, MidletVersion __to)
		throws NullPointerException
	{
		// Check
		if (__from == null || __to == null)
			throw new NullPointerException("NARG");
		
		// Make sure from is always first
		if (__from.compareTo(__to) <= 0)
		{
			this.from = __from;
			this.to = __to;
		}
		
		// Swapped
		else
		{
			this.from = __to;
			this.to = __from;
		}
	}
	
	/**
	 * Parses the version range that is specified in the dependency of JAR
	 * files.
	 *
	 * @param __s The string to parse.
	 * @throws IllegalArgumentException If the range is not valid.
	 * @throws NullPointerException On null arguments.
	 * @since 2017/02/22
	 */
	public MidletVersionRange(String __s)
		throws IllegalArgumentException, NullPointerException
	{
		// Check
		if (__s == null)
			throw new NullPointerException("NARG");
		
		// Trim
		__s = __s.trim();
		
		// {@squirreljme.error CC0f The version range cannot be blank.}
		int sl = __s.length();
		if (sl <= 0)
			throw new IllegalArgumentException("CC0f");
		
		// Get the last character
		char lc = __s.charAt(__s.length() - 1);
		
		// All versions following this.
		if (lc == '+')
		{
			this.from = new MidletVersion(__s.substring(0, sl - 1));
			this.to = new MidletVersion(99, 99, 99);
		}
		
		// All versions in the group
		else if (lc == '*')
		{
			// Get the last dot, if any
			int ld = __s.lastIndexOf('.');
			if (ld < 0)
			{
				// Any version, does not matter
				if (sl == 1)
				{
					this.from = new MidletVersion(0);
					this.to = new MidletVersion(99, 99, 99);
				}
				
				// {@squirreljme.error CC0g Major only wildcard versions must
				// be a single asterisk. (The input string)}
				else
					throw new IllegalArgumentException(String.format("CC0g %s",
						__s));
			}
			
			// Parse otherwise, just count the number of dots to determine
			// how deep it goes
			else
			{
				// {@squirreljme.error CC0h The last dot in a wildcard must be
				// before the asterisk. (The input string)}
				if (ld != sl - 1)
					throw new IllegalArgumentException(String.format("CC0h %s",
						__s));
				
				// Source range is simple
				MidletVersion ver = new MidletVersion(
					__s.substring(0, sl - 2));
				this.from = ver;
				
				// Count dots, determines major/minor
				int numdots = 0;
				for (int i = 0; i < sl; i++)
					if (__s.charAt(i) == '.')
						numdots++;
				
				// minor and release wildcard
				if (numdots == 1)
					this.to = new MidletVersion(ver.major(), 99, 99);
				
				// release ranged wildcard
				else if (numdots == 2)
					this.to = new MidletVersion(ver.major(), ver.minor(), 99);
				
				// {@squirreljme.error CC0i There are too many decimal points
				// in the wildcard version string. (The input string)}
				else
					throw new IllegalArgumentException(String.format("CC0i %s",
						__s));
			}
		}
		
		// Only this version
		else
		{
			MidletVersion ver = new MidletVersion(__s);
			this.from = ver;
			this.to = ver;
		}
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2017/02/22
	 */
	@Override
	public boolean equals(Object __o)
	{
		// Check
		if (!(__o instanceof MidletVersionRange))
			return false;
		
		// Compare
		MidletVersionRange o = (MidletVersionRange)__o;
		return this.from.equals(o.from) && this.to.equals(o.to);
	}
	
	/**
	 * Returns the start of the range
	 *
	 * @return The range start.
	 * @since 2017/02/22
	 */
	public MidletVersion from()
	{
		return this.from;
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2017/02/22
	 */
	@Override
	public int hashCode()
	{
		return this.to.hashCode() ^ (~this.from.hashCode());
	}
	
	/**
	 * Checks whether the specified version is in range.
	 *
	 * @param __v The version to check.
	 * @return {@code true} if it is in the range.
	 * @throws NullPointerException On null arguments.
	 * @since 2017/02/22
	 */
	public boolean inRange(MidletVersion __v)
		throws NullPointerException
	{
		// Check
		if (__v == null)
			throw new NullPointerException("NARG");
		
		return this.from.compareTo(__v) <= 0 &&
			this.to.compareTo(__v) >= 0;
	}
	
	/**
	 * Returns the end of the range.
	 *
	 * @return The range end.
	 * @since 2017/02/22
	 */
	public MidletVersion to()
	{
		return this.to;
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2017/02/22
	 */
	@Override
	public String toString()
	{
		throw new Error("TODO");
	}
}

