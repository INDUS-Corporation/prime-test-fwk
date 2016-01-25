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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.testng.Assert;

import com.induscorp.prime.testing.ui.core.objects.crud.ColumnType;

/**
 * 
 * @author Madhav Krishna
 *
 */
public class TableRow {
	private Map<String, String> row;
	private Map<String, ColumnType> rowColumnType;

	public TableRow() {
		row = new LinkedHashMap<String, String>();
		rowColumnType = new LinkedHashMap<String, ColumnType>();
	}

	public TableRow append(String columnName, String columnValue) {
		row.put(columnName, columnValue);
		return this;
	}
	
	public TableRow append(String columnName, String columnValue, ColumnType columnType) {
		row.put(columnName, columnValue);
		if(columnType == null) {
			rowColumnType.put(columnName, ColumnType.plainText);
		} else {
			rowColumnType.put(columnName, columnType);
		}
		return this;
	}

	public Map<String, String> getRow() {
		return row;
	}

	public int size() {
		return row.size();
	}
	
	public ColumnType getColumnType(String columnName) {
		return rowColumnType.get(columnName);
	}

	public boolean isValueExist(String value, int colIndex) {
		boolean exist = false;

		Collection<String> values = row.values();
		if (values.size() > colIndex) {
			Iterator<String> itr = values.iterator();
			int colIdxCounter = 0;
			String v1;
			while (itr.hasNext() && colIndex <= colIdxCounter) {
				v1 = itr.next();
				if (colIndex == colIdxCounter) {
					if (v1.equals(value)) {
						exist = true;
					}
					break;
				}
				colIdxCounter++;
			}

		}

		return exist;
	}

	public boolean isHeaderEqual(TableRow arg) {
		boolean equalHeader = true;
		try {
			ArrayList<String> localColumns = new ArrayList<String>(row.keySet());
			ArrayList<String> argColumns = new ArrayList<String>(arg.getRow()
					.keySet());
			for (int i = 0; i < localColumns.size(); i++) {
				if (!localColumns.get(i).equals(argColumns.get(i))) {
					equalHeader = false;
					Assert.fail("Failed to match header columns. Mismatched columns ('"
							+ localColumns.get(i)
							+ "', '"
							+ argColumns.get(i)
							+ "' at column number " + (i + 1));
					break;
				}
			}
		} catch (Exception ex) {
			equalHeader = false;
		}

		return equalHeader;
	}
	
	@Override
	public String toString() {		
		return row == null ? "" : row.toString();
	}
}
