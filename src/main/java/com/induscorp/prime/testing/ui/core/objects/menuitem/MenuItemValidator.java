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
package com.induscorp.prime.testing.ui.core.objects.menuitem;

import org.sikuli.script.Region;

import com.induscorp.prime.testing.ui.core.commons.ItemList;
import com.induscorp.prime.testing.ui.core.config.webbrowser.WebBrowser;
import com.induscorp.prime.testing.ui.core.objects.UIObjectValidator;

/**
 * 
 * @author Madhav Krishna
 *
 */
public abstract class MenuItemValidator extends UIObjectValidator {
	private MenuItem menuItem;

	public MenuItemValidator(WebBrowser browser, MenuItem uiObject, Region region) {
		super(browser, uiObject, region);
		this.menuItem = uiObject;
	}

	@Override
	public MenuItem getUIObject() {
		return menuItem;
	}
	
	public abstract void validateDisabledItemsPresent(ItemList<String> disabledItems, int numRetries);

	public abstract void validateEnabledItemsPresent(ItemList<String> enabledItems, int numRetries);
}
