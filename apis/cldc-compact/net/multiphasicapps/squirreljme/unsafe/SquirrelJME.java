// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) 2013-2016 Steven Gawroriski <steven@multiphasicapps.net>
//     Copyright (C) 2013-2016 Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// For more information see license.mkd.
// ---------------------------------------------------------------------------

package net.multiphasicapps.squirreljme.unsafe;

import java.io.IOException;
import java.net.BindException;
import java.util.NoSuchElementException;

/**
 * This class contains static methods which are used by the standard class
 * library code to perform special system and virtual machine related
 * functions.
 *
 * @since 2016/08/07
 */
public final class SquirrelJME
{
	/**
	 * Not used.
	 *
	 * @since 2016/08/07
	 */
	private SquirrelJME()
	{
		throw new RuntimeException("OOPS");
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
		throw new RuntimeException("OOPS");
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
		throw new RuntimeException("OOPS");
	}
	
	/**
	 * Returns the number of milliseconds that have passed in the UTC
	 * timezone since the Java epoch.
	 *
	 * @return The amount of milliseconds that have passed.
	 * @since 2016/08/07
	 */
	public static long currentTimeMillis()
	{
		throw new RuntimeException("OOPS");
	}
	
	/**
	 * This exits the virtual machine using the specifed exit code.
	 *
	 * @param __e The exit code to use.
	 * @since 2016/08/07
	 */
	public static void exit(int __e)
	{
		throw new RuntimeException("OOPS");
	}
	
	/**
	 * This suggests that the garbage collector should be ran.
	 *
	 * @since 2016/08/07
	 */
	public static void gc()
	{
		throw new RuntimeException("OOPS");
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
		throw new RuntimeException("OOPS");
	}
	
	/**
	 * Is this the SquirrelJME kernel?
	 *
	 * @return {@code true} if this is the kernel process, otherwise
	 * {@code false} indicates a user-mode process.
	 * @since 2016/10/11
	 */
	public static boolean isKernel()
	{
		throw new RuntimeException("OOPS");
	}
	
	/**
	 * This is used to detect whether the environment truly is running on
	 * SquirrelJME.
	 *
	 * @return {@code true} if running on SquirrelJME, otherwise {@code false}.
	 * @since 2016/10/11
	 */
	public static boolean isSquirrelJME()
	{
		throw new RuntimeException("OOPS");
	}
	
	/**
	 * Accepts an incoming mailbox request to create a mailbox connection.
	 *
	 * @param __id The listening mailbox to accept.
	 * @return The mailbox descriptor for the server end of the mailbox.
	 * @throws IllegalArgumentException If the mailbox is not valid.
	 * @throws InterruptedException If the thread was interrupted accepting
	 * a connection.
	 * @throws IOException If the mailbox is closed.
	 * @since 2016/10/13
	 */
	public static int mailboxAccept(int __ld)
		throws IllegalArgumentException, IOException, InterruptedException
	{
		throw new RuntimeException("OOPS");
	}
	
	/**
	 * Closes the given mailbox client.
	 *
	 * If the mailbox is already closed or is going to be closed this has
	 * no effect.
	 *
	 * @param __fd The descriptor to close.
	 * @since 2016/10/13
	 */
	public static void mailboxClose(int __fd)
	{
		throw new RuntimeException("OOPS");
	}
	
	/**
	 * Connects to a mailbox that is listening. 
	 *
	 * @param __mb The UTF-8 bytes for the midlet to connect to, if
	 * {@code null} then any midlet is connected to.
	 * @param __mo The offset in the midlet name, ignored if {@code __mb} is
	 * {@code null}.
	 * @param __ml The length of the midlet UTF-8 bytes, ignored if
	 * {@code __mb} is {@code null}.
	 * @param __sb The UTF-8 bytes of the server to connect to.
	 * @param __so The offset in the server name.
	 * @param __sl The number of UTF-8 bytes used in the server name.
	 * @param __v The encoded version of the server to connect to, the server
	 * must have a version that is at least this value.
	 * @param __am If {@code true} then authorized mode to use.
	 * @throws ArrayIndexOutOfBoundsException If the offset and/or length are
	 * negative or exceed the array bounds.
	 * @throws BindException If the server does not exist.
	 * @throws IllegalArgumentException If the remote midlet is malformed or
	 * the server name and/or version are malformed.
	 * @throws IOException If the remote destination is closed.
	 * @throws NullPointerException If no server was specified or the offset
	 * and length are not of the specified values and the midlet is not
	 * specified.
	 * @since 2016/10/13
	 */
	public static int mailboxConnect(byte[] __mb, int __mo, int __ml,
		byte[] __sb, int __so, int __sl, int __v, boolean __am)
		throws ArrayIndexOutOfBoundsException, BindException,
			IllegalArgumentException, IOException, NullPointerException
	{
		throw new RuntimeException("OOPS");
	}
	
	/**
	 * Sets up a listening mailbox that waits for incoming mailbox connections.
	 *
	 * @param __b The name of the server, UTF-8 encoded.
	 * @param __o The offset.
	 * @param __l The length of the name.
	 * @param __v The integer encoded version number.
	 * @param __am If {@code true} then authorization mode is used.
	 * @return The descriptor of the mailbox destination.
	 * @throws ArrayIndexOutOfBoundsException If the offset and/or length are
	 * negative or exceed the array bounds.
	 * @throws IllegalArgumentException If the version number is not correct.
	 * @throws IOException If the mailbox was closed.
	 * @throws NullPointerException On null arguments.
	 * @since 2016/10/13
	 */
	public static int mailboxListen(byte[] __b, int __o, int __l, int __v,
		boolean __am)
		throws ArrayIndexOutOfBoundsException,
			IllegalArgumentException, IOException, NullPointerException
	{
		throw new RuntimeException("OOPS");
	}
	
