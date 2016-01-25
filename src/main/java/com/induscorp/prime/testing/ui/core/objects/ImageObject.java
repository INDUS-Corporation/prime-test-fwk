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
package com.induscorp.prime.testing.ui.core.objects;

import org.sikuli.script.Region;

import com.induscorp.prime.testing.ui.core.commons.LocatorType;
import com.induscorp.prime.testing.ui.core.commons.UIObjectType;
import com.induscorp.prime.testing.ui.core.config.webbrowser.WebBrowser;

/**
 * 
 * @author Madhav Krishna
 *
 */
public class ImageObject extends UIObject {
	protected String image;

	public ImageObject(UIObjectType elemType, String displayName, String image) {
		super(LocatorType.IMAGE, elemType, displayName);
		this.image = image;
	}

	public ImageObjectValidator getValidator(WebBrowser browser, Region region) {
		return new ImageObjectValidator(browser, this, region);
	}

	public String getImage() {
		return image;
	}

	@Override
	public UIObject clone() {
		return new ImageObject(uiObjectType, getDisplayName(), image);
	}

	@Override
	public ImageObject updateLocatorParameterWithValue(String paramName, String value) {
		return this;	
	}

}
