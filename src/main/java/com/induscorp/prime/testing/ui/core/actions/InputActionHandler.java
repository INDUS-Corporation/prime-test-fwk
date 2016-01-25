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
package com.induscorp.prime.testing.ui.core.actions;

import org.sikuli.script.Region;
import org.testng.Assert;
import org.testng.Reporter;

import com.induscorp.prime.testing.ui.core.config.webbrowser.WebBrowser;
import com.induscorp.prime.testing.ui.core.events.InputEvent;
import com.induscorp.prime.testing.ui.core.objects.UIObject;
import com.induscorp.prime.testing.ui.core.objects.tab.Tab;
import com.induscorp.prime.testing.ui.core.objects.webpage.WebPage;
import com.induscorp.prime.testing.ui.standard.imgobj.WebPageTitleSI;

/**
 * 
 * @author Madhav Krishna
 *
 */
public class InputActionHandler {
	private InputActions inputActions;
	private WebBrowser browser;

	public InputActionHandler(WebBrowser browser, InputActions inputActions) {
		this.browser = browser;
		this.inputActions = inputActions;
	}

	public InputActions getInputActions() {
		return inputActions;
	}

	public void setInputActions(InputActions inputActions) {
		this.inputActions = inputActions;
	}

	/**
	 * Based on the web page recognition parameters, this method checks whether
	 * the web page is opened/presence.
	 * 
	 * @return if web page is opened/present then returns true else returns
	 *         false.
	 */
	private boolean checkWebPagePresence(WebPage expectedWebPage, Region region, int numRetries) {
		boolean webPagePresent = true;
		// Reporter.log("Going to check WebPage presence - "
		// + expectedWebPage.getTitle());

		// First check if the opened screen contains all the recognition params
		try {
			for (UIObject item : inputActions.getPageRecognitionItems()) {
				if (item instanceof Tab) {
					((Tab) item).getValidator(browser, region).validateSelected(numRetries);
				} else {
					item.getValidator(browser, region).validatePresent(numRetries);
				}
			}
		} catch (Throwable ex) {
			webPagePresent = false;
			Reporter.log("Error during checking of web page presence. Message: " + ex.getMessage());
		}

		// if (webPagePresent) {
		// Reporter.log("WebPage present - " + expectedWebPage.getTitle());
		// }

		return webPagePresent;
	}

	/**
	 * This method is used to open the web page only if it is not already
	 * opened.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void performActions(Region region, int numRetries) {

		Reporter.log("Going to perform input action '" + inputActions.getName() + "' on web page - "
				+ inputActions.getWebPage().getTitle().getDisplayName());

		UIObject item;
		InputEvent itemEvent;

		WebPage expectedWebPage = new WebPage(new WebPageTitleSI(
				inputActions.getWebPage().getTitle() + ":> " + inputActions.getReadablePath(), null), null, null);

		for (InputAction inputAction : inputActions.getActions()) {
			item = inputAction.getItem();
			itemEvent = inputAction.getEvent();
			Assert.assertNotNull(item, "Found null item in InputActions - " + inputActions.getName());
			Assert.assertNotNull(itemEvent, "Found null input event in InputActions - " + inputActions.getName());

			item.getValidator(browser, region).performAction(itemEvent, numRetries);
		}

		if (checkWebPagePresence(expectedWebPage, region, numRetries)) {
			Reporter.log("Input action '" + inputActions.getName() + "' performed successfully on web page - "
					+ inputActions.getWebPage().getTitle().getDisplayName());
		} else {
			Assert.fail("Failed to perform input action '" + inputActions.getName() + "' on web page - "
					+ inputActions.getWebPage().getTitle().getDisplayName() + ". Expected web page: "
					+ expectedWebPage.getTitle().getDisplayName());

		}

	}
}