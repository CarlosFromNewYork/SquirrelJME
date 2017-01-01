// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) Steven Gawroriski <steven@multiphasicapps.net>
//     Copyright (C) Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// See license.mkd for licensing and copyright information.
// ---------------------------------------------------------------------------

package javax.microedition.lcdui;


public abstract class Item
{
	public static final int BUTTON =
		2;
	
	public static final int HYPERLINK =
		1;
	
	public static final int LAYOUT_2 =
		16384;
	
	public static final int LAYOUT_BOTTOM =
		32;
	
	public static final int LAYOUT_CENTER =
		3;
	
	public static final int LAYOUT_DEFAULT =
		0;
	
	public static final int LAYOUT_EXPAND =
		2048;
	
	public static final int LAYOUT_LEFT =
		1;
	
	public static final int LAYOUT_NEWLINE_AFTER =
		512;
	
	public static final int LAYOUT_NEWLINE_BEFORE =
		256;
	
	public static final int LAYOUT_RIGHT =
		2;
	
	public static final int LAYOUT_SHRINK =
		1024;
	
	public static final int LAYOUT_TOP =
		16;
	
	public static final int LAYOUT_VCENTER =
		48;
	
	public static final int LAYOUT_VEXPAND =
		8192;
	
	public static final int LAYOUT_VSHRINK =
		4096;
	
	public static final int PLAIN =
		0;
	
	Item()
	{
		super();
		throw new Error("TODO");
	}
	
	public void addCommand(Command __a)
	{
		throw new Error("TODO");
	}
	
	public Command[] getCommands()
	{
		throw new Error("TODO");
	}
	
	public String getLabel()
	{
		throw new Error("TODO");
	}
	
	public int getLayout()
	{
		throw new Error("TODO");
	}
	
	public ItemLayoutHint getLayoutHint()
	{
		throw new Error("TODO");
	}
	
	public int getMinimumHeight()
	{
		throw new Error("TODO");
	}
	
	public int getMinimumWidth()
	{
		throw new Error("TODO");
	}
	
	public int getPreferredHeight()
	{
		throw new Error("TODO");
	}
	
	public int getPreferredWidth()
	{
		throw new Error("TODO");
	}
	
	public void notifyStateChanged()
	{
		throw new Error("TODO");
	}
	
	public void removeCommand(Command __a)
	{
		throw new Error("TODO");
	}
	
	public void setCommand(Command __c, int __p)
	{
		throw new Error("TODO");
	}
	
	public void setDefaultCommand(Command __a)
	{
		throw new Error("TODO");
	}
	
	public void setItemCommandListener(ItemCommandListener __a)
	{
		throw new Error("TODO");
	}
	
	public void setLabel(String __a)
	{
		throw new Error("TODO");
	}
	
	public void setLayout(int __a)
	{
		throw new Error("TODO");
	}
	
	public void setLayoutHint(ItemLayoutHint __h)
	{
		throw new Error("TODO");
	}
	
	public void setPreferredSize(int __a, int __b)
	{
		throw new Error("TODO");
	}
}


