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

/**
 * These are flags which modify how a class is accessed and is behaved.
 *
 * @since 2016/04/23
 */
public enum ClassFlag
	implements Flag
{
	/** Public access. */
	PUBLIC,
	
	/** Final. */
	FINAL,
	
	/** Super. */
	SUPER,
	
	/** Interface. */
	INTERFACE,
	
	/** Abstract. */
	ABSTRACT,
	
	/** Synthetic. */
	SYNTHETIC,
	
	/** Annotation. */
	ANNOTATION,
	
	/** Enumeration. */
	ENUM,
	
	/** End. */
	;
}
