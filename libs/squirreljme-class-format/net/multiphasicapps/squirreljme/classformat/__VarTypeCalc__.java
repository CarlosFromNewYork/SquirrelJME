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

import java.util.Map;
import net.multiphasicapps.io.data.ExtendedDataInputStream;

/**
 * This calculates the variable types for all positions within the byte code
 * which may be used by the JIT to perform pre-determined stack size
 * determination.
 *
 * @since 2016/09/27
 */
class __VarTypeCalc__
{
	/** The returning type array. */
	protected final int[] types;
	
	/**
	 * Initializes the variable type calculator.
	 *
	 * @param __dis The input data source.
	 * @param __cl The code length.
	 * @param __smt The stack map table.
	 * @throws NullPointerException On null arguments.
	 * @since 2016/09/28
	 */
	__VarTypeCalc__(ExtendedDataInputStream __dis, int __cl,
		Map<Integer, __SMTState__> __smt)
		throws NullPointerException
	{
		// Check
		if (__dis == null || __smt == null)
			throw new NullPointerException("NARG");
		
		// Set
		throw new todo.TODO();
	}
}

