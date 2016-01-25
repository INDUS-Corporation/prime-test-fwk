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
package com.induscorp.prime.testing.ui.core.objects.crud;

import java.util.ArrayList;
import java.util.List;

import com.induscorp.prime.testing.ui.core.objects.table.TableRows;
import com.induscorp.prime.testing.ui.core.objects.validator.mechanisms.RowSelectionMechanism;

/**
 * 
 * @author Madhav Krishna
 *
 */
public class ObjectPropertiesAndValues {
	private boolean readOnly;
	private boolean disabled;
	private boolean valueAlreadyFilled;
	private List<String> validValues;
	private TableRows rows;
	private RowSelectionMechanism rowSelectionMechanism;
	private SearchRecord searchRecord;

	public ObjectPropertiesAndValues() {
		readOnly = false;
		disabled = false;
		valueAlreadyFilled = false;
		validValues = new ArrayList<String>();
	}

	public ObjectPropertiesAndValues setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
		return this;
	}

	public ObjectPropertiesAndValues setDisabled(boolean disabled) {
		this.disabled = disabled;
		return this;
	}

	public ObjectPropertiesAndValues addValidValue(String value) {
		validValues.add(value);
		return this;
	}

	public List<String> getValidValues() {
		return validValues;
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public boolean isEditable() {
		return !(readOnly || disabled);
	}

	public ObjectPropertiesAndValues setValueAlreadyFilled(
			boolean valueAlreadyFilled) {
		this.valueAlreadyFilled = valueAlreadyFilled;
		return this;
	}

	public boolean isValueAlreadyFilled() {
		return this.valueAlreadyFilled;
	}

	public TableRows getRows() {
		return rows;
	}

	public ObjectPropertiesAndValues setRows(TableRows rows,
			RowSelectionMechanism rowSelectionMechanism) {
		this.rows = rows;
		this.rowSelectionMechanism = rowSelectionMechanism;
		return this;
	}
		
	public RowSelectionMechanism getRowSelectionMechanism() {
		return rowSelectionMechanism;
	}

	public SearchRecord getSearchRecord() {
		return searchRecord;
	}

	public ObjectPropertiesAndValues setSearchRecord(
			SearchRecord searchRecord) {
		this.searchRecord = searchRecord;
		return this;
	}
}
