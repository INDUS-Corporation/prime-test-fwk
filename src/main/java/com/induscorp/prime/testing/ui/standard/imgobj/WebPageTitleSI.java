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

import com.induscorp.prime.testing.ui.core.commons.UIObjectType;
import com.induscorp.prime.testing.ui.core.config.webbrowser.WebBrowser;
import com.induscorp.prime.testing.ui.core.objects.ImageObject;

/**
 * 
 * @author Madhav Krishna
 *
 */
public class WebPageTitleSI extends ImageObject {

	public WebPageTitleSI(String displayName, String image) {
		super(UIObjectType.webPageTitle, displayName, image);
	}

	@Override
	public WebPageTitleValidatorSI getValidator(WebBrowser browser, Region region) {
		return new WebPageTitleValidatorSI(browser, this, region);
	}

	@Override
	public String toString() {
		return getDisplayName();
	}

}
