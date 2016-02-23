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

import com.induscorp.prime.testing.ui.core.objects.validator.mechanisms.TextValidationMechanism;

/**
 * 
 * @author Madhav Krishna
 *
 */
public class SearchCell {
	protected HeaderColumnSI column;
	protected Object value;
	protected ValueType valueType;
	protected TextValidationMechanism textValueValidationMechanism;

	public SearchCell(HeaderColumnSI column, Object value,
			ValueType valueType,
			TextValidationMechanism textValueValidationMechanism) {
		this.column = column;
		this.value = value;
		this.valueType = valueType;
		this.textValueValidationMechanism = textValueValidationMechanism;
	}

	public HeaderColumnSI getColumn() {
		return column;
	}

	public void setColumn(HeaderColumnSI column) {
		this.column = column;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public ValueType getValueType() {
		return valueType;
	}

	public void setValueType(ValueType valueType) {
		this.valueType = valueType;
	}

	public TextValidationMechanism getTextValueValidationMechanism() {
		return textValueValidationMechanism;
	}

	public void setTextValueValidationMechanism(
			TextValidationMechanism textValueValidationMechanism) {
		this.textValueValidationMechanism = textValueValidationMechanism;
	}

	@Override
	public String toString() {
		return "{Column=" + column.getDisplayName() + ", Value=" + value
				+ ", ValueType=" + valueType.name()
				+ ", TextValueValidationMechanism="
				+ textValueValidationMechanism + "}";
	}
}
