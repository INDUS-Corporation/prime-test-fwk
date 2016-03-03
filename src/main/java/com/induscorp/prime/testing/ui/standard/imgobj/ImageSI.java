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
import com.induscorp.prime.testing.ui.core.objects.image.Image;

/**
 * 
 * @author Madhav Krishna
 *
 */
public class ImageSI extends Image {
	protected String image;
	protected ObjectLocation imageLocation;

	public ImageSI(String displayName, String image, ObjectLocation imageLocation) {
		super(LocatorType.IMAGE, displayName);
		this.image = image;
		this.imageLocation = imageLocation;
	}

	public String getImage() {
		return image;
	}

	public ObjectLocation getImageLocation() {
		return imageLocation;
	}

	@Override
	public ImageValidatorSI getValidator(WebBrowser browser, Region region) {
		return new ImageValidatorSI(browser, this, region);
	}

	@Override
	public ImageSI clone() {
		return null;
	}

	@Override
	public ImageSI updateLocatorParameterWithValue(String paramName, String value) {
		Assert.fail("updateLocatorParameterWithValue() API is not implemented.");
		return this;
	}

}
