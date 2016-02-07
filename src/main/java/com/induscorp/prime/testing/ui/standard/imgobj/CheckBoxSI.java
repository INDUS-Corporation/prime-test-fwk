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
import com.induscorp.prime.testing.ui.core.objects.checkbox.CheckBox;

/**
 * 
 * @author Madhav Krishna
 *
 */
public class CheckBoxSI extends CheckBox {
	protected String checkBoxImg;
	protected ObjectLocation checkBoxImgLocation;

	public CheckBoxSI(String displayName, String checkBoxImg, ObjectLocation checkBoxImgLocation) {
		super(LocatorType.IMAGE, displayName);
		this.checkBoxImg = checkBoxImg;
		this.checkBoxImgLocation = checkBoxImgLocation;
	}

	public String getCheckBoxImage() {
		return checkBoxImg;
	}

	public ObjectLocation getCheckBoxImageLocation() {
		return checkBoxImgLocation;
	}

	@Override
	public CheckBoxValidatorSI getValidator(WebBrowser browser, Region region) {
		return new CheckBoxValidatorSI(browser, this, region);
	}

	@Override
	public CheckBoxSI clone() {
		return null;
	}

	@Override
	public CheckBoxSI updateLocatorParameterWithValue(String paramName, String value) {
		Assert.fail("updateLocatorParameterWithValue() API is not implemented.");
		return this;
	}

}
