// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) 2013-2016 Steven Gawroriski <steven@multiphasicapps.net>
//     Copyright (C) 2013-2016 Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// For more information see license.mkd.
// ---------------------------------------------------------------------------

package net.multiphasicapps.squirreljme.build.projects;

import java.io.InputStream;
import java.io.IOException;
import java.util.Iterator;
import net.multiphasicapps.squirreljme.build.base.FileDirectory;
import net.multiphasicapps.zip.blockreader.ZipBlockReader;

/**
 * This is a file directory which wraps a ZIP file.
 *
 * @since 2016/12/27
 */
class __ZipFileDirectory__
	implements FileDirectory
{
	/** The ZIP to wrap. */
	protected final ZipBlockReader zip;
	
	/**
	 * Initializes the ZIP file directory.
	 *
	 * @param __zip The ZIP file to access.
	 * @throws IOException On read errors.
	 * @throws NullPointerException On null arguments.
	 * @since 2016/12/27
	 */
	__ZipFileDirectory__(ZipBlockReader __zip)
		throws IOException, NullPointerException
	{
		// Check
		if (__zip == null)
			throw new NullPointerException("NARG");
		
		// Set
		this.zip = __zip;
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2016/12/27
	 */
	@Override
	public void close()
		throws IOException
	{
		this.zip.close();
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2016/12/27
	 */
	@Override
	public boolean contains(String __fn)
		throws IOException, NullPointerException
	{
		throw new Error("TODO");
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2016/12/27
	 */
	@Override
	public Iterator<String> iterator()
	{
		throw new Error("TODO");
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2016/12/27
	 */
	@Override
	public InputStream open(String __fn)
		throws IOException, NullPointerException
	{
		throw new Error("TODO");
	}
}
