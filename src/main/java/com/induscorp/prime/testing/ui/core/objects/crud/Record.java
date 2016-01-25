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
package com.induscorp.prime.testing.ui.core.objects.crud;

import java.util.LinkedHashMap;
import java.util.Map;

import com.induscorp.prime.testing.ui.core.config.webbrowser.WebBrowser;
import com.induscorp.prime.testing.ui.core.objects.UIObject;

/**
 * 
 * @author Madhav Krishna
 *
 */
public class Record {
	private Map<UIObject, ObjectPropertiesAndValues> uiObjValues;

	public Record() {
		uiObjValues = new LinkedHashMap<UIObject, ObjectPropertiesAndValues>();
	}

	public Record append(UIObject component, ObjectPropertiesAndValues propertiesAndValues) {
		uiObjValues.put(component, propertiesAndValues);
		return this;
	}

	public Map<UIObject, ObjectPropertiesAndValues> getRocord() {
		return uiObjValues;
	}

	public RecordInputHandler getRecordInputHandler(WebBrowser browser) {
		return new RecordInputHandler(browser, this);
	}

	public WebPageInfoRecognizer getWebPageInfoRecognizer(WebBrowser browser) {
		return new WebPageInfoRecognizer(browser, this);
	}

	/**
	 * Updates the parameters (Format: ":paramName") with the value in each
	 * locator string present in component. (NOTE: It creates the new object for
	 * component and destroys the older one.)
	 * 
	 * @param paramName
	 * @param value
	 */
	public void updateLocatorParameterWithValue(String paramName, String value) {
		LinkedHashMap<UIObject, ObjectPropertiesAndValues> compValuesNew = new LinkedHashMap<UIObject, ObjectPropertiesAndValues>();
		UIObject clonedComp;
		for (UIObject comp : uiObjValues.keySet()) {
			clonedComp = comp.clone();
			clonedComp.updateLocatorParameterWithValue(paramName, value);
			compValuesNew.put(clonedComp, uiObjValues.get(comp));
		}

		uiObjValues.clear();
		uiObjValues = compValuesNew;
	}
}
