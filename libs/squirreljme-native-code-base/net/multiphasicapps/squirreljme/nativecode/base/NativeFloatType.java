// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) 2013-2016 Steven Gawroriski <steven@multiphasicapps.net>
//     Copyright (C) 2013-2016 Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// For more information see license.mkd.
// ---------------------------------------------------------------------------

package net.multiphasicapps.squirreljme.nativecode.base;

/**
 * This represents the kind of floating point format that is used by a given
 * native machine.
 *
 * @since 2016/08/29
 */
public enum NativeFloatType
{
	/** Pure software. */
	SOFT("soft", false, false),
	
	/** 32-bit Hardware. */
	HARD32("hard32", true, false),
	
	/** 64-bit Hardware. */
	HARD64("hard64", true, true),
	
	/** End. */
	;
	
	/** The name of the floating point type. */
	protected final String name;
	
	/** Hardware 32-bit support? */
	protected final boolean hard32;
	
	/** Hardware 64-bit support? */
	protected final boolean hard64;
	
	/**
	 * Initializes the floating point name.
	 *
	 * @param __n The identifying name.
	 * @param __h32 Is 32-bit floating point in hardware?
	 * @param __h64 Is 64-bit floating point in hardware?
	 * @throws NullPointerException On null arguments.
	 * @since 2016/08/29
	 */
	private NativeFloatType(String __n, boolean __h32, boolean __h64)
		throws NullPointerException
	{
		// Check
		if (__n == null)
			throw new NullPointerException("NARG");
		
		// Set
		this.name = __n;
		this.hard32 = __h32;
		this.hard64 = __h64;
	}
	
	/**
	 * Is any hardware floating point being used?
	 *
	 * @return {@code true} if any floating point hardware is used.
	 * @since 2016/09/01
	 */
	public boolean isAnyHardware()
	{
		return this.hard32 || this.hard64;
	}
	
	/**
	 * Returns {@code true} if 32-bit floating point is implement in hardware.
	 *
	 * @return {@code true} if 32-bit floating point is implement in hardware.
	 * @since 2016/08/29
	 */
	public boolean isHardwareFloat()
	{
		return this.hard32;
	}
	
	/**
	 * Returns {@code true} if 64-bit floating point is implement in hardware.
	 *
	 * @return {@code true} if 64-bit floating point is implement in hardware.
	 * @since 2016/08/29
	 */
	public boolean isHardwareDouble()
	{
		return this.hard64;
	}
	
	/**
	 * Returns {@code true} if 32-bit floating point is implement in software.
	 *
	 * @return {@code true} if 32-bit floating point is implement in software.
	 * @since 2016/08/29
	 */
	public boolean isSoftwareFloat()
	{
		return !this.hard32;
	}
	
	/**
	 * Returns {@code true} if 64-bit floating point is implement in software.
	 *
	 * @return {@code true} if 64-bit floating point is implement in software.
	 * @since 2016/08/29
	 */
	public boolean isSoftwareDouble()
	{
		return !this.hard64;
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2016/08/29
	 */
	@Override
	public final String toString()
	{
		return this.name;
	}
	
	/**
	 * Returns the floating point type which is associated with the given
	 * string.
	 *
	 * @param __s The string to get the floating point type for.
	 * @return The floating point type associated with the given string.
	 * @throws IllegalArgumentException If the input string is not known.
	 * @since 2016/08/29
	 */
	public static NativeFloatType of(String __s)
		throws IllegalArgumentException
	{
		switch (__s)
		{
				// Software
			case "soft":
				return SOFT;
			
				// Hardware 32-bit, Software 64-bit
			case "hard32":
				return HARD32;
				
				// Hardware, Both
			case "hard64":
				return HARD64;
				
				// {@squirreljme.error BX01 Unknown floating point type.
				// (The floating point type string)}
			default:
				throw new IllegalArgumentException(String.format("BX01 %s",
					__s));
		}
	}
}
