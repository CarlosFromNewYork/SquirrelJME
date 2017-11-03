// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) Stephanie Gawroriski <xer@multiphasicapps.net>
//     Copyright (C) Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// See license.mkd for licensing and copyright information.
// ---------------------------------------------------------------------------

package net.multiphasicapps.squirreljme.build.project;

import java.lang.ref.Reference;
import java.nio.file.Files;
import java.nio.file.Path;
import net.multiphasicapps.zip.streamreader.ZipStreamReader;

/**
 * This class represents a binary which has been loaded by the binary manager.
 *
 * @since 2017/10/31
 */
public final class Binary
{
	/** The name of this binary. */
	protected final SourceName name;
	
	/** The source code for this binary, may be null if there is none. */
	protected final Source source;
	
	/** The path to the binary for this executable. */
	protected final Path path;
	
	/** Reference to the owning binary manager, used for dependencies. */
	private final Reference<BinaryManager> _managerref;
	
	/**
	 * Initializes the binary.
	 *
	 * @param __ref The reference to the binary manager, to find other binaries
	 * such as for dependencies.
	 * @param __name The name of this binary.
	 * @param __source The source of this binary, may be {@code null} if there
	 * is no source.
	 * @
	 */
	Binary(Reference<BinaryManager> __ref, SourceName __name, Source __source,
		Path __path)
		throws NullPointerException
	{
		if (__ref == null || __name == null || __path == null)
			throw new NullPointerException("NARG");
		
		// Set
		this._managerref = __ref;
		this.name = __name;
		this.source = __source;
		this.path = __path;
	}
	
	/**
	 * Returns all binaries which are dependencies of this project, this
	 * includes any binaries which are recursively depended upon.
	 *
	 * @return The dependencies for this binary.
	 * @since 2017/11/02
	 */
	public final Binary[] dependencies()
	{
		throw new todo.TODO();
	}
	
	/**
	 * Returns the name of the project.
	 *
	 * @return The project name.
	 * @since 2017/11/02
	 */
	public final SourceName name()
	{
		return this.name;
	}
	
	/**
	 * Opens the binary as a ZIP file for reading the contents.
	 *
	 * @return The stream over the ZIP's contents.
	 * @since 2017/11/02
	 */
	public final ZipStreamReader zipStream()
	{
		throw new todo.TODO();
	}
}
