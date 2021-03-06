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
public abstract class DateTimeItemValidator extends UIObjectValidator {
	private DateTimeItem dateTimeItem;

	public DateTimeItemValidator(WebBrowser browser, DateTimeItem uiObject, Region region) {
		super(browser, uiObject, region);
		this.dateTimeItem = uiObject;
	}

	@Override
	public DateTimeItem getUIObject() {
		return this.dateTimeItem;
	}

	public abstract void validateDisabled(int numRetries);

	public abstract void validateEnabled(int numRetries);

	public abstract void typeDateTime(String date, String time, int numRetries);

	public abstract void selectDateTimeUsingDateTimePicker(String date, String time, int numRetries);

	public abstract void validateDateTime(String date, String time, TextValidationMechanism dateValidationMechanism, TextValidationMechanism timeValidationMechanism, int numRetries);
}