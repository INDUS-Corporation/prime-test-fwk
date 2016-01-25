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

import java.lang.reflect.Constructor;

import org.openqa.selenium.By;
import org.sikuli.script.Region;

import com.induscorp.prime.testing.ui.core.commons.UIObjectType;
import com.induscorp.prime.testing.ui.core.commons.LocatorType;
import com.induscorp.prime.testing.ui.core.config.webbrowser.WebBrowser;

/**
 * 
 * @author Madhav Krishna
 *
 */
public class DOMObject extends UIObject {
	protected String xpath;

	public DOMObject(String displayName, String xpath) {
		super(LocatorType.DOM, UIObjectType.locator, displayName);
		this.xpath = xpath;
	}
	
	public DOMObject(UIObjectType elemType, String displayName, String xpath) {
		super(LocatorType.DOM, elemType, displayName);
		this.xpath = xpath;
	}

	public String getLocatorAsXPath() {
		return xpath;
	}

	public By getLocatorAsBy() {
		return By.xpath(xpath);
	}

	@Override
	public DOMObject updateLocatorParameterWithValue(String paramName, String value) {
		xpath = xpath.replaceAll(":" + paramName, value);
		return this;
	}

	@Override
	public DOMObjectValidator getValidator(WebBrowser browser, Region region) {
		return new DOMObjectValidator(browser, this, region);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public DOMObject clone() {
		try {
			Class[] paramTypes = new Class[2];
			paramTypes[0] = String.class;
			paramTypes[1] = String.class;
			Constructor ctor = this.getClass().getConstructor(paramTypes);

			Object[] paramValues = new Object[2];
			paramValues[0] = displayName;
			paramValues[1] = xpath;

			return (DOMObject) ctor.newInstance(paramValues);
		} catch (Exception ex) {
			new Throwable(ex);
		}
		return null;
	}
}
