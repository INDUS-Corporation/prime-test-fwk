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
package com.induscorp.prime.testing.ui.core.objects.textbox;

import org.sikuli.script.Region;

import com.induscorp.prime.testing.ui.core.config.webbrowser.WebBrowser;
import com.induscorp.prime.testing.ui.core.objects.NewTextLocation;
import com.induscorp.prime.testing.ui.core.objects.UIObjectValidator;
import com.induscorp.prime.testing.ui.core.objects.validator.mechanisms.TextValidationMechanism;

/**
 * 
 * @author Madhav Krishna
 *
 */
public abstract class TextBoxValidator extends UIObjectValidator {
	private TextBox textBox;

	public TextBoxValidator(WebBrowser browser, TextBox uiObject, Region region) {
		super(browser, uiObject, region);
		this.textBox = uiObject;
	}

	@Override
	public TextBox getUIObject() {
		return textBox;
	}

	public abstract void validateDisabled(int numRetries);

	public abstract void validateEnabled(int numRetries);

	public abstract void typeText(String textToType, NewTextLocation location, int numRetries);

	public abstract void validateValue(String expectedValue, TextValidationMechanism validationMechanism,
			int numRetries);
	
	public abstract String getValue(int numRetries);
}
