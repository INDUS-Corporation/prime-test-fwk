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

import com.induscorp.prime.testing.ui.core.config.webbrowser.WebBrowser;
import com.induscorp.prime.testing.ui.core.objects.UIObjectValidator;

/**
 * 
 * @author Madhav Krishna
 *
 */
public abstract class CheckBoxValidator extends UIObjectValidator {
	private CheckBox checkBox;

	public CheckBoxValidator(WebBrowser browser, CheckBox uiObject, Region region) {
		super(browser, uiObject, region);
		this.checkBox = uiObject;
	}

	@Override
	public CheckBox getUIObject() {
		return checkBox;
	}

	public abstract boolean isCheckBoxChecked(int numRetries);

	public abstract void validateCheckBoxChecked(int numRetries);

	public abstract void validateCheckBoxUnchecked(int numRetries);

	public abstract void checkAndValidateChecked(int numRetries);

	public abstract void uncheckAndValidateUnchecked(int numRetries);
}
