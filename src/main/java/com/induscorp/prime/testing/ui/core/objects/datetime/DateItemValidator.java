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
package com.induscorp.prime.testing.ui.core.objects.datetime;

import org.sikuli.script.Region;

import com.induscorp.prime.testing.ui.core.config.webbrowser.WebBrowser;
import com.induscorp.prime.testing.ui.core.objects.UIObjectValidator;
import com.induscorp.prime.testing.ui.core.objects.validator.mechanisms.TextValidationMechanism;

/**
 * 
 * @author Madhav Krishna
 *
 */
public abstract class DateItemValidator extends UIObjectValidator {
	private DateItem dateItem;

	public DateItemValidator(WebBrowser browser, DateItem uiObject, Region region) {
		super(browser, uiObject, region);
		this.dateItem = uiObject;
	}

	@Override
	public DateItem getUIObject() {
		return this.dateItem;
	}

	public abstract void validateDisabled(int numRetries);

	public abstract void validateEnabled(int numRetries);

	public abstract void typeDate(String date, int numRetries);

	public abstract void selectDateUsingDatePicker(String date, int numRetries);

	public abstract void validateDate(String date, TextValidationMechanism validationMechanism, int numRetries);
}