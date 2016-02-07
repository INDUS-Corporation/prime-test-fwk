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
import com.induscorp.prime.testing.ui.core.objects.listbox.ListBox;

/**
 * 
 * @author Madhav Krishna
 *
 */
public class ListBoxSI extends ListBox {
	protected int width;
	protected int height;
	protected ObjectLocation location;
	protected boolean readOnly;
	protected boolean disabled;

	public ListBoxSI(String displayName, int width, int height, ObjectLocation location) {
		super(LocatorType.IMAGE, displayName);
		this.width = width;
		this.height = height;
		this.location = location;
		this.readOnly = true;
		this.disabled = false;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public ObjectLocation getLocation() {
		return location;
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public ListBoxSI setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
		return this;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public ListBoxSI setDisabled(boolean disabled) {
		this.disabled = disabled;
		return this;
	}

	@Override
	public ListBoxValidatorSI getValidator(WebBrowser browser, Region region) {
		return new ListBoxValidatorSI(browser, this, region);
	}

	@Override
	public ListBoxSI clone() {
		return new ListBoxSI(displayName, width, height, location);
	}

	@Override
	@Deprecated
	public ListBoxSI updateLocatorParameterWithValue(String paramName, String value) {
		return this;
	}

}
