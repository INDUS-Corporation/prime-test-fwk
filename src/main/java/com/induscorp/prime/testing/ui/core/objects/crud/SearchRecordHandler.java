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

import java.util.Map;

import org.sikuli.script.Region;
import org.testng.Assert;

import com.induscorp.prime.testing.ui.core.config.webbrowser.WebBrowser;
import com.induscorp.prime.testing.ui.core.objects.UIObject;
import com.induscorp.prime.testing.ui.core.objects.table.Table;
import com.induscorp.prime.testing.ui.core.objects.webpage.WebPage;

/**
 * 
 * @author Madhav Krishna
 *
 */
public class SearchRecordHandler {
	private WebBrowser browser;
	private WebPage webPage;
	private SearchRecord searchRecord;

	public SearchRecordHandler(WebBrowser browser, WebPage webPage, SearchRecord searchRecord) {
		this.browser = browser;
		this.webPage = webPage;
		this.searchRecord = searchRecord;
	}
	
	public WebPage getWebPage() {
		return webPage;
	}

	public void performSearchAndSelectRecords(Region region, int numRetries) {
		Map<UIObject, ObjectPropertiesAndValues> compValueMap = searchRecord.getSearchRecord().getRocord();
		Assert.assertNotNull(compValueMap, "Please specify the component in which you want to search the information.");

		ObjectPropertiesAndValues propsAndValues;
		for (UIObject uiObj : compValueMap.keySet()) {
			propsAndValues = compValueMap.get(uiObj);
			switch (uiObj.getType()) {
			case table:
				((Table) uiObj).getValidator(browser, region).selectRecords(propsAndValues.getRows(),
						propsAndValues.getRowSelectionMechanism(), numRetries);
				break;
			default:
				break;
			}
		}
	}

	public void validateRecordPresence(Region region, int numRetries) {
		Map<UIObject, ObjectPropertiesAndValues> compValueMap = searchRecord.getSearchRecord().getRocord();
		Assert.assertNotNull(compValueMap, "Please specify the component in which you want to search the information.");

		ObjectPropertiesAndValues propsAndValues;
		for (UIObject uiObj : compValueMap.keySet()) {
			propsAndValues = compValueMap.get(uiObj);
			switch (uiObj.getType()) {
			case table:
				if (!((Table) uiObj).getValidator(browser, region).isRecordsPresent(propsAndValues.getRows(),
						numRetries)) {
					Assert.fail("Failed to search record in data table '" + uiObj.getDisplayName() + "'.");
				}
				break;
			default:
				break;
			}
		}
	}

	public boolean checkIfRecordPresent(Region region, int numRetries) {
		Map<UIObject, ObjectPropertiesAndValues> compValueMap = searchRecord.getSearchRecord().getRocord();
		Assert.assertNotNull(compValueMap, "Please specify the component in which you want to search the information.");

		int recordPresentCounter = 0;
		ObjectPropertiesAndValues propsAndValues;
		for (UIObject uiObj : compValueMap.keySet()) {
			propsAndValues = compValueMap.get(uiObj);
			switch (uiObj.getType()) {
			case table:
				((Table) uiObj).getValidator(browser, region).isRecordsPresent(propsAndValues.getRows(), numRetries);
				recordPresentCounter++;
				break;
			default:
				break;
			}
		}

		return (recordPresentCounter == compValueMap.size());
	}

	public boolean checkIfRecordExistUsingCriteria(Region region, int numRetries) {
		boolean exist = false;
		if (searchRecord.getInputRecord() != null) {
			searchRecord.getInputRecord().getRecordInputHandler(browser).enterRecordInfoIntoFields();
			searchRecord.getPostInputActions().getActionHandler(browser).performActions(region, numRetries);
		}

		try {
			validateRecordPresence(region, numRetries);
			exist = true;
		} catch (Throwable th) {
		}

		return exist;
	}
}
