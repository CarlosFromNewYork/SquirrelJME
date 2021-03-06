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

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.Objects;
import net.multiphasicapps.javac.FileNameLineAndColumn;

/**
 * This represents a single token which contains a type and the characters
 * which make up the token.
 *
 * @since 2017/09/04
 */
public final class Token
	implements FileNameLineAndColumn
{
	/** The type of token this is, */
	protected final TokenType type;
	
	/** The token string data. */
	protected final String chars;
	
	/** The file name this token was read from. */
	protected final String filename;
	
	/** The line the token is on. */
	protected final int line;
	
	/** The column the token is on. */
	protected final int column;
	
	/** String representation. */
	private volatile Reference<String> _string;
	
	/**
	 * Initializes the token.
	 *
	 * @param __t The type of token this is.
	 * @param __c The characters which make up the token.
	 * @param __fn The file name this was read from.
	 * @param __l The line the token is on.
	 * @param __o The column the token is on.
	 * @throws NullPointerException On null arguments.
	 * @since 2017/09/06
	 */
	public Token(TokenType __t, String __c, String __fn, int __l, int __o)
		throws NullPointerException
	{
		// Check
		if (__t == null || __c == null || __fn == null)
			throw new NullPointerException("NARG");
		
		this.type = __t;
		this.chars = __c;
		this.filename = __fn;
		this.line = __l;
		this.column = __o;
	}
	
	/**
	 * Returns the token characters.
	 *
	 * @return The token characters.
	 * @since 2017/09/06
	 */
	public String characters()
	{
		return this.chars;
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2017/09/09
	 */
	@Override
	public int column()
	{
		return this.column;
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2017/09/04
	 */
	@Override
	public boolean equals(Object __o)
	{
		if (!(__o instanceof Token))
			return false;
		
		Token o = (Token)__o;
		return this.type.equals(o.type) &&
			this.chars.equals(o.chars) &&
			Objects.equals(this.filename, o.filename) &&
			this.line == o.line &&
			this.column == o.column;
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2018/03/12
	 */
	@Override
	public final String fileName()
	{
		return this.filename;
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2017/09/04
	 */
	@Override
	public int hashCode()
	{
		return this.type.hashCode() ^ this.chars.hashCode() ^
			Objects.hashCode(this.filename) ^
			this.line ^ (~this.column);
	}
	
	/**
	 * Is this a comment?
	 *
	 * @return If this is a comment or not.
	 * @since 2018/03/07
	 */
	public final boolean isComment()
	{
		return this.type == TokenType.COMMENT;
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2017/09/09
	 */
	@Override
	public int line()
	{
		return this.line;
	}
	
	/**
	 * Does this token need a space following it to be valid?
	 *
	 * @return If a space is needed to follow to make it valid.
	 * @since 2018/03/07
	 */
	public final boolean needFollowingSpace()
	{
		TokenType type = this.type;
		return type.isIdentifier() ||
			type.isKeyword() ||
			type.isLiteral() ||
			type == TokenType.OPERATOR_PLUS ||
			type == TokenType.OPERATOR_MINUS ||
			type == TokenType.OPERATOR_INCREMENT ||
			type == TokenType.OPERATOR_DECREMENT;
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2017/09/04
	 */
	@Override
	public String toString()
	{
		Reference<String> ref = this._string;
		String rv;
		
		if (ref == null || null == (rv = ref.get()))
			this._string = new WeakReference<>((rv = String.format(
				"%s@%s:%d,%d: %s", this.type, this.filename, this.line,
				this.column, this.chars)));
		
		return rv;
	}
	
	/**
	 * Returns the type of token this is.
	 *
	 * @return The token type.
	 * @since 2017/09/06
	 */
	public TokenType type()
	{
		return this.type;
	}
}

