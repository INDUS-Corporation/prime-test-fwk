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

import org.sikuli.script.Region;
import org.testng.Assert;

import com.induscorp.prime.testing.ui.core.config.webbrowser.WebBrowser;
import com.induscorp.prime.testing.ui.core.objects.UIObjectValidator;
import com.induscorp.prime.testing.ui.core.objects.validator.mechanisms.RowSelectionMechanism;

/**
 * 
 * @author Madhav Krishna
 *
 */
public abstract class TableValidator extends UIObjectValidator {
	private Table table;

	public TableValidator(WebBrowser browser, Table locator, Region region) {
		super(browser, locator, region);
		this.table = locator;
	}

	@Override
	public Table getUIObject() {
		return table;
	}

	public void validateRecordsPresent(TableRows records, int numRetries) {
		Assert.assertTrue(isRecordsPresent(records, numRetries), "All records are not presents in table. Records: " + records);
	}

	public void validateRecordsNotPresent(TableRows records, int numRetries) {
		Assert.assertFalse(isRecordsPresent(records, numRetries), "Some records are presents in table. Records: " + records);
	}

	public abstract boolean isRecordsPresent(TableRows records, int numRetries);

	public abstract void selectAllRecords(RowSelectionMechanism selMechanism, int numRetries);

	public abstract void selectRecords(TableRows recordsToBeSelected, RowSelectionMechanism selMechanism, int numRetries);

	public abstract void selectFirstRecord(RowSelectionMechanism selMechanism, int numRetries);

	public abstract void selectLastRecord(RowSelectionMechanism selMechanism, int numRetries);

	public abstract int getRecordCount(int numRetries);

	public abstract int getRowIndex(TableRow row, int numRetries);
	
	public abstract void clickRowCell(TableRow row, int cellIndex, int numRetries);
	
	public abstract void doubleClickRowCell(TableRow row, int cellIndex, int numRetries);

	public abstract void moveVScrollbarThumbGripDown(int numClicks, int numRetries);

	public abstract void moveVScrollbarThumbGripUp(int numClicks, int numRetries);

	public abstract void moveHScrollbarThumbGripRight(int numClicks, int numRetries);

	public abstract void moveHScrollbarThumbGripLeft(int numClicks, int numRetries);

}
