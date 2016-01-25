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
package com.induscorp.prime.testing.ui.core.objects.listbox;

import java.util.List;

import org.sikuli.script.Region;

import com.induscorp.prime.testing.ui.core.commons.ItemList;
import com.induscorp.prime.testing.ui.core.config.webbrowser.WebBrowser;
import com.induscorp.prime.testing.ui.core.objects.UIObjectValidator;
import com.induscorp.prime.testing.ui.core.objects.validator.mechanisms.TextValidationMechanism;

/**
 * 
 * @author Madhav Krishna
 *
 */
public abstract class ListBoxValidator extends UIObjectValidator {
	private ListBox comboBox;

	public ListBoxValidator(WebBrowser browser, ListBox uiObject, Region region) {
		super(browser, uiObject, region);
		this.comboBox = uiObject;
	}

	@Override
	public ListBox getUIObject() {
		return this.comboBox;
	}

	public abstract void validateDisabled(int numRetries);

	public abstract void validateEnabled(int numRetries);

	public abstract void validateSelectedItem(String expectedSelectedValue, TextValidationMechanism validationMechanism,
			int numRetries);

	public abstract String getSelectedItem(int numRetries);
	
	public abstract List<String> getSelectedItems(int numRetries);

	public abstract void selectFirstItem(int numRetries);

	public abstract void selectLastItem(int numRetries);

	public abstract void selectItem(String itemName, int numRetries);

	public abstract void selectItems(ItemList<String> itemsToBeSelected, int numRetries);

	public abstract void validateItemsPresent(ItemList<String> items, int numRetries);

	public abstract void validateItemsNotPresent(ItemList<String> items, int numRetries);
}