	/**
	 * Receives a single datagram from the input mailbox.
	 *
	 * @param __fd The mailbox to receive data from.
	 * @param __chan An array with a length of least zero, used as output to
	 * specify the channel the data was sent on.
	 * @param __b The output array where data is to be written.
	 * @param __o The starting offset to the output.
	 * @param __l The maximum number of bytes to read.
	 * @param __wait If {@code true} then the operation will block until a
	 * datagram is read or the thread is interrupted.
	 * @return The number of bytes read, or a negative value if the end of
	 * the stream was reached. A value of zero means that a datagram with no
	 * actual data was sent by the remote side.
	 * @throws ArrayIndexOutOfBoundsException If the offset and/or length
	 * are negative or exceed the length of the array; or the channel array has
	 * a zero length.
	 * @throws ArrayStoreException If there is not enough data in the output
	 * array to store the datagram data, {@code __chan} will contain the
	 * required storage length.
	 * @throws IllegalArgumentException If the mailbox descriptor is not
	 * valid.
	 * @throws InterruptedException If an interrupt occured waiting for data.
	 * @throws NoSuchElementException If not waiting and there are no datagrams
	 * available.
	 * @throws NullPointerException On null arguments.
	 * @since 2016/10/13
	 */
	public static int mailboxReceive(int __fd, int[] __chan, byte[] __b,
		int __o, int __l, boolean __wait)
		throws ArrayIndexOutOfBoundsException, ArrayStoreException,
			IllegalArgumentException, InterruptedException,
			NoSuchElementException, NullPointerException
	{
		throw new RuntimeException("OOPS");
	}
	
	/**
	 * Returns the remote ID of the connected mailbox.
	 *
	 * @param __fd The remote file descriptor.
	 * @return The byte array representing the remote ID.
	 * @throws IllegalArgumentException If the mailbox descriptor is not valid.
	 * @since 2016/10/13
	 */
	public static byte[] mailboxRemoteID(int __fd)
		throws IllegalArgumentException
	{
		throw new RuntimeException("OOPS");
	}
	
	/**
	 * Sends a single datagram to the destination mailbox.
	 *
	 * @param __fd The mailbox descriptor to send data over.
	 * @param __chan The channel to send over.
	 * @param __b The data to send.
	 * @param __o The offset to the start of the data.
	 * @param __l The number of bytes to send.
	 * @throws ArrayIndexOutOfBoundsException If the offset and/or length
	 * are negative or exceed the length of the array.
	 * @throws IllegalArgumentException If the descriptor is not valid.
	 * @throws IOException If the remote end was closed.
	 * @throws NullPointerException On null arguments.
	 * @since 2016/10/13
	 */
	public static void mailboxSend(int __fd, int __chan, byte[] __b, int __o,
		int __l)
		throws ArrayIndexOutOfBoundsException, IllegalArgumentException,
			IOException, NullPointerException
	{
		throw new RuntimeException("OOPS");
	}
	
	/**
	 * Returns the identification number of the current midlet.
	 *
	 * @return The unique midlet number, a value of zero represents the
	 * kernel.
	 * @since 2016/10/13
	 */
	public static int midletID()
	{
		throw new RuntimeException("OOPS");
	}
	
	/**
	 * Returns the amount of time which has passed on an unspecified
	 * monotonic clock.
	 *
	 * @return The number of monotonic nanoseconds which have passed.
	 * @since 2016/08/07
	 */
	public static long nanoTime()
	{
		throw new RuntimeException("OOPS");
	}
	
	/**
	 * Writes a single byte to standard error.
	 *
	 * @param __b The byte to write.
	 * @since 2016/08/07
	 */
	public static void stdErr(int __b)
	{
		throw new RuntimeException("OOPS");
	}
	
	/**
	 * Writes multiple bytes to standard error.
	 *
	 * @param __b The bytes to write.
	 * @param __o The starting offset.
	 * @param __l The number of bytes to write.
	 * @throws ArrayIndexOutOfBoundsException If the offset and/or length are
	 * negative or exceed the array bounds.
	 * @since 2016/08/07
	 */
	public static void stdErr(byte[] __b, int __o, int __l)
		throws ArrayIndexOutOfBoundsException
	{
		throw new RuntimeException("OOPS");
	}
	
	/**
	 * Writes a single byte to standard output.
	 *
	 * @param __b The byte to write.
	 * @since 2016/08/07
	 */
	public static void stdOut(int __b)
	{
		throw new RuntimeException("OOPS");
	}
	
	/**
	 * Writes multiple bytes to standard output.
	 *
	 * @param __b The bytes to write.
	 * @param __o The starting offset.
	 * @param __l The number of bytes to write.
	 * @throws ArrayIndexOutOfBoundsException If the offset and/or length are
	 * negative or exceed the array bounds.
	 * @since 2016/08/07
	 */
	public static void stdOut(byte[] __b, int __o, int __l)
		throws ArrayIndexOutOfBoundsException
	{
		throw new RuntimeException("OOPS");
	}
}
