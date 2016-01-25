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
package com.induscorp.prime.testing.ui.core.objects.checkbox;

import org.sikuli.script.Region;

import com.induscorp.prime.testing.ui.core.commons.ItemList;
import com.induscorp.prime.testing.ui.core.config.webbrowser.WebBrowser;
import com.induscorp.prime.testing.ui.core.objects.UIObjectValidator;

/**
 * 
 * @author Madhav Krishna
 *
 */
public abstract class CheckBoxGroupValidator extends UIObjectValidator {
	private CheckBoxGroup checkBoxGroup;

	public CheckBoxGroupValidator(WebBrowser browser, CheckBoxGroup uiObject, Region region) {
		super(browser, uiObject, region);
		this.checkBoxGroup = uiObject;
	}

	@Override
	public CheckBoxGroup getUIObject() {
		return checkBoxGroup;
	}

	public abstract void validateCheckedAndEnabledCheckBoxes(ItemList<String> cbNames, int numRetries);

	public abstract void validateUncheckedAndEnabledCheckBoxes(ItemList<String> cbNames, int numRetries);

	public abstract void validateCheckedAndDisabledCheckBoxes(ItemList<String> cbNames, int numRetries);

	public abstract void validateUncheckedAndDisabledCheckBoxes(ItemList<String> cbNames, int numRetries);

	public abstract void validateDisabledCheckBoxes(ItemList<String> cbNames, int numRetries);

	public abstract void validateEnabledCheckBoxes(ItemList<String> cbNames, int numRetries);

	public abstract void validateAllCheckBoxesPresent(ItemList<String> cbNames, int numRetries);
}
