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
package com.induscorp.prime.testing.ui.standard.domobj;

import org.sikuli.script.Region;

import com.induscorp.prime.testing.ui.core.commons.LocatorType;
import com.induscorp.prime.testing.ui.core.config.webbrowser.WebBrowser;
import com.induscorp.prime.testing.ui.core.objects.UIObject;
import com.induscorp.prime.testing.ui.core.objects.radio.RadioButtonGroup;

/**
 * 
 * @author Madhav Krishna
 *
 */
public class RadioButtonGroupSD extends RadioButtonGroup {
	protected String xpath;

	public RadioButtonGroupSD(String displayName, String xpath) {
		super(LocatorType.DOM, displayName);
		this.xpath = xpath;
	}

	@Override
	public RadioButtonGroupValidatorSD getValidator(WebBrowser browser, Region region) {
		return new RadioButtonGroupValidatorSD(browser, this, region);
	}

	public String getLocatorXPath() {
		return xpath;
	}

	@Override
	public UIObject clone() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RadioButtonGroupSD updateLocatorParameterWithValue(String paramName, String value) {
		String newXPath = xpath.replaceAll(":" + paramName, value);
		return new RadioButtonGroupSD(displayName, newXPath);
	}

}
