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
package com.induscorp.prime.testing.ui.core.objects.radio;

import org.sikuli.script.Region;

import com.induscorp.prime.testing.ui.core.commons.ItemMap;
import com.induscorp.prime.testing.ui.core.config.webbrowser.WebBrowser;
import com.induscorp.prime.testing.ui.core.objects.UIObjectValidator;

/**
 * 
 * @author Madhav Krishna
 *
 */
public abstract class RadioButtonGroupValidator extends UIObjectValidator {
	private RadioButtonGroup optionGroup;

	public RadioButtonGroupValidator(WebBrowser browser, RadioButtonGroup uiObject, Region region) {
		super(browser, uiObject, region);
		this.optionGroup = uiObject;
	}

	@Override
	public RadioButtonGroup getUIObject() {
		return optionGroup;
	}

	public abstract void validateDisabled(int numRetries);

	public abstract void validateEnabled(int numRetries);

	public abstract void selectOption(String value, String displayValue, int numRetries);

	public abstract void validateSelectedOption(String value, String displayValue, int numRetries);

	/**
	 * 
	 * @param options: ItemMap contains key: optionValue, value: optionDisplayValue
	 * @param numRetries
	 */
	public abstract void validateNotSelectedOptions(ItemMap<String, String> options, int numRetries);
}
