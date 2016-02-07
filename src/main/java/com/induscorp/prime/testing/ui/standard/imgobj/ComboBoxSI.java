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
import com.induscorp.prime.testing.ui.core.objects.PullDownMenuInfo;
import com.induscorp.prime.testing.ui.core.objects.combobox.ComboBox;

/**
 * 
 * @author Madhav Krishna
 *
 */
public class ComboBoxSI extends ComboBox {
	protected String leftSideImg;
	protected String rightSideImg;
	protected ObjectLocation location;
	protected PullDownMenuInfo pullDownMenuInfo;
	protected boolean readOnly;
	protected boolean disabled;

	public ComboBoxSI(String displayName, String leftSideImg, String rightSideImg, ObjectLocation location,
			PullDownMenuInfo pullDownMenuInfo) {
		super(LocatorType.IMAGE, displayName);
		this.leftSideImg = leftSideImg;
		this.rightSideImg = rightSideImg;
		this.location = location;
		this.pullDownMenuInfo = pullDownMenuInfo;
		this.readOnly = true;
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

	public PullDownMenuInfo getPullDownMenuInfo() {
		return pullDownMenuInfo;
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
	public ComboBoxValidatorSI getValidator(WebBrowser browser, Region region) {
		return new ComboBoxValidatorSI(browser, this, region);
	}

	@Override
	public ComboBoxSI clone() {
		return new ComboBoxSI(displayName, leftSideImg, rightSideImg, location, pullDownMenuInfo);
	}

	@Override
	@Deprecated
	public ComboBoxSI updateLocatorParameterWithValue(String paramName, String value) {
		return this;
	}

}
