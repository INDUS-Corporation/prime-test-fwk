/*
 * PrimeTestFwk
 * Copyright 2014 and beyond, INDUS Corporation, Inc.
 * 
 * PrimeTestFwk is free software: you can redistribute it and/or modify
 * it under the terms of the LESSER GNU General Public License version 3 as 
 * published by the Free Software Foundation.
 *
 * PrimeTestFwk is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * LESSER GNU General Public License version 3 for more details.
 *
 * You should have received a copy of the LESSER GNU General Public License
 * version 3 along with PrimeTestFwk. If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package com.induscorp.prime.testing.ui.standard.imgobj.datagrid;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.sikuli.script.Region;

import com.induscorp.prime.testing.ui.core.commons.LocatorType;
import com.induscorp.prime.testing.ui.core.commons.UIObjectType;
import com.induscorp.prime.testing.ui.core.config.webbrowser.WebBrowser;
import com.induscorp.prime.testing.ui.core.objects.scrollbar.HorizontalScrollbar;
import com.induscorp.prime.testing.ui.core.objects.scrollbar.VerticalScrollbar;

/**
 * This DataGrid does not work 
 * 1. If the whole table is not visible including horizontal and vertical scrollbars of the tables. 
 * 2. If there is no row marker to identify the row break.
 * 3. Header columns are not fixed
 * 4. If all the primary key columns are not visible always.
 * 5. Primary key column value is not text
 * 
 * @author Madhav Krishna
 *
 */
public class DataGridSI {
	protected LocatorType locatorType;
	protected UIObjectType uiObjectType;
	protected String displayName;

	protected List<HeaderColumnSI> keyHeaderColumns;
	protected List<HeaderColumnSI> additionalHeaderColumns;
	protected List<String> rowMarkerImages;
	protected int numLeftFrozenColumns;
	protected int numRightFrozenColumns;
	protected VerticalScrollbar vScrollbar;
	protected HorizontalScrollbar hScrollbar;
	protected int width;
	protected int height;

	public DataGridSI(String displayName, int widthInPx, int heightInPx) {
		this.locatorType = LocatorType.IMAGE;
		this.uiObjectType = UIObjectType.table;
		this.displayName = displayName;

		keyHeaderColumns = new LinkedList<HeaderColumnSI>();
		additionalHeaderColumns = new LinkedList<HeaderColumnSI>();
		rowMarkerImages = new LinkedList<String>();
		vScrollbar = null;
		hScrollbar = null;
		width = widthInPx;
		height = heightInPx;
	}

	public LocatorType getLocatorType() {
		return locatorType;
	}

	public UIObjectType getUiObjectType() {
		return uiObjectType;
	}

	public String getDisplayName() {
		return displayName;
	}

	/**
	 * It should contain the whole image to cover key header column height and
	 * width.
	 * 
	 * @param objects
	 * @return
	 */
	public DataGridSI addKeyHeaderColumns(HeaderColumnSI... objects) {
		if (objects != null && objects.length > 0) {
			keyHeaderColumns.addAll(Arrays.asList(objects));
		}
		return this;
	}

	public List<HeaderColumnSI> getKeyHeaderColumns() {
		return keyHeaderColumns;
	}
	
	public HeaderColumnSI getKeyHeaderColumn(String columnDisplayName) {
		for(HeaderColumnSI colObj : keyHeaderColumns) {
			if(columnDisplayName.equals(colObj.getDisplayName())) {
				return colObj;
			}
		}
		return null;
	}
	
	/**
	 * It should contain the whole image to cover additional header column height and
	 * width.
	 * 
	 * @param objects
	 * @return
	 */
	public DataGridSI addAdditionalHeaderColumns(HeaderColumnSI... objects) {
		if (objects != null && objects.length > 0) {
			additionalHeaderColumns.addAll(Arrays.asList(objects));
		}
		return this;
	}

	public List<HeaderColumnSI> getAdditionalHeaderColumns() {
		return additionalHeaderColumns;
	}
	
	public HeaderColumnSI getAdditionalHeaderColumn(String columnDisplayName) {
		for (HeaderColumnSI colObj : additionalHeaderColumns) {
			if(columnDisplayName.equals(colObj.getDisplayName())) {
				return colObj;
			}
		}
		return null;
	}

	/**
	 * you can just specify the first column left border + row border
	 * combination as row marker.
	 * 
	 * @param images
	 * @return
	 */
	public DataGridSI addRowMarkerImages(String... images) {
		if (images != null && images.length > 0) {
			rowMarkerImages.addAll(Arrays.asList(images));
		}
		return this;
	}

	public List<String> getRowMarkerImages() {
		return rowMarkerImages;
	}

	public int getNumLeftFrozenColumns() {
		return numLeftFrozenColumns;
	}

	public void setNumLeftFrozenColumns(int numLeftFrozenColumns) {
		this.numLeftFrozenColumns = numLeftFrozenColumns;
	}

	public int getNumRightFrozenColumns() {
		return numRightFrozenColumns;
	}

	public void setNumRightFrozenColumns(int numRightFrozenColumns) {
		this.numRightFrozenColumns = numRightFrozenColumns;
	}

	public VerticalScrollbar getVScrollbar() {
		return vScrollbar;
	}

	public void setVScrollbar(VerticalScrollbar vScrollbar) {
		this.vScrollbar = vScrollbar;
	}

	public HorizontalScrollbar getHScrollbar() {
		return hScrollbar;
	}

	public void setHScrollbar(HorizontalScrollbar hScrollbar) {
		this.hScrollbar = hScrollbar;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public DataGridValidatorSI getValidator(WebBrowser browser, Region region) {
		return new DataGridValidatorSI(browser, this, region);
	}
}
