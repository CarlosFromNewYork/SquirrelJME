// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) Stephanie Gawroriski <xer@multiphasicapps.net>
//     Copyright (C) Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// See license.mkd for licensing and copyright information.
// ---------------------------------------------------------------------------

package javax.microedition.lcdui;

public class Command
	extends __Collectable__
{ 
	/** Returns the user to the previous screen.. */
	public static final int BACK =
		2;
	
	/** Specified a standard negative to something on the screen. */
	public static final int CANCEL =
		3;
	
	/** A command that is used to exit the application. */
	public static final int EXIT =
		7;
	
	/** A request for on-line help. */
	public static final int HELP =
		5;
	
	/** System specific type. */
	public static final int ITEM =
		8;
	
	/** Specified as a standard affirmative to something on the screen. */
	public static final int OK =
		4;
	
	/** A command which pertains to the current screen. */
	public static final int SCREEN =
		1;
	
	/** A command which will stop an in-progress operation. */
	public static final int STOP =
		6;
	
	/** The first command type. */
	private static final int _FIRST_TYPE =
		SCREEN;
	
	/** The last command type. */
	private static final int _LAST_TYPE =
		ITEM;
	
	/** The command type. */
	private final int _type;
	
	/** The priority. */
	private final int _priority;
	
	/** Is this an implementation specific command with fixed text? */
	private final boolean _implspec;
	
	/** The short label. */
	private String _shortlabel;
	
	/** The long label. */
	private String _longlabel;
	
	/** The image used. */
	private Image _image;
	
	/**
	 * Creates a new command with the specified parameters.
	 *
	 * @param __sl The short label of the command.
	 * @param __type The type of command this is.
	 * @param __pri The priority of the command.
	 * @throws IllegalArgumentException If the command type is not valid.
	 * @throws NullPointerException If no short label was specified.
	 * @since 2017/02/28
	 */
	public Command(String __sl, int __type, int __pri)
		throws IllegalArgumentException, NullPointerException
	{
		this(__sl, null, null, __type, __pri, false);
	}
	
	/**
	 * Creates a new command with the specified parameters.
	 *
	 * @param __sl The short label of the command.
	 * @param __type The type of command this is.
	 * @param __pri The priority of the command.
	 * @param __implspec If true this is an implementation specific command
	 * which the returned labels are always blank except that internally they
	 * use the passed strings.
	 * @throws IllegalArgumentException If the command type is not valid.
	 * @throws NullPointerException If no short label was specified.
	 * @since 2017/02/28
	 */
	Command(String __sl, int __type, int __pri, boolean __implspec)
		throws IllegalArgumentException, NullPointerException
	{
		this(__sl, null, null, __type, __pri, __implspec);
	}
	
	/**
	 * Creates a new command with the specified parameters.
	 *
	 * @param __sl The short label of the command.
	 * @param __ll The long label of the command, may be {@code null}.
	 * @param __type The type of command this is.
	 * @param __pri The priority of the command.
	 * @throws IllegalArgumentException If the command type is not valid.
	 * @throws NullPointerException If no short label was specified.
	 * @since 2017/02/28
	 */
	public Command(String __sl, String __ll, int __type, int __pri)
		throws IllegalArgumentException, NullPointerException
	{
		this(__sl, null, null, __type, __pri, false);
	}
	
	/**
	 * Creates a new command with the specified parameters.
	 *
	 * @param __sl The short label of the command.
	 * @param __ll The long label of the command, may be {@code null}.
	 * @param __i The image used on the command, may be {@code null}.
	 * @param __type The type of command this is.
	 * @param __pri The priority of the command.
	 * @throws IllegalArgumentException If the command type is not valid.
	 * @throws NullPointerException If no short label was specified.
	 * @since 2017/02/28
	 */
	public Command(String __sl, String __ll, Image __i, int __type, int __pri)
		throws IllegalArgumentException, NullPointerException
	{
		this(__sl, __ll, __i, __type, __pri, false);
	}
	
	/**
	 * Creates a new command with the specified parameters.
	 *
	 * @param __sl The short label of the command.
	 * @param __ll The long label of the command, may be {@code null}.
	 * @param __i The image used on the command, may be {@code null}.
	 * @param __type The type of command this is.
	 * @param __pri The priority of the command.
	 * @param __implspec If true this is an implementation specific command
	 * which the returned labels are always blank except that internally they
	 * use the passed strings.
	 * @throws IllegalArgumentException If the command type is not valid.
	 * @throws NullPointerException If no short label was specified.
	 * @since 2018/03/29
	 */
	Command(String __sl, String __ll, Image __i, int __type, int __pri,
		boolean __implspec)
		throws IllegalArgumentException, NullPointerException
	{
		// Check
		if (__sl == null)
			throw new NullPointerException("NARG");
		
		// {@squirreljme.error EB16 And invalid command type was specified.
		// (The command type)}
		if (__type < _FIRST_TYPE || __type > _LAST_TYPE)
			throw new IllegalArgumentException(
				String.format("EB16 %d", __type));
		
		// Set
		this._implspec = __implspec;
		this._type = __type;
		this._priority = __pri;
		
		// Internally set details
		this.__setImage(__i);
		this.__setLabels(__sl, __ll);
	}
	
	public int getCommandType()
	{
		throw new todo.TODO();
	}
	
	public boolean getEnabled()
	{
		throw new todo.TODO();
	}
	
	public Font getFont()
	{
		throw new todo.TODO();
	}
	
	/**
	 * Returns the image of the command.
	 *
	 * @return The image of the command or {@code null} if it has none.
	 * @since 2018/03/29
	 */
	public Image getImage()
	{
		// Do not provide implementation specific images
		if (this._implspec)
			return null;
		
		throw new todo.TODO();
	}
	
	/**
	 * Returns the label used for this command.
	 *
	 * @return The label used for the command.
	 * @since 2018/03/29
	 */
	public String getLabel()
	{
		// Do not provide implementation specific labels
		if (this._implspec)
			return "";
		
		throw new todo.TODO();
	}
	
	/**
	 * Returns the long label of the command.
	 *
	 * @return The long label of the command or {@code null} if it has none.
	 * @since 2018/03/29
	 */
	public String getLongLabel()
	{
		// Do not provide implementation specific labels
		if (this._implspec)
			return null;
		
		throw new todo.TODO();
	}
	
	public int getPriority()
	{
		throw new todo.TODO();
	}
	
	public void onParentEnabled(boolean __e)
	{
		throw new todo.TODO();
	}
	
	public void setEnabled(boolean __e)
	{
		// Do nothing for implementation specific commands
		if (this._implspec)
			return;
		
		throw new todo.TODO();
	}
	
	public void setFont(Font __f)
	{
		// Do nothing for implementation specific commands
		if (this._implspec)
			return;
		
		throw new todo.TODO();
	}
	
	/**
	 * Sets the label to be displayed.
	 *
	 * @param __s The label to display.
	 * @throws NullPointerException On null arguments.
	 * @since 2018/03/29
	 */
	public void setLabel(String __s)
		throws NullPointerException
	{
		if (__s == null)
			throw new NullPointerException("NARG");
		
		// Do nothing for implementation specific commands
		if (this._implspec)
			return;
		
		this.__setLabels(__s, this._longlabel);
	}
	
	/**
	 * Sets the long label of the command.
	 *
	 * @param __s The long label to use, {@code null} clears it.
	 * @since 2018/03/29
	 */
	public void setLongLabel(String __s)
	{
		// Do nothing for implementation specific commands
		if (this._implspec)
			return;
		
		this.__setLabels(this._shortlabel, __s);
	}
	
	/**
	 * Sets the image to be displayed for this command.
	 *
	 * @param __i The image to be displayed, {@code null} clears this.
	 * @since 2018/03/29
	 */
	public void setImage(Image __i)
	{
		// Do nothing for implementation specific commands
		if (this._implspec)
			return;
		
		this.__setImage(__i);
	}
	
	/**
	 * Internally sets the image to be displayed.
	 *
	 * @param __i The image to display, {@code null} will clear it.
	 * @since 2018/03/29
	 */
	private final void __setImage(Image __i)
	{
		throw new todo.TODO();
	}
	
	/**
	 * Internally sets the labels to be displayed.
	 *
	 * @param __sl The short label.
	 * @param __ll The long label, {@code null} will clear it.
	 * @throws NullPointerException If no short label was specified.
	 * @since 2018/03/29
	 */
	private final void __setLabels(String __sl, String __ll)
		throws NullPointerException
	{
		if (__sl == null)
			throw new NullPointerException("NARG");
		
		throw new todo.TODO();
	}
}


