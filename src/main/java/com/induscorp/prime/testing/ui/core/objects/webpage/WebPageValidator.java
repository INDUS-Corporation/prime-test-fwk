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
package com.induscorp.prime.testing.ui.core.objects.webpage;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

import com.induscorp.prime.testing.ui.core.commons.UIObjectType;
import com.induscorp.prime.testing.ui.core.config.webbrowser.WebBrowser;
import com.induscorp.prime.testing.ui.core.events.InputEvent;
import com.induscorp.prime.testing.ui.core.objects.UIObject;
import com.induscorp.prime.testing.ui.core.objects.UIObjectValidator;
import com.induscorp.prime.testing.ui.core.objects.scrollbar.Scrollbar;
import com.induscorp.prime.testing.ui.core.objects.tab.Tab;

/**
 * 
 * @author Madhav Krishna
 *
 */
public class WebPageValidator extends UIObjectValidator {
	private WebPage webPage;

	public WebPageValidator(WebBrowser browser, WebPage element) {
		super(browser, element, null);
		this.webPage = element;
	}

	@Override
	public WebPage getUIObject() {
		return webPage;
	}

	public void setComponent(WebPage component) {
		webPage = component;
	}

	public void validateWebPagePresent(int numRetries) {
		if (!isPresent(numRetries)) {
			Assert.fail("Web page (" + webPage.getTitle().getDisplayName()
					+ ") is not recognized as per the provided recognition parameters.");
		}
	}

	/**
	 * Based on the web page recognition parameters, this method checks whether
	 * the web page is opened/presence.
	 * 
	 * @return if web page is opened/present then returns true else returns
	 *         false.
	 */
	protected boolean checkWebPagePresence() {
		boolean webPagePresent = isWebPageInputParamsValid();

		// First check if the opened screen contains all the recognition params
		try {
			// check the path element
			UIObject item;

			for (PathItem pathItem : webPage.getLaunchPath().getPath()) {
				item = pathItem.getItem();
				Assert.assertNotNull(item, "Found null item in WebPage(" + webPage.getTitle().getDisplayName()
						+ ")->launchPath->pathItem");

				if (item instanceof Tab) {
					((Tab) item).getValidator(browser, null).validateSelected(0);
				}
			}

			// check the page recognition params
			for (UIObject item1 : webPage.getPageRecognitionItems().getItems()) {
				item1.getValidator(browser, null).validatePresent(5);
			}
		} catch (Throwable ex) {
			webPagePresent = false;
			Reporter.log("Error during checking of web page presence. ErrorMessage: " + ex.getMessage());
		}

		return webPagePresent;
	}

	/**
	 * This method is used to open the web page only if it is not already
	 * opened.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void openWebPage() {
		if (!checkWebPagePresence()) {
			Reporter.log("Going to open web page - " + webPage.getTitle().getDisplayName());

			// open web page
			UIObject item;
			InputEvent itemEvent;

			for (PathItem pathItem : webPage.getLaunchPath().getPath()) {
				item = pathItem.getItem();
				itemEvent = pathItem.getEvent();
				Assert.assertNotNull(item, "Found null item in WebPage(" + webPage.getTitle().getDisplayName()
						+ ")->launchPath->pathItem");
				Assert.assertNotNull(itemEvent, "Found null input event in WebPage("
						+ webPage.getTitle().getDisplayName() + ")->launchPath->pathItem");
				if (item.getType() == UIObjectType.tab) {
					((Tab) item).getValidator(browser, null).selectTab(0);
				} else {
					item.getValidator(browser, null).performAction(itemEvent, 0);
				}

				browser.waitForSeconds(1);
			}

			if (checkWebPagePresence()) {
				Reporter.log("Web page (" + webPage.getTitle().getDisplayName() + ") opened successfully.");
				webPage.setStatus(WebPageStatus.Started);
			} else {
				Reporter.log("ERROR: Failed to open web page (" + webPage.getTitle().getDisplayName() + ").");
			}
		} else {
			webPage.setStatus(WebPageStatus.Started);
		}
	}

	/**
	 * Validates the input parameters of the web page.
	 * 
	 * @return if input params are good then returns true else false.
	 */
	private boolean isWebPageInputParamsValid() {
		boolean valid = true;
		Assert.assertNotNull(webPage.getTitle(), "Please specify the web page title.");
		Assert.assertNotNull(webPage.getTitle().getDisplayName(), "Please specify the display name in web page title.");

		if (webPage.getLaunchPath() == null && webPage.getPageRecognitionItems() == null) {
		} else if (webPage.getLaunchPath() == null || webPage.getLaunchPath().getPath().size() < 1) {
			valid = false;
			Assert.fail("Please specify the web page launch path.");
		} else
			if (webPage.getPageRecognitionItems() == null || webPage.getPageRecognitionItems().getItems().size() < 1) {
			valid = false;
			Assert.fail("Please specify the web page recognition items.");
		}

		return valid;
	}

	@Override
	public boolean isPresent(int numRetries) {
		boolean present = false;
		present = checkWebPagePresence();

		return present;
	}

	@Override
	public boolean isVisible(int numRetries) {
		return isPresent(numRetries);
	}

	@Override
	public void click(int numRetries) {
		Assert.fail("Web page click not supported.");
	}

	@Override
	public void doubleClick(int numRetries) {
		Assert.fail("Web page double click not supported.");
	}

	@Override
	public void rightClick(int numRetries) {
		Assert.fail("Web page right click not supported.");
	}

	@Override
	public WebPageValidator scrollElementOnViewport(Scrollbar scrollbar) {
		Assert.fail("Web page scrollElementOnViewport not supported.");
		return this;
	}

	@Override
	public void clickAndHold(int numRetries) {
		Assert.fail("Web page clickAndHold not supported.");
	}

	@Override
	public void release(int numRetries) {
		Assert.fail("Web page release not supported.");
	}

	@Override
	public void performKeyDown(Keys keys, int numRetries) {
		Assert.fail("Web page performKeyDown not supported.");
	}

	@Override
	public void performKeyUp(Keys keys, int numRetries) {
		Assert.fail("Web page performKeyUp not supported.");
	}

	@Override
	public void performKeyPressed(Keys keys, int numRetries) {
		Assert.fail("Web page performKeyPressed not supported.");
	}

	@Override
	public void typeText(String text, int numRetries) {
		Assert.fail("Web page typeText not supported.");
	}

	@Override
	public WebElement findElement(int numRetries) {	
		Assert.fail("findElement operation is not applicable.");
		return null;
	}

	@Override
	public WebElement findElementNoException(int numRetries) {
		Assert.fail("findElementNoException operation is not applicable.");
		return null;
	}

	@Override
	public List<WebElement> findElements(int numRetries) {
		Assert.fail("findElements operation is not applicable.");
		return null;
	}
}
