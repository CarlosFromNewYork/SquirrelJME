// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) Stephanie Gawroriski <xer@multiphasicapps.net>
//     Copyright (C) Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// See license.mkd for licensing and copyright information.
// ---------------------------------------------------------------------------

package cc.squirreljme.interpreter;

import cc.squirreljme.jit.cff.ClassName;
import cc.squirreljme.jit.cff.MethodDescriptor;
import cc.squirreljme.jit.cff.MethodName;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import net.multiphasicapps.collections.SortedTreeMap;

/**
 * This contains the interpreter which executes Java byte code in a contained
 * environment which is built for SquirrelJME.
 *
 * @since 2017/10/05
 */
public class Interpreter
	implements Runnable
{
	/** The lock for the global state in the virtual machine. */
	final Object _lock =
		new Object();
	
	/** The boot class. */
	protected final ClassName bootclass;
	
	/** Extra system properties. */
	private Map<String, String> _properties;
	
	/** Processes within the virtual machine. */
	private final List<VMProcess> _processes =
		new ArrayList<>();
	
	/** The next ID for new threads. */
	private volatile int _nextthreadid;
	
	/**
	 * Initializes the interpreter to run the given program.
	 *
	 * @param __rom The ROM to interpret.
	 * @param __props The system properties to use.
	 * @param __boot The MIDlet to enter for execution.
	 * @throws NullPointerException On null arguments.
	 * @since 2017/10/05
	 */
	public Interpreter(byte[] __rom, Map<String, String> __props,
		String __boot)
		throws NullPointerException
	{
		if (__rom == null || __props == null || __boot == null)
			throw new NullPointerException("NARG");
		
		// Set input
		if (true)
			throw new todo.TODO();
		
		// Copy properties
		Map<String, String> properties = new SortedTreeMap<>();
		properties.putAll(__props);
		this._properties = properties;
		
		// Set starting point
		this.bootclass = new ClassName(__boot.replace('.', '/'));
		
		throw new todo.TODO();
	}
	
	/**
	 * Creates a new process and returns it.
	 *
	 * @return The newly created process.
	 * @since 2017/10/08
	 */
	public VMProcess createProcess()
	{
		List<VMProcess> processes = this._processes;
		synchronized (this._lock)
		{
			VMProcess rv = new VMProcess(new WeakReference<>(this));
			
			processes.add(rv);
			
			return rv;
		}
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2017/10/06
	 */
	@Override
	public void run()
	{
		// Setup main thread, construct/init an object in it and then set
		// the entry point to startApp().
		VMProcess mainprocess = createProcess();
		VMThread mainthread = mainprocess.createThread();
		
		// Create a new instance and construct it
		ClassInstance classobj = mainthread.classInstance(this.bootclass);
		ClassMethod defcon = classobj.getDefaultConstructor();
		Instance bootobj = mainthread.allocateInstance(classobj);
		mainthread.invoke(defcon, bootobj);
		
		// Run the main thread in this thread, like magic!
		ClassMethod startapp = classobj.getVirtualMethod(bootobj,
			new MethodName("startApp"), new MethodDescriptor("()V"));
		mainthread.invoke(startapp, bootobj);
	}
	
	/**
	 * Returns the next thread ID.
	 *
	 * @return The next thread ID.
	 * @since 2017/10/08
	 */
	final int __nextThreadId()
	{
		synchronized (this._lock)
		{
			return ++this._nextthreadid;
		}
	}
}

