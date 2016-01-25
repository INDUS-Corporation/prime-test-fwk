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
package com.induscorp.prime.testing.ui.core.objects.table;

/**
 * 
 * @author Madhav Krishna
 *
 */
public class HeaderColumnInfo {
	private String  columnName;
	private int htmlcolumnIndex; 
	private HeaderColumnProperty columnProperty;
	
	public HeaderColumnInfo(String  columnName, int htmlcolumnIndex, HeaderColumnProperty columnProperty) {
		this.columnName = columnName;
		this.htmlcolumnIndex = htmlcolumnIndex;
		this.columnProperty = columnProperty;
	}

	public String getColumnName() {
		return columnName;
	}

	public int getHtmlColumnIndex() {
		return htmlcolumnIndex;
	}

	public HeaderColumnProperty getColumnProperty() {
		return columnProperty;
	}	
}
