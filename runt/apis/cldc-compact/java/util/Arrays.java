// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) Stephanie Gawroriski <xer@multiphasicapps.net>
//     Copyright (C) Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// See license.mkd for licensing and copyright information.
// ---------------------------------------------------------------------------

package java.util;

/**
 * This class contains utility methods which operate on arrays.
 *
 * {@squirreljme.error ZZ06 The sort range exceeds the array bounds.}
 * {@squirreljme.error ZZ07 The from index exceeds the to index.}
 *
 * @since 2016/09/30
 */
public class Arrays
{
	private Arrays()
	{
		super();
		throw new todo.TODO();
	}
	
	/**
	 * Wraps the specified array allowing access to its data as a fixed size
	 * list. The returned {@link List} will have {@link RandomAccess}
	 * implemented.
	 *
	 * @param <T> The type of values contained within the array.
	 * @param __a The array to wrap.
	 * @return The specified array wrapped in a {@link List}.
	 * @throws NullPointerException On null arguments.
	 * @since 2016/08/31
	 */
	@SuppressWarnings({"unchecked"})
	public static <T> List<T> asList(T... __a)
		throws NullPointerException
	{
		// Check
		if (__a == null)
			throw new NullPointerException("NARG");
		
		// Wrap it
		return new __ArraysList__<T>(__a);
	}
	
	public static int binarySearch(long[] __a, long __b)
	{
		throw new todo.TODO();
	}
	
	public static int binarySearch(long[] __a, int __b, int __c, long __d)
	{
		throw new todo.TODO();
	}
	
	public static int binarySearch(int[] __a, int __b)
	{
		throw new todo.TODO();
	}
	
	public static int binarySearch(int[] __a, int __b, int __c, int __d)
	{
		throw new todo.TODO();
	}
	
	public static int binarySearch(short[] __a, short __b)
	{
		throw new todo.TODO();
	}
	
	public static int binarySearch(short[] __a, int __b, int __c, short __d)
	{
		throw new todo.TODO();
	}
	
	public static int binarySearch(char[] __a, char __b)
	{
		throw new todo.TODO();
	}
	
	public static int binarySearch(char[] __a, int __b, int __c, char __d)
	{
		throw new todo.TODO();
	}
	
	public static int binarySearch(byte[] __a, byte __b)
	{
		throw new todo.TODO();
	}
	
	public static int binarySearch(byte[] __a, int __b, int __c, byte __d)
	{
		throw new todo.TODO();
	}
	
	public static int binarySearch(double[] __a, double __b)
	{
		throw new todo.TODO();
	}
	
	public static int binarySearch(double[] __a, int __b, int __c, double __d)
	{
		throw new todo.TODO();
	}
	
	public static int binarySearch(float[] __a, float __b)
	{
		throw new todo.TODO();
	}
	
	public static int binarySearch(float[] __a, int __b, int __c, float __d)
	{
		throw new todo.TODO();
	}
	
	public static int binarySearch(Object[] __a, Object __b)
	{
		throw new todo.TODO();
	}
	
	public static int binarySearch(Object[] __a, int __b, int __c, Object __d
		)
	{
		throw new todo.TODO();
	}
	
	public static <T> int binarySearch(T[] __a, T __b, Comparator<? super T>
		__c)
	{
		throw new todo.TODO();
	}
	
	public static <T> int binarySearch(T[] __a, int __b, int __c, T __d,
		Comparator<? super T> __e)
	{
		throw new todo.TODO();
	}
	
	public static <T> T[] copyOf(T[] __a, int __b)
	{
		throw new todo.TODO();
	}
	
	public static <T, U> T[] copyOf(U[] __a, int __b, Class<? extends T[]>
		__c)
	{
		throw new todo.TODO();
	}
	
	public static byte[] copyOf(byte[] __a, int __b)
	{
		throw new todo.TODO();
	}
	
	public static short[] copyOf(short[] __a, int __b)
	{
		throw new todo.TODO();
	}
	
	public static int[] copyOf(int[] __a, int __b)
	{
		throw new todo.TODO();
	}
	
	public static long[] copyOf(long[] __a, int __b)
	{
		throw new todo.TODO();
	}
	
	public static char[] copyOf(char[] __a, int __b)
	{
		throw new todo.TODO();
	}
	
	public static float[] copyOf(float[] __a, int __b)
	{
		throw new todo.TODO();
	}
	
	public static double[] copyOf(double[] __a, int __b)
	{
		throw new todo.TODO();
	}
	
	public static boolean[] copyOf(boolean[] __a, int __b)
	{
		throw new todo.TODO();
	}
	
	public static boolean equals(long[] __a, long[] __b)
	{
		throw new todo.TODO();
	}
	
	public static boolean equals(int[] __a, int[] __b)
	{
		throw new todo.TODO();
	}
	
