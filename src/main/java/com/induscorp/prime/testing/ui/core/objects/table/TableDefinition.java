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
public class TableDefinition {
	
	private boolean editable;
	private boolean searchFilterEnabled;
	private boolean headerPresent;
	private boolean listGridHeightDynamic;
	private HeaderDefinition headerDefinition;
	private RecordDefinition recordDefinition;
	
	public TableDefinition() {
		editable = false;
		searchFilterEnabled = false;
		headerPresent = true;
		listGridHeightDynamic = false;
		headerDefinition = null;
		recordDefinition = null;
	}

	public boolean isEditable() {
		return editable;
	}

	public TableDefinition setEditable(boolean editable) {
		this.editable = editable;
		return this;
	}
	
	public TableDefinition setSearchFilterEnabled(boolean enabled) {
		searchFilterEnabled = enabled;
		return this;
	}
	
	public boolean isSearchFilterEnabled() {
		return searchFilterEnabled;
	}

	public boolean isHeaderPresent() {
		return headerPresent;
	}

	public TableDefinition setHeaderPresent(boolean headerPresent) {
		this.headerPresent = headerPresent;
		return this;
	}

	public boolean isListGridHeightDynamic() {
		return listGridHeightDynamic;
	}

	public TableDefinition setListGridHeightDynamic(boolean listGridHeightDynamic) {
		this.listGridHeightDynamic = listGridHeightDynamic;
		return this;
	}

	public HeaderDefinition getHeaderDefinition() {
		return headerDefinition;
	}

	public TableDefinition setHeaderDefinition(HeaderDefinition headerDefinition) {
		this.headerDefinition = headerDefinition;
		return this;
	}

	public RecordDefinition getRecordDefinition() {
		return recordDefinition;
	}

	public TableDefinition setRecordDefinition(RecordDefinition recordDefinition) {
		this.recordDefinition = recordDefinition;
		return this;
	}
	

}
