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
package com.induscorp.prime.testing.ui.core.objects.tab;

import org.sikuli.script.Region;

import com.induscorp.prime.testing.ui.core.commons.ItemSet;
import com.induscorp.prime.testing.ui.core.config.webbrowser.WebBrowser;
import com.induscorp.prime.testing.ui.core.objects.UIObjectValidator;
import com.induscorp.prime.testing.ui.core.objects.webpage.WebPage;

/**
 * 
 * @author Madhav Krishna
 *
 */
public abstract class TabSheetValidator extends UIObjectValidator {
	private TabSheet tabSheet;
	
	public TabSheetValidator(WebBrowser browser, TabSheet locator, Region region) {
		super(browser, locator, region);
		this.tabSheet = locator;
	}

	@Override
	public TabSheet getUIObject() {
		return this.tabSheet;
	}

	public void setComponent(TabSheet component) {
		this.tabSheet = component;
	}

	public abstract void selectTab(String tabName, int numRetries);
	
	public abstract void validateSelectedTab(WebPage webPage,
			String expectedSelectedTabName, int numRetries);

	public abstract void validateTabsPresent(WebPage webPage,
			ItemSet<String> allTabNames, int numRetries);

	public abstract void validateDisabledTabs(WebPage webPage,
			ItemSet<String> disabledTabNames, int numRetries);

	public abstract void validateEnabledTabsOnTabSheet(WebPage webPage, int numRetries);
}
