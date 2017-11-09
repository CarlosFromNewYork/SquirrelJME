// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) Stephanie Gawroriski <xer@multiphasicapps.net>
//     Copyright (C) Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// See license.mkd for licensing and copyright information.
// ---------------------------------------------------------------------------

package net.multiphasicapps.squirreljme.unsafe;

import java.io.InputStream;

/**
 * This class contains static methods which are used by the standard class
 * library code to perform special system and virtual machine related
 * functions. These methods are specific to SquirrelJME and using them will
 * cause your code to not be compatible with other virtual machines.
 *
 * @see __Ext_systemvm__
 * @since 2017/08/10
 */
public final class SystemVM
{
	/** Private access. */
	public static final int ACCESS_PRIVATE =
		0;
	
	/** Package private access. */
	public static final int ACCESS_PACKAGE_PRIVATE =
		1;
	
	/** Protected access. */
	public static final int ACCESS_PROTECTED =
		2;
	
	/** Public access. */
	public static final int ACCESS_PUBLIC =
		3;
	
	/**
	 * Not used.
	 *
	 * @since 2017/08/10
	 */
	private SystemVM()
	{
	}
	
	/**
	 * Creates a new instance of the given class.
	 *
	 * @param <C> The class to allocate.
	 * @param __cl The class to allocate.
	 * @return The allocated class.
	 * @throws IllegalAccessException If the class cannot be accessed from the
	 * calling class.
	 * @throws NullPointerException On null arguments.
	 * @since 2017/03/30
	 */
	public static <C> C allocateInstance(Class<C> __cl)
		throws IllegalAccessException, NullPointerException
	{
		// Check
		if (__cl == null)
			throw new NullPointerException("NARG");
		
		throw new todo.TODO();
	}
	
	/**
	 * This throws an index out of bounds exception.
	 *
	 * @param __i The out of bounds index.
	 * @param __l The length of the array.
	 * @throws ArrayIndexOutOfBoundsException Always.
	 * @since 2017/05/13
	 */
	public static void arrayIndexOutOfBounds(int __i, int __l)
		throws ArrayIndexOutOfBoundsException
	{
		throw new ArrayIndexOutOfBoundsException("ZZ0a " + __i + " " + __l);
	}
	
	/**
	 * Returns the class which called the method which called this method.
	 *
	 * With the following stack trace:
	 * {@code
	 * SquirrelJME.callingClass()
	 * ClassB.methodB()
	 * ClassA.methodA()
	 * } this returns {@code ClassA}.
	 *
	 * @return The class which called the method which called this method.
	 * @since 2017/03/24
	 */
	public static Class<?> callingClass()
	{
		throw new todo.TODO();
	}
	
	/**
	 * Returns the class object for the class that uses the specified name.
	 *
	 * @param __n The name of the class to get the class object for.
	 * @return The class object for the given class or {@code null} if it was
	 * not found.
	 * @since 2016/09/30
	 */
	public static Class<?> classForName(String __n)
	{
		throw new todo.TODO();
	}
	
	/**
	 * Returns the class type of the specified object.
	 *
	 * @param __o The object to get the class of.
	 * @return The class type of the given object.
	 * @since 2016/08/07
	 */
	public static Class<?> classOf(Object __o)
	{
		throw new todo.TODO();
	}
	
	/**
	 * Returns a stream to the resource associated with the given class.
	 *
	 * @param __cl The class to get a resource from.
	 * @param __rc The resource name of the class.
	 * @return The input stream of the resource or {@code null} if it does not
	 * exist.
	 * @throws NullPointerException On null arguments.
	 * @since 2017/03/24
	 */
	public static InputStream classResource(Class<?> __cl, String __rc)
		throws NullPointerException
	{
		throw new todo.TODO();
	}
	
	/**
	 * Returns the access level of the classe's default constructor.
	 *
	 * @param __cl The class to get the default constructor for.
	 * @return The access level, if there is no default constructor then a
	 * negative value is returned.
	 * @throws NullPointerException On null arguments.
	 * @since 2017/03/24
	 */
	public static int defaultConstructorAccess(Class<?> __cl)
		throws NullPointerException
	{
		throw new todo.TODO();
	}
	
	/**
	 * This suggests that the garbage collector should be ran.
	 *
	 * @since 2016/08/07
	 */
	public static void gc()
	{
		throw new todo.TODO();
	}
	
	/**
	 * Returns the identity hash code of the specified object.
	 *
	 * Note that for memory reduction, the hash code is only 16-bits wide.
	 *
	 * @return The object's identity hashcode.
	 * @since 2016/08/07
	 */
	public static short identityHashCode(Object __o)
	{
		throw new todo.TODO();
	}
	
	/**
	 * Checks if the specified class is visible from the from class.
	 *
	 * @param __from The source class to check visibility for.
	 * @param __cl The target class to check if it is visible.
	 * @return {@code true} if the specified class is visible.
	 * @throws NullPointerException On null arguments.
	 * @since 2017/03/24
	 */
	public static boolean isClassVisibleFrom(Class<?> __from, Class<?> __cl)
		throws NullPointerException
	{
		throw new todo.TODO();
	}
	
