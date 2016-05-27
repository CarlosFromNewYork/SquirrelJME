// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) 2013-2016 Steven Gawroriski <steven@multiphasicapps.net>
//     Copyright (C) 2013-2016 Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU Affero General Public License v3+, or later.
// For more information see license.mkd.
// ---------------------------------------------------------------------------

package net.multiphasicapps.squirreljme.ci;

/**
 * This represents the flags which a class may be.
 *
 * @since 2016/04/23
 */
public class CIClassFlags
	extends CIFlags<CIClassFlag>
	implements CIAccessibleFlags
{
	/**
	 * Initializes the class flags.
	 *
	 * @param __fl The class flags.
	 * @since 2016/04/23
	 */
	public CIClassFlags(CIClassFlag... __fl)
	{
		super(CIClassFlag.class, __fl);
		
		__checkFlags();
	}
	
	/**
	 * Initializes the class flags.
	 *
	 * @param __fl The class flags.
	 * @since 2016/04/23
	 */
	public CIClassFlags(Iterable<CIClassFlag> __fl)
	{
		super(CIClassFlag.class, __fl);
		
		__checkFlags();
	}
	
	/**
	 * Is this class abstract?
	 *
	 * @return {@code true} if it is abstract.
	 * @since 2016/03/15
	 */
	public final boolean isAbstract()
	{
		return contains(CIClassFlag.ABSTRACT);
	}
	
	/**
	 * Is this an annotation?
	 *
	 * @return {@code true} if it is an annotation.
	 * @since 2016/03/15
	 */
	public final boolean isAnnotation()
	{
		return contains(CIClassFlag.ANNOTATION);
	}
	
	/**
	 * Is this an enumeration?
	 *
	 * @return {@code true} if it is an enumeration.
	 * @since 2016/03/15
	 */
	public final boolean isEnum()
	{
		return contains(CIClassFlag.ENUM);
	}
	
	/**
	 * Is this class final?
	 *
	 * @return {@code true} if it is final.
	 * @since 2016/03/15
	 */
	public final boolean isFinal()
	{
		return contains(CIClassFlag.FINAL);
	}
	
	/**
	 * Is this an interface?
	 *
	 * @return {@code true} if an interface.
	 * @since 2016/03/15
	 */
	public final boolean isInterface()
	{
		return contains(CIClassFlag.INTERFACE);
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2016/03/15
	 */
	@Override
	public final boolean isPackagePrivate()
	{
		return !isPublic();
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2016/05/12
	 */
	@Override
	public final boolean isPrivate()
	{
		// Classes are never private
		return false;
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2016/05/12
	 */
	@Override
	public final boolean isProtected()
	{
		// Classes are never protected
		return false;
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2016/03/15
	 */
	@Override
	public final boolean isPublic()
	{
		return contains(CIClassFlag.PUBLIC);
	}
	
	/**
	 * Is there special handling for super-class method calls?
	 *
	 * @return {@code true} if the super-class invocation special flag is set.
	 * @since 2016/03/15
	 */
	public final boolean isSpecialInvokeSpecial()
	{
		return contains(CIClassFlag.SUPER);
	}
	
	/**
	 * Checks that the given flags are valid.
	 *
	 * @throws CIException If they are not valid.
	 * @since 2016/04/23
	 */
	private final void __checkFlags()
		throws CIException
	{
		// Interface?
		if (isInterface())
		{
			// {@squirreljme.error AO03 An interface must also be abstract.
			// (The class flags}}
			if (!isAbstract())
				throw new CIException(String.format("AO03 %s", this));
			
			// {@squirreljme.error AO04 An interface cannot be {@code final} or
			// {@code enum} and it must not have the special flag set. (The
			// class flags)}
			if (isFinal() || isSpecialInvokeSpecial() || isEnum())
				throw new CIException(String.format("AO04 %s", this));
		}
		
		// Normal class
		else
		{
			// {@squirreljme.error AO05 Annotations must be interfaces. (The
			// class flags)}
			if (isAnnotation())
				throw new CIException(String.format("AO05 %s", this));
				
			// {@squirreljme.error AO06 A class cannot be both {@code abstract}
			// and {@code final}. (The class flags)}
			if (isAbstract() && isFinal())
				throw new CIException(String.format("AO06 %s", this));
		}
	}
}

