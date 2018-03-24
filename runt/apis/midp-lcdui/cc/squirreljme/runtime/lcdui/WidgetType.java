// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) Stephanie Gawroriski <xer@multiphasicapps.net>
//     Copyright (C) Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// See license.mkd for licensing and copyright information.
// ---------------------------------------------------------------------------

package cc.squirreljme.runtime.lcdui;

/**
 * This represents the type of a displayable that something is.
 *
 * @since 2018/03/17
 */
public enum WidgetType
{
	/** Display. */
	DISPLAY,
	
	/** Canvas, */
	DISPLAYABLE_CANVAS,
	
	/** Alert. */
	DISPLAYABLE_ALERT,
	
	/** File selector. */
	DISPLAYABLE_FILE_SELECTOR,
	
	/** Form. */
	DISPLAYABLE_FORM,
	
	/** List. */
	DISPLAYABLE_LIST,
	
	/** Tabbed pane. */
	DISPLAYABLE_TABBED_PANE,
	
	/** Text Box. */
	DISPLAYABLE_TEXT_BOX,
	
	/** Choice group. */
	ITEM_CHOICE_GROUP,
	
	/** Custom item. */
	ITEM_CUSTOM,
	
	/** Date field. */
	ITEM_DATE,
	
	/** Gauge. */
	ITEM_GAUGE,
	
	/** Image. */
	ITEM_IMAGE,
	
	/** Spacer. */
	ITEM_SPACER,
	
	/** String. */
	ITEM_STRING,
	
	/** Text field. */
	ITEM_TEXT_FIELD,
	
	/** End. */
	;
}