	/**
	 * Checks whether the specified object is an instance of the given class.
	 *
	 * @param __cl The class to check.
	 * @param __o The object to check if it is an instance.
	 * @return {@code true} if the object is an instance of the specified
	 * class.
	 * @throws NullPointerException If no class was specified.
	 * @since 2017/03/24
	 */
	public static boolean isInstance(Class<?> __cl, Object __o)
		throws NullPointerException
	{
		throw new todo.TODO();
	}
	
	/**
	 * Checks if the two classes are in the same package.
	 *
	 * @param __a The first class.
	 * @param __b The second class.
	 * @throws NullPointerException On null arguments.
	 * @since 2017/03/24
	 */
	public static boolean isSamePackage(Class<?> __a, Class<?> __b)
		throws NullPointerException
	{
		throw new todo.TODO();
	}
	
	/**
	 * This is used to detect whether the environment truly is running on
	 * SquirrelJME, that is if the virtual machine is SquirrelJME itself.
	 *
	 * SquirrelJME's libraries could potentially be used with other virtual
	 * machines. This is used to detect that case to determine how some
	 * methods should perform when they are called (for consistency).
	 *
	 * A SquirrelJME JVM is one that is generated by the build enviroment or
	 * interpreted environment, not a third party virtual machine (such as
	 * Hotspot or JamVM, which in that case this will return {@code false}).
	 *
	 * @return {@code true} if running on a SquirrelJME JVM,
	 * otherwise {@code false}.
	 * @since 2016/10/11
	 */
	public static boolean isSquirrelJMEJVM()
	{
		return __Ext_systemvm__.isSquirrelJMEJVM();
	}
	
	/**
	 * Returns the version of the class libraries.
	 *
	 * @return The class library version.
	 * @since 2017/10/02
	 */
	public static String javaRuntimeVersion()
	{
		return "0.0.2";
	}
	
	/**
	 * Returns the e-mail to contact for the virtual machine.
	 *
	 * @return The contact e-mail for the virtual machine.
	 * @since 2017/10/02
	 */
	public static String javaVMEmail()
	{
		if (isSquirrelJMEJVM())
			return "xer@multiphasicapps.net";
		return __Ext_systemvm__.javaVMEmail();
	}
	
	/**
	 * Returns the name of the Java virtual machine.
	 *
	 * @return The name of the virtual machine.
	 * @since 2017/10/02
	 */
	public static String javaVMName()
	{
		if (isSquirrelJMEJVM())
			return "SquirrelJME";
		return __Ext_systemvm__.javaVMName();
	}
	
	/**
	 * Returns the URL to the virtual machine's vendor's URL.
	 *
	 * @return The URL of the JVM's virtual machine.
	 * @since 2017/10/02
	 */
	public static String javaVMURL()
	{
		if (isSquirrelJMEJVM())
			return "http://multiphasicapps.net/";
		return __Ext_systemvm__.javaVMURL();
	}
	
	/**
	 * Returns the vendor of the Java virtual machine.
	 *
	 * @return The vendor of the Java virtual machine.
	 * @since 2017/10/02
	 */
	public static String javaVMVendor()
	{
		if (isSquirrelJMEJVM())
			return "Stephanie Gawroriski";
		return __Ext_systemvm__.javaVMVendor();
	}
	
	/**
	 * Returns the full version of the Java virtual machine.
	 *
	 * @return The full Java virtual machine version.
	 * @since 2017/08/13
	 */
	public static String javaVMVersionFull()
	{
		if (isSquirrelJMEJVM())
			return "1.8.0-0.0.2";
		return __Ext_systemvm__.javaVMVersionFull();
	}
	
	/**
	 * Returns the short version of the Java virtual machine.
	 *
	 * @return The short Java virtual machine version.
	 * @since 2017/08/13
	 */
	public static String javaVMVersionShort()
	{
		if (isSquirrelJMEJVM())
			return "1.8.0";
		return __Ext_systemvm__.javaVMVersionShort();
	}
	
	/**
	 * This searches the the virtual machine executable memory space for
	 * strings which exist within the binary (which are always interned).
	 *
	 * @param __s The string to find the interned instance of.
	 * @return The interned string or {@code null} if it was not found.
	 * @throws NullPointerException On null arguments.
	 * @since 2017/08/15
	 */
	public static String locateInternString(String __s)
		throws NullPointerException
	{
		// Check
		if (__s == null)
			throw new NullPointerException("NARG");
		
		throw new todo.TODO();
	}
	
	/**
	 * Creates a new instance of the given class.
	 *
	 * @param <C> The class to create.
	 * @param __cl The class to create.
	 * @return The created class.
	 * @throws IllegalAccessException If the class cannot be accessed from the
	 * calling class.
	 * @throws InstantiationException If the class could not be initialized.
	 * @throws NullPointerException On null arguments.
	 * @since 2017/03/24
	 */
	public static <C> C newInstance(Class<C> __cl)
		throws IllegalAccessException, InstantiationException,
			NullPointerException
	{
		throw new todo.TODO();
	}
}
