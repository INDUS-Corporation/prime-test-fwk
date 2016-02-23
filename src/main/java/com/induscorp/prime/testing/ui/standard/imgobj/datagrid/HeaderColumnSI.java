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
package com.induscorp.prime.testing.ui.standard.imgobj.datagrid;

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
public class HeaderColumnSI extends Button {
	protected String columnImg;
	protected ObjectLocation columnImgLocation;

	public HeaderColumnSI(String displayName, String columnImg, ObjectLocation columnImgLocation) {
		super(LocatorType.IMAGE, displayName);
		this.columnImg = columnImg;
		this.columnImgLocation = columnImgLocation;
	}
	
	public String getColumnImage() {
		return columnImg;
	}

	public ObjectLocation getColumnImageLocation() {
		return columnImgLocation;
	}

	@Override
	public HeaderColumnValidatorSI getValidator(WebBrowser browser, Region region) {
		return new HeaderColumnValidatorSI(browser, this, region);
	}

	@Override
	public HeaderColumnSI clone() {
		return null;
	}

	@Override
	public HeaderColumnSI updateLocatorParameterWithValue(String paramName, String value) {
		Assert.fail("updateLocatorParameterWithValue() API is not implemented."); 
		return this;
	}

}