	public static boolean equals(short[] __a, short[] __b)
	{
		throw new todo.TODO();
	}
	
	public static boolean equals(char[] __a, char[] __b)
	{
		throw new todo.TODO();
	}
	
	public static boolean equals(byte[] __a, byte[] __b)
	{
		throw new todo.TODO();
	}
	
	public static boolean equals(boolean[] __a, boolean[] __b)
	{
		throw new todo.TODO();
	}
	
	public static boolean equals(double[] __a, double[] __b)
	{
		throw new todo.TODO();
	}
	
	public static boolean equals(float[] __a, float[] __b)
	{
		throw new todo.TODO();
	}
	
	public static boolean equals(Object[] __a, Object[] __b)
	{
		throw new todo.TODO();
	}
	
	public static void fill(long[] __a, long __b)
	{
		throw new todo.TODO();
	}
	
	public static void fill(int[] __a, int __b)
	{
		throw new todo.TODO();
	}
	
	public static void fill(short[] __a, short __b)
	{
		throw new todo.TODO();
	}
	
	public static void fill(char[] __a, char __b)
	{
		throw new todo.TODO();
	}
	
	public static void fill(byte[] __a, byte __b)
	{
		throw new todo.TODO();
	}
	
	public static void fill(boolean[] __a, boolean __b)
	{
		throw new todo.TODO();
	}
	
	public static void fill(double[] __a, double __b)
	{
		throw new todo.TODO();
	}
	
	public static void fill(float[] __a, float __b)
	{
		throw new todo.TODO();
	}
	
	public static void fill(Object[] __a, Object __b)
	{
		throw new todo.TODO();
	}
	
	/**
	 * Sorts the specified array.
	 *
	 * @param __a The array to sort.
	 * @throws NullPointerException If no array was specified.
	 * @since 2016/09/30
	 */
	public static void sort(int[] __a)
		throws NullPointerException
	{
		// Check
		if (__a == null)
			throw new NullPointerException("NARG");
		
		sort(__a, 0, __a.length);
	}
	
	public static void sort(int[] __a, int __from, int __to)
		throws ArrayIndexOutOfBoundsException, IllegalArgumentException,
			NullPointerException
	{
		// Check
		if (__a == null)
			throw new NullPointerException("NARG");
		int an = __a.length;
		if (__from < 0 || __to > an)
			throw new ArrayIndexOutOfBoundsException("ZZ04");
		if (__from > __to)
			throw new IllegalArgumentException("ZZ07");
		
		// Pointless sort?
		if (__from == __to)
			return;
		
		throw new todo.TODO();
	}
	
	/**
	 * Sorts the specified array.
	 *
	 * @param __a The array to sort.
	 * @throws NullPointerException If no array was specified.
	 * @since 2016/09/30
	 */
	public static void sort(long[] __a)
		throws NullPointerException
	{
		// Check
		if (__a == null)
			throw new NullPointerException("NARG");	
		
		sort(__a, 0, __a.length);
	}
	
	public static void sort(long[] __a, int __from, int __to)
		throws ArrayIndexOutOfBoundsException, IllegalArgumentException,
			NullPointerException
	{
		// Check
		if (__a == null)
			throw new NullPointerException("NARG");
		int an = __a.length;
		if (__from < 0 || __to > an)
			throw new ArrayIndexOutOfBoundsException("ZZ04");
		if (__from > __to)
			throw new IllegalArgumentException("ZZ07");
		
		// Pointless sort?
		if (__from == __to)
			return;
		
		throw new todo.TODO();
	}
	
	/**
	 * Sorts the specified array.
	 *
	 * @param __a The array to sort.
	 * @throws NullPointerException If no array was specified.
	 * @since 2016/09/30
	 */
	public static void sort(short[] __a)
		throws NullPointerException
	{
		// Check
		if (__a == null)
			throw new NullPointerException("NARG");	
		
		sort(__a, 0, __a.length);
	}
	
	public static void sort(short[] __a, int __from, int __to)
		throws ArrayIndexOutOfBoundsException, IllegalArgumentException,
			NullPointerException
	{
		// Check
		if (__a == null)
			throw new NullPointerException("NARG");
		int an = __a.length;
		if (__from < 0 || __to > an)
			throw new ArrayIndexOutOfBoundsException("ZZ04");
		if (__from > __to)
			throw new IllegalArgumentException("ZZ07");
		
		// Pointless sort?
		if (__from == __to)
			return;
		
		throw new todo.TODO();
	}
	
	/**
	 * Sorts the specified array.
	 *
	 * @param __a The array to sort.
	 * @throws NullPointerException If no array was specified.
	 * @since 2016/09/30
	 */
	public static void sort(char[] __a)
		throws NullPointerException
	{
		// Check
		if (__a == null)
			throw new NullPointerException("NARG");	
		
		sort(__a, 0, __a.length);
	}
	
