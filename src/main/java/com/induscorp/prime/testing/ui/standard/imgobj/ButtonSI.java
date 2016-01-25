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

import com.induscorp.prime.testing.ui.core.commons.LocatorType;
import com.induscorp.prime.testing.ui.core.config.webbrowser.WebBrowser;
import com.induscorp.prime.testing.ui.core.objects.UIObject;
import com.induscorp.prime.testing.ui.core.objects.button.Button;

/**
 * 
 * @author Madhav Krishna
 *
 */
public class ButtonSI extends Button {
	protected String topLeftCornerImg;
	protected String bottomRightCornerImg;
	protected String buttonName;
	
	enum ImageRecognitionBy {
		textString, image
	}

	public ButtonSI(String displayName, String topLeftCornerImg, String bottomRightCornerImg, ImageRecognitionBy buttonRecognitionBy,
			String recognitionString) {
		super(LocatorType.IMAGE, displayName);
	}

	@Override
	public ButtonValidatorSI getValidator(WebBrowser browser, Region region) {
		return new ButtonValidatorSI(browser, this, region);
	}

	@Override
	public UIObject clone() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UIObject updateLocatorParameterWithValue(String paramName, String value) {
		// TODO Auto-generated method stub
		return this;
	}

}
