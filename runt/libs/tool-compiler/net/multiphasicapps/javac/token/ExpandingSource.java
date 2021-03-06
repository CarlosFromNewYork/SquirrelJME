// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) Stephanie Gawroriski <xer@multiphasicapps.net>
//     Copyright (C) Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// See license.mkd for licensing and copyright information.
// ---------------------------------------------------------------------------

package net.multiphasicapps.javac.token;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import net.multiphasicapps.javac.FileNameLineAndColumn;

/**
 * This class is used for any source which provides expanding
 * tokens.
 *
 * @since 2018/03/22
 */
public abstract class ExpandingSource
	implements Closeable, FileNameLineAndColumn
{
	/** This stores the temporary token queue which allows for peeking. */
	private final LinkedList<ExpandedToken> _queue =
		new LinkedList<>();
	
	/** The last token which was read. */
	private volatile FileNameLineAndColumn _last;
	
	/**
	 * Reads the next token from the input.
	 *
	 * @return The next token.
	 * @throws IOException On read errors.
	 * @since 2018/03/22
	 */
	protected abstract ExpandedToken readNext()
		throws IOException;
	
	/**
	 * {@inheritDoc}
	 * @since 2018/03/12
	 */
	@Override
	public final int column()
	{
		FileNameLineAndColumn last = this._last;
		if (last != null)
			return last.column();
		return -1;
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2018/03/12
	 */
	@Override
	public final String fileName()
	{
		FileNameLineAndColumn last = this._last;
		if (last != null)
			return last.fileName();
		return null;
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2018/03/12
	 */
	@Override
	public final int line()
	{
		FileNameLineAndColumn last = this._last;
		if (last != null)
			return last.line();
		return -1;
	}
	
	/**
	 * Returns the next token in the queue.
	 *
	 * This method may return different sets of tokens from peeked tokens if
	 * they are to be decomposed.
	 *
	 * @return The next token in the queue or end of file tokens if there are
	 * no more tokens.
	 * @throws IOException On read errors.
	 * @since 2018/03/12
	 */
	public final ExpandedToken next()
		throws IOException
	{
		// Make sure next token is ready
		this.peek();
		
		// Return and store it
		ExpandedToken rv = this._queue.removeFirst();
		this._last = rv;
		return rv;
	}
	
	/**
	 * Returns the token that would be returned on the call to {@link #next()}
	 *
	 * @return The next token that would be returned.
	 * @throws IOException On read errors.
	 * @since 2018/03/12
	 */
	public final ExpandedToken peek()
		throws IOException
	{
		// Just read the first token
		return this.peek(0);
	}
	
	/**
	 * Returns the token which seen in the future from a given token offset.
	 *
	 * @param __o The number of tokens to read into the future.
	 * @return The next token that would be returned.
	 * @throws IndexOutOfBoundsException If a negative index was specified.
	 * @throws IOException On read errors.
	 * @since 2018/03/12
	 */
	public final ExpandedToken peek(int __o)
		throws IndexOutOfBoundsException, IOException
	{
		// {@squirreljme.error AQ12 Cannot peek a token with a negative
		// offset.}
		if (__o < 0)
			throw new IndexOutOfBoundsException("AQ12");
		
		// Read tokens from the input
		LinkedList<ExpandedToken> queue = this._queue;
		int qsize;
		while ((qsize = queue.size()) <= __o)
		{
			// Read in tokens from the input
			ExpandedToken gen = this.readNext();
			if (gen == null)
				gen = new ExpandedToken(new Token(TokenType.END_OF_FILE, "",
					this.fileName(), this.line(), this.column()));
			
			// Store token for later usage
			queue.addLast(gen);
			
			// Debug print that token
			System.err.printf("DEBUG -- Queued: %s%n", gen.characters());
		}
		
		// Use that given token
		if (__o == 0)
			return queue.getFirst();
		else if (__o == qsize - 1)
			return queue.getLast();
		return queue.get(__o);
	}
}

