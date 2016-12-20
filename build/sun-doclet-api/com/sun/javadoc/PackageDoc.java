// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) 2013-2016 Steven Gawroriski <steven@multiphasicapps.net>
//     Copyright (C) 2013-2016 Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// For more information see license.mkd.
// ---------------------------------------------------------------------------

package com.sun.javadoc;

public interface PackageDoc
	extends Doc
{
	public abstract ClassDoc[] allClasses(boolean __a);
	
	public abstract ClassDoc[] allClasses();
	
	public abstract AnnotationTypeDoc[] annotationTypes();
	
	public abstract AnnotationDesc[] annotations();
	
	public abstract ClassDoc[] enums();
	
	public abstract ClassDoc[] errors();
	
	public abstract ClassDoc[] exceptions();
	
	public abstract ClassDoc findClass(String __a);
	
	public abstract ClassDoc[] interfaces();
	
	public abstract ClassDoc[] ordinaryClasses();
}

