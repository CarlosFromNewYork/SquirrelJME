// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) Stephanie Gawroriski <xer@multiphasicapps.net>
//     Copyright (C) Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// See license.mkd for licensing and copyright information.
// ---------------------------------------------------------------------------

package cc.squirreljme.builder.support;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import cc.squirreljme.jit.input.JITInput;
import cc.squirreljme.jit.input.JITInputGroup;
import cc.squirreljme.jit.input.VerifiedJITInput;
import net.multiphasicapps.zip.streamreader.ZipStreamReader;

/**
 * This class is used to specify the files and projects which are to be
 * compiled as input for the JIT with produced output for targets.
 *
 * This pipe is just told which projects should be JIT compiled, when those
 * projects are obtained they will be returned from the compiler.
 *
 * @since 2017/10/30
 */
public class JITPipe
{
	/** Binary project manager. */
	protected final BinaryManager binaries;
	
	/** The input binaries to be used in the JIT. */
	private final Set<Binary> _input =
		new HashSet<>();
	
	/**
	 * Initializes the JIT pipe.
	 *
	 * @param __bins Binary manager.
	 * @throws NullPointerException On null arguments.
	 * @since 2017/10/31
	 */
	public JITPipe(BinaryManager __bins)
		throws NullPointerException
	{
		if (__bins == null)
			throw new NullPointerException("NARG");
		
		this.binaries = __bins;
	}
	
	/**
	 * Adds the given input to the JIT, it is either interpreted as a path
	 * or the name of a project.
	 *
	 * @param __s The path or source to add.
	 * @throws NullPointerException On null arguments.
	 * @since 2017/10/31
	 */
	public void addInput(String __s)
		throws NullPointerException
	{
		if (__s == null)
			throw new NullPointerException("NARG");
		
		Path p = Paths.get(__s);
		if (Files.exists(p))
			addInput(p);
		addInput(new SourceName(__s));
	}
	
	/**
	 * Adds the specified path to a JAR to be used as input for the JIT, this
	 * allows out of tree binaries to be included as input.
	 *
	 * @param __p The path to the file to use as input.
	 * @throws NullPointerException On null arguments.
	 * @since 2017/10/31
	 */
	public void addInput(Path __p)
		throws NullPointerException
	{
		if (__p == null)
			throw new NullPointerException("NARG");
		
		__addInput(this.binaries.getVirtual(__p));
	}
	
	/**
	 * Adds the specified project to act as input for the JIT.
	 *
	 * @param __b The project name to add.
	 * @throws NullPointerException On null arguments.
	 * @since 2017/10/31
	 */
	public void addInput(SourceName __b)
		throws NullPointerException
	{
		if (__b == null)
			throw new NullPointerException("NARG");
		
		__addInput(this.binaries.get(__b));
	}
	
	/**
	 * Verifies the JIT input to determine if it is correct.
	 *
	 * @return The verified JIT input.
	 * @throws IOException On read errors.
	 * @since 2017/10/31
	 */
	public VerifiedJITInput verify()
		throws IOException
	{
		// Obtain the set of binaries which will be used as the input for the
		// JIT
		Binary[] binaries = __binaries();
		
		// Load all binaries into groups
		int n = binaries.length;
		JITInputGroup[] groups = new JITInputGroup[n];
		for (int i = 0; i < n; i++)
		{
			Binary binary = binaries[i];
			
			try (ZipStreamReader z = binary.zipStream())
			{
				groups[i] = JITInputGroup.readZip(binary.name().toString(), z);
			}
		}
		
		// Setup input for the JIT
		JITInput input = new JITInput(groups);
		
		// Verify the result in the JIT
		return VerifiedJITInput.verify(input);
	}
	
	/**
	 * Adds the given input binary to the pipe for JIT compilation.
	 *
	 * @param __b The binary project to add.
	 * @throws NullPointerException On null arguments.
	 * @since 2017/10/31
	 */
	private void __addInput(Binary __b)
		throws NullPointerException
	{
		if (__b == null)
			throw new NullPointerException("NARG");
		
		// Lock
		Set<Binary> input = this._input;
		synchronized (input)
		{
			input.add(__b);
		}
	}
	
	/**
	 * Goes through all of the input, compiles all of the binaries which are
	 * required to run the binary.
	 *
	 * @return The compiled set of binaries in compilation and dependency
	 * order.
	 * @since 2017/11/02
	 */
	private Binary[] __binaries()
	{
		// Add all input binaries to the queue
		Deque<Binary> queue;
		Collection<Binary> input = this._input;
		synchronized (input)
		{
			queue = new ArrayDeque<>(input);
		}
		
		// All the binaries must be compiled before they can be used
		BinaryManager binaries = this.binaries;
		Set<Binary> rv = new LinkedHashSet<>();
		while (!queue.isEmpty())
			for (Binary b : binaries.compile(queue.removeFirst()))
				rv.add(b);
		
		// Return the set
		return rv.<Binary>toArray(new Binary[rv.size()]);
	}
}
