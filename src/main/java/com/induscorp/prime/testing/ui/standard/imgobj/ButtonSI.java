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
package com.induscorp.prime.testing.ui.standard.imgobj;

import org.sikuli.script.Region;
import org.testng.Assert;

import com.induscorp.prime.testing.ui.core.commons.LocatorType;
import com.induscorp.prime.testing.ui.core.config.webbrowser.WebBrowser;
import com.induscorp.prime.testing.ui.core.objects.ObjectLocation;
import com.induscorp.prime.testing.ui.core.objects.button.Button;

/**
 * 
 * @author Madhav Krishna
 *
 */
public class ButtonSI extends Button {
	protected String buttonImg;
	protected ObjectLocation buttonImgLocation;

	public ButtonSI(String displayName, String buttonImg, ObjectLocation buttonImgLocation) {
		super(LocatorType.IMAGE, displayName);
		this.buttonImg = buttonImg;
		this.buttonImgLocation = buttonImgLocation;
	}
	
	public String getButtonImage() {
		return buttonImg;
	}

	public ObjectLocation getButtonImageLocation() {
		return buttonImgLocation;
	}

	@Override
	public ButtonValidatorSI getValidator(WebBrowser browser, Region region) {
		return new ButtonValidatorSI(browser, this, region);
	}

	@Override
	public ButtonSI clone() {
		return null;
	}

	@Override
	public ButtonSI updateLocatorParameterWithValue(String paramName, String value) {
		Assert.fail("updateLocatorParameterWithValue() API is not implemented."); 
		return this;
	}

}
