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
import java.util.List;

import org.testng.Assert;

/**
 * 
 * @author Madhav Krishna
 *
 */
public class TableRows {

	private List<TableRow> rows;
	private boolean editable;

	public TableRows() {
		rows = new ArrayList<TableRow>();
		editable = false;
	}

	public TableRows append(TableRow record) {
		if (rows.size() > 0) {
			if (rows.get(0).size() != record.size()) {
				Assert.fail("Please specify the same number of columns ("
						+ rows.get(0).size()
						+ ") as specified in first row. Found num columns ("
						+ rows.get(0).size() + ") in row (" + rows.size() + 1
						+ ").");
			} else if (!rows.get(0).isHeaderEqual(record)) {
				Assert.fail("Please specify the correct name of of the columns in all the table row."
						+ "Each row must have the same column name and the order should also be same.");
			}
		}

		rows.add(record);
		return this;
	}
	
	public TableRows append(TableRows records) {
		for(TableRow record : records.getRows()) {
			append(record);
		}
			
		return this;
	}
	
	public TableRows setEditable(boolean editable) {
		this.editable = editable;
		return this;
	}
	
	public boolean isEditable() {
		return editable;
	}

	public List<TableRow> getRows() {
		return rows;
	}
		
	public boolean contains(String value, int colIndex) {
		boolean exist = false;

		for (TableRow erow : rows) {
			if (erow.isValueExist(value, colIndex)) {
				exist = true;
				break;
			}
		}

		return exist;
	}
	
	@Override
	public String toString() {		
		return rows.toString();
	}

}