	public static void sort(char[] __a, int __from, int __to)
		throws ArrayIndexOutOfBoundsException, IllegalArgumentException,
			NullPointerException
	{
		// Check
		if (__a == null)
			throw new NullPointerException("NARG");
		int an = __a.length;
		if (__from < 0 || __to > an)
			throw new ArrayIndexOutOfBoundsException("ZZ04");
		if (__from > __to)
			throw new IllegalArgumentException("ZZ07");
		
		// Pointless sort?
		if (__from == __to)
			return;
		
		throw new todo.TODO();
	}
	
	/**
	 * Sorts the specified array.
	 *
	 * @param __a The array to sort.
	 * @throws NullPointerException If no array was specified.
	 * @since 2016/09/30
	 */
	public static void sort(byte[] __a)
		throws NullPointerException
	{
		// Check
		if (__a == null)
			throw new NullPointerException("NARG");	
		
		sort(__a, 0, __a.length);
	}
	
	public static void sort(byte[] __a, int __from, int __to)
		throws ArrayIndexOutOfBoundsException, IllegalArgumentException,
			NullPointerException
	{
		// Check
		if (__a == null)
			throw new NullPointerException("NARG");
		int an = __a.length;
		if (__from < 0 || __to > an)
			throw new ArrayIndexOutOfBoundsException("ZZ04");
		if (__from > __to)
			throw new IllegalArgumentException("ZZ07");
		
		// Pointless sort?
		if (__from == __to)
			return;
		
		throw new todo.TODO();
	}
	
	/**
	 * Sorts the specified array.
	 *
	 * @param __a The array to sort.
	 * @throws NullPointerException If no array was specified.
	 * @since 2016/09/30
	 */
	public static void sort(float[] __a)
		throws NullPointerException
	{
		// Check
		if (__a == null)
			throw new NullPointerException("NARG");	
		
		sort(__a, 0, __a.length);
	}
	
	public static void sort(float[] __a, int __from, int __to)
		throws ArrayIndexOutOfBoundsException, IllegalArgumentException,
			NullPointerException
	{
		// Check
		if (__a == null)
			throw new NullPointerException("NARG");
		int an = __a.length;
		if (__from < 0 || __to > an)
			throw new ArrayIndexOutOfBoundsException("ZZ04");
		if (__from > __to)
			throw new IllegalArgumentException("ZZ07");
		
		// Pointless sort?
		if (__from == __to)
			return;
		
		throw new todo.TODO();
	}
	
	/**
	 * Sorts the specified array.
	 *
	 * @param __a The array to sort.
	 * @throws NullPointerException If no array was specified.
	 * @since 2016/09/30
	 */
	public static void sort(double[] __a)
		throws NullPointerException
	{
		// Check
		if (__a == null)
			throw new NullPointerException("NARG");	
		
		sort(__a, 0, __a.length);
	}
	
	public static void sort(double[] __a, int __from, int __to)
		throws ArrayIndexOutOfBoundsException, IllegalArgumentException,
			NullPointerException
	{
		// Check
		if (__a == null)
			throw new NullPointerException("NARG");
		int an = __a.length;
		if (__from < 0 || __to > an)
			throw new ArrayIndexOutOfBoundsException("ZZ04");
		if (__from > __to)
			throw new IllegalArgumentException("ZZ07");
		
		// Pointless sort?
		if (__from == __to)
			return;
		
		throw new todo.TODO();
	}
	
	/**
	 * Sorts the specified array using the natural {@link Comparator}.
	 *
	 * @param __a The array to sort.
	 * @throws NullPointerException If no array was specified.
	 * @since 2016/09/30
	 */
	public static void sort(Object[] __a)
		throws NullPointerException
	{
		// Check
		if (__a == null)
			throw new NullPointerException("NARG");	
		
		sort(__a, 0, __a.length, null);
	}
	
	public static void sort(Object[] __a, int __from, int __to)
		throws ArrayIndexOutOfBoundsException, IllegalArgumentException,
			NullPointerException
	{
		sort(__a, __from, __to, null);
	}
	
	public static <T> void sort(T[] __a, Comparator<? super T> __comp)
		throws NullPointerException
	{
		// Check
		if (__a == null)
			throw new NullPointerException("NARG");
		
		sort(__a, 0, __a.length, __comp);
	}
	
	public static <T> void sort(T[] __a, int __from, int __to,
		Comparator<? super T> __comp)
		throws ArrayIndexOutOfBoundsException, IllegalArgumentException,
			NullPointerException
	{
		// Check
		if (__a == null)
			throw new NullPointerException("NARG");
		int an = __a.length;
		if (__from < 0 || __to > an)
			throw new ArrayIndexOutOfBoundsException("ZZ04");
		if (__from > __to)
			throw new IllegalArgumentException("ZZ07");
		
		// Pointless sort?
		if (__from == __to)
			return;
		
		throw new todo.TODO();
	}
}

