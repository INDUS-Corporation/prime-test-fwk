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
package com.induscorp.prime.testing.ui.core.utils;

import com.induscorp.prime.testing.ui.core.commons.ItemList;
import com.induscorp.prime.testing.ui.core.objects.table.TableRow;
import com.induscorp.prime.testing.ui.core.objects.table.TableRows;

/**
 * 
 * @author Madhav Krishna
 *
 */
public class TableUtil {
	
	public static TableRows prepareTableRows(ItemList<String> items) {
		TableRows tableRows = new TableRows();
		if(items.getItems().contains("")) {
			tableRows.append(new TableRow().append("0", ""));
		} else {		
			for(String item : items.getItems()) {
				tableRows.append(new TableRow().append("0", item));
			}
		}
		
		return tableRows;
	}
	
	public static TableRow prepareTableRow(String item) {
		return new TableRow().append("0", item);
	}
}
