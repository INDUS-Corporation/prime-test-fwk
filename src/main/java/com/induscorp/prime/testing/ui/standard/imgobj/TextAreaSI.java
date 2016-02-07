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
import com.induscorp.prime.testing.ui.core.objects.ObjectLocation;
import com.induscorp.prime.testing.ui.core.objects.textarea.TextArea;

/**
 * 
 * @author Madhav Krishna
 *
 */
public class TextAreaSI extends TextArea {
	protected String leftSideImg;
	protected String rightSideImg;
	protected ObjectLocation location;
	protected boolean readOnly;
	protected boolean disabled;

	public TextAreaSI(String displayName, String leftSideImg, String rightSideImg, ObjectLocation location) {
		super(LocatorType.IMAGE, displayName);
		this.leftSideImg = leftSideImg;
		this.rightSideImg = rightSideImg;
		this.location = location;
		this.readOnly = false;
		this.disabled = false;
	}

	public String getLeftSideImage() {
		return leftSideImg;
	}

	public String getRightSideImage() {
		return rightSideImg;
	}

	public ObjectLocation getLocation() {
		return location;
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	@Override
	public TextAreaValidatorSI getValidator(WebBrowser browser, Region region) {
		return new TextAreaValidatorSI(browser, this, region);
	}

	@Override
	public TextAreaSI clone() {
		return new TextAreaSI(displayName, leftSideImg, rightSideImg, location);
	}

	@Override
	@Deprecated
	public TextAreaSI updateLocatorParameterWithValue(String paramName, String value) {
		return this;
	}

}
