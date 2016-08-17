// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) 2013-2016 Steven Gawroriski <steven@multiphasicapps.net>
//     Copyright (C) 2013-2016 Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// For more information see license.mkd.
// ---------------------------------------------------------------------------

package net.multiphasicapps.squirreljme.os.generic;

import net.multiphasicapps.io.data.RandomAccessData;

/**
 * This class supports reading the executable blob format.
 *
 * Blobs are laid out similarly to ZIP files.
 * {@code
 * [entries]
 * [central index]
 * [end header]
 * }
 *
 * The entries are laid out as the following. All entries start on an aligned
 * address of 4.
 * {@code
 * [... Align to 4 bytes ...]
 * [byte[]        : data...]
 * }
 *
 * The central index is laid out in the following manner.
 * {@code
 * [... Align to 4 bytes ...]
 * [int: Central directory magic number]
 * [???: Central index entries]
 * }
 *
 * Each central index entry is of the following.
 * {@code
 * [byte:  The entry type (BlobContentType)]
 * [byte:  The lower masked away bits of the entry size.]
 * [short: The string in the string table for this entry name.]
 * [short: The shifted offset where the entry's data position is.]
 * [short: The shifted size of the entry.]
 * }
 *
 * The end header is laid out in the following manner.
 * {@code
 * [... Align to 4 bytes ...]
 * [int: End header magic number]
 * [int: The number of entries in the central index]
 * [int: Bytes to subtract from the magic position to reach the central index]
 * }
 *
 * @since 2016/08/11
 */
public class GenericBlob
{
	/** The number of bit positions to shift. */
	public static final int NAMESPACE_SHIFT =
		2;
	
	/** The mask for namespace positions (minimal alignment). */
	public static final int ALIGN_MASK =
		(1 << NAMESPACE_SHIFT) - 1;
	
	/** Maximum size namespace entries may be. */
	public static final int MAX_ENTRY_SIZE =
		65535 << NAMESPACE_SHIFT;
	
	/** The magic number identifying entry start. */
	public static final int START_ENTRY_MAGIC_NUMBER =
		0xD3CECDCA;
	
	/** Central directory magic number. */
	public static final int CENTRAL_DIRECTORY_MAGIC_NUMBER =
		0xC1CCD2C1;
	
	/** The magic number for the end header. */
	public static final int END_CENTRAL_DIRECTORY_MAGIC_NUMBER =
		0xC2F6E5AE;
	
	/** End of class magic number. */
	public static final int END_CLASS_MAGIC_NUMBER =
		0xD9E6F0EA;
	
	/** The blob data. */
	protected final RandomAccessData data;
	
	/**
	 * Initializes the blob and uses the given random data.
	 *
	 * @param __d The data to the blob.
	 * @throws NullPointerException On null arguments.
	 * @since 2016/08/11
	 */
	public GenericBlob(RandomAccessData __d)
		throws NullPointerException
	{
		// Check
		if (__d == null)
			throw new NullPointerException("NARG");
		
		// Set
		this.data = __d;
	}
}

