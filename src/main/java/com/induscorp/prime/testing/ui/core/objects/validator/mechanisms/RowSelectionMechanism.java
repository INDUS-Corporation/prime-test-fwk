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
package com.induscorp.prime.testing.ui.core.objects.validator.mechanisms;

/**
 * 
 * @author Madhav Krishna
 *
 */
public class RowSelectionMechanism {
	private SelectionMechanism selectionMechanism;
	private Integer columnIndex;

	public static enum SelectionMechanism {
		clickRowCell, doubleClickRowCell, ctrlClickRowCellForMultiSelect
	};

	/**
	 * 
	 * @param mechanism
	 *            Mechanism used to select the row in a grid or combobox.
	 */
	public RowSelectionMechanism(SelectionMechanism mechanism) {
		this.selectionMechanism = mechanism;
		this.columnIndex = 0;
	}
	
	/**
	 * 
	 * @param mechanism
	 *            Mechanism used to select the row in a grid or combobox.
	 * @param columnIndex
	 *            Column index at which the selection mechanism will get
	 *            applied.
	 */
	public RowSelectionMechanism(SelectionMechanism mechanism,
			Integer columnIndex) {
		this.selectionMechanism = mechanism;
		this.columnIndex = columnIndex;
	}

	public SelectionMechanism getSelectionMechanism() {
		return selectionMechanism;
	}

	public Integer getColumnIndex() {
		return columnIndex;
	}
}
