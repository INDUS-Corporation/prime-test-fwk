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

import java.util.List;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.Region;
import org.testng.Assert;

import com.google.common.base.Function;
import com.induscorp.prime.testing.ui.core.config.webbrowser.WebBrowser;
import com.induscorp.prime.testing.ui.core.objects.scrollbar.Scrollbar;

/**
 * 
 * @author Madhav Krishna
 *
 */
public class DOMObjectValidator extends UIObjectValidator {
	private DOMObject domObject;

	public DOMObjectValidator(WebBrowser browser, DOMObject domObject, Region region) {
		super(browser, domObject, region);
		this.domObject = domObject;
	}

	@Override
	public DOMObject getUIObject() {
		return domObject;
	}

	/**
	 * Returns the attribute value of the first element.
	 * 
	 * @param attributeName
	 * @param numRetries
	 * @return
	 */
	public String getAttributeValue(String attributeName, int numRetries) {
		try {
			WebElement webElem = findElement(numRetries);
			return webElem.getAttribute(attributeName);
		} catch (Throwable th) {
			Assert.fail("Failed to get attribute (name='" + attributeName + "') value for element '"
					+ domObject.getDisplayName() + "'.", th);
		}
		return null;
	}

	/**
	 * Finds first element with polling and it polls after 2 seconds for
	 * numRetries times.
	 * 
	 * @param numRetries
	 * @return
	 */
	@Override
	public WebElement findElement(int numRetries) {
		WebElement webElem = null;
		for (int i = 0; i <= numRetries; i++) {
			try {
				// browser.getSeleniumWebDriver().manage().timeouts().pageLoadTimeout(30,
				// TimeUnit.SECONDS);
				// browser.getSeleniumWebDriver().manage().timeouts().implicitlyWait(10,
				// TimeUnit.SECONDS);
				webElem = browser.getSeleniumWebDriver().findElement(domObject.getLocatorAsBy());
				Assert.assertNotNull(webElem, "Unable to find element '" + domObject.getDisplayName() + "'.");
				// System.out.println(domObject.getDisplayName() + ", DISPLAYED:
				// " + webElem.isDisplayed() + ", " + webElem.isEnabled());
				// Assert.assertTrue(webElem.isDisplayed(), "Unable to find
				// element '" + domObject.getDisplayName() + "'. Reason - Not
				// displayed.");
				break;
			} catch (Throwable th) {
				if (i == numRetries) {
					Assert.fail("Unable to find element '" + domObject.getDisplayName()
							+ "'. Reason timeout(waited for " + (numRetries * 2) + " seconds).", th);
					break;
				}
			}
			browser.waitForSeconds(2);
		}
		return webElem;
	}

	public void waitForPageLoad() {
		WebDriverWait wait = new WebDriverWait(browser.getSeleniumWebDriver(), 30);
		wait.until(new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				String readyState = String.valueOf(((JavascriptExecutor) browser.getSeleniumWebDriver())
						.executeScript("return document.readyState"));
				// System.out.println("Current Window State: " + readyState);
				return "complete".equals(readyState);
			}
		});
	}

	/**
	 * Finds first element with polling and it polls after 2 seconds for
	 * numRetries times. It does not throw any exception
	 * 
	 * @param numRetries
	 * @return
	 */
	@Override
	public WebElement findElementNoException(int numRetries) {
		WebElement webElem = null;
		for (int i = 0; i <= numRetries; i++) {
			try {
				webElem = browser.getSeleniumWebDriver().findElement(domObject.getLocatorAsBy());
				if (webElem != null) {
					break;
				}
			} catch (Throwable th) {
				webElem = null;
				if (i == numRetries) {
					break;
				}
			}
			browser.waitForSeconds(2);
		}
		return webElem;
	}

	/**
	 * This returns all the elements based on the locator. It waits for the
	 * configured timeout if the element is not present. Performs polling
	 * numRetries times.
	 * 
	 * @param numRetries
	 * @return
	 */
	@Override
	public List<WebElement> findElements(int numRetries) {
		List<WebElement> webElems = null;
		for (int i = 0; i <= numRetries; i++) {
			try {
				webElems = browser.getSeleniumWebDriver().findElements(domObject.getLocatorAsBy());
				Assert.assertNotNull(webElems, "Unable to find elements for '" + domObject.getDisplayName() + "'.");
				Assert.assertTrue(webElems.size() > 0,
						"Unable to find elements for '" + domObject.getDisplayName() + "' locator.");
				break;
			} catch (Throwable th) {
				if (i == numRetries) {
					Assert.fail("Unable to find elements for '" + uiObject.getDisplayName()
							+ "' locator. Reason timeout(waited for " + (numRetries * 2) + " seconds).", th);
					break;
				}
			}
			browser.waitForSeconds(2);
		}
		return webElems;
	}

	@Override
	public DOMObjectValidator scrollElementOnViewport(Scrollbar scrollbar) {
		if (scrollbar == null) {
			return this;
		}
		scrollbar.scrollElementToViewport(browser, getUIObject());
		return this;
	}

	/**
	 * Return true only if first element is present but it might not be visible.
	 * 
	 * @param numRetries
	 * @return
	 */
	@Override
	public boolean isPresent(int numRetries) {
		boolean elemPresent = false;
		WebElement webElem = null;
		for (int i = 0; i <= numRetries; i++) {
			try {
				webElem = browser.getSeleniumWebDriver().findElement(domObject.getLocatorAsBy());
				if (webElem != null) {
					elemPresent = true;
					break;
				}
			} catch (Throwable th) {
				if (i == numRetries) {
					break;
				}
			}
			browser.waitForSeconds(2);
		}
		return elemPresent;
	}

	/**
	 * Return true only if first element is visible.
	 * 
	 * @param numRetries
	 * @return
	 */
	@Override
	public boolean isVisible(int numRetries) {
		boolean elemVisible = false;
		WebElement webElem = null;
		for (int i = 0; i <= numRetries; i++) {
			try {
				webElem = browser.getSeleniumWebDriver().findElement(domObject.getLocatorAsBy());
				if (webElem != null && !"hidden".equals(webElem.getCssValue("visibility"))) {
					elemVisible = true;
					break;
				}
			} catch (Throwable th) {
				if (i == numRetries) {
					break;
				}
			}
			browser.waitForSeconds(2);
		}
		return elemVisible;
	}

	public boolean isDisabled(int numRetries) {
		boolean elemDisabled = false;
		WebElement webElem = null;
		for (int i = 0; i <= numRetries; i++) {
			try {
				webElem = browser.getSeleniumWebDriver().findElement(domObject.getLocatorAsBy());
				if (webElem != null && !webElem.isEnabled()) {
					elemDisabled = true;
					break;
				}
			} catch (Throwable th) {
				if (i == numRetries) {
					break;
				}
			}
			browser.waitForSeconds(2);
		}
		return elemDisabled;
	}

	/**
	 * Determine whether or not this element is selected or not. This operation
	 * only applies to input elements such as checkboxes, options in a select
	 * and radio buttons.
	 * 
	 * @return True if the element is currently selected or checked, false
	 *         otherwise.
	 */
	public boolean isSelected(int numRetries) {
		boolean elemSelected = false;
		WebElement webElem = null;
		for (int i = 0; i <= numRetries; i++) {
			try {
				webElem = browser.getSeleniumWebDriver().findElement(domObject.getLocatorAsBy());
				if (webElem != null && webElem.isSelected()) {
					elemSelected = true;
					break;
				}
			} catch (Throwable th) {
				if (i == numRetries) {
					break;
				}
			}
			browser.waitForSeconds(2);
		}
		return elemSelected;
	}

	/**
	 * Finds text from first element.
	 * 
	 * @param numRetries
	 */
	public String getText(int numRetries) {
		try {
			WebElement webElem = findElement(numRetries);
			return webElem.getText();
		} catch (Throwable th) {
			Assert.fail("Failed to get text from element '" + domObject.getDisplayName() + "'.", th);
		}
		return null;
	}

	/**
	 * Copy text into clipboard from the current cursor position. Applicable
	 * only for editable fields i.e textbox, textarea etc. First it will click
	 * on that element and then select all text and copy into clipboard.
	 * 
	 * @return
	 */
	public void copyTextToClipboard(int numRetries) {
		try {
			for (int i = 0; i < 5; i++) {
				try {
					WebElement webElem = findElement(numRetries);
					webElem.click();
					Actions webActions = new Actions(browser.getSeleniumWebDriver());
					webActions.sendKeys(Keys.CONTROL + "a").sendKeys(Keys.CONTROL + "c");
					break;
				} catch (MoveTargetOutOfBoundsException | ElementNotVisibleException ex) {
					browser.waitForSeconds(2);
				}
			}
		} catch (Throwable th) {
			Assert.fail("Failed to copy contents into clipboard. Element '" + domObject.getDisplayName() + "'.", th);
		}
	}

	/**
	 * Replace the content of the element with the clipboard contents.
	 * Applicable only for editable fields i.e textbox, textarea etc.
	 * 
	 * @return
	 */
	public void pasteTextFromClipboard(int numRetries) {
		try {
			for (int i = 0; i < 5; i++) {
				try {
					WebElement webElem = findElement(numRetries);
					webElem.click();
					Actions webActions = new Actions(browser.getSeleniumWebDriver());
					webActions.sendKeys(Keys.CONTROL + "a").sendKeys(Keys.CONTROL + "v").build().perform();
					break;
				} catch (MoveTargetOutOfBoundsException | ElementNotVisibleException ex) {
					browser.waitForSeconds(2);
				}
			}
		} catch (Throwable th) {
			Assert.fail("Failed to paste clipboard contents into field '" + domObject.getDisplayName() + "'.", th);
		}
	}

	@Override
	public void click(int numRetries) {
		try {
			for (int i = 0; i < 5; i++) {
				try {
					WebElement webElem = findElement(numRetries);
					webElem.click();
					break;
				} catch (MoveTargetOutOfBoundsException | ElementNotVisibleException ex) {
					browser.waitForSeconds(2);
				}
			}
		} catch (Throwable th) {
			Assert.fail("Failed to perform mouse click on element '" + domObject.getDisplayName() + "'.", th);
		}
	}

	@Override
	public void doubleClick(int numRetries) {
		try {
			for (int i = 0; i < 5; i++) {
				try {
					WebElement webElem = findElement(numRetries);
					webElem.click();

					Actions webActions = new Actions(browser.getSeleniumWebDriver());
					webActions.doubleClick().build().perform();
					break;
				} catch (MoveTargetOutOfBoundsException | ElementNotVisibleException ex) {
					browser.waitForSeconds(2);
				}
			}
		} catch (Throwable th) {
			Assert.fail("Failed to perform mouse double click on element '" + domObject.getDisplayName() + "'.", th);
		}
	}

	@Override
	public void rightClick(int numRetries) {
		try {
			WebElement webElem = findElement(numRetries);
			Point location = webElem.getLocation();
			Dimension size = webElem.getSize();

			browser.getSikuliScreen()
					.rightClick(new Region(location.getX(), location.getY(), size.getWidth(), size.getHeight()));
		} catch (Throwable th) {
			Assert.fail("Failed to perform mouse right click on element '" + domObject.getDisplayName() + "'.", th);
		}
	}

	public void clickAndHold(int numRetries) {
		for (int i = 0; i < 5; i++) {
			try {
				WebElement webElem = findElement(numRetries);
				Actions actions = new Actions(browser.getSeleniumWebDriver());
				actions.clickAndHold(webElem).build().perform();
				break;
			} catch (MoveTargetOutOfBoundsException | ElementNotVisibleException ex) {
				browser.waitForSeconds(2);
			}
		}
	}

	public void release(int numRetries) {
		for (int i = 0; i < 5; i++) {
			try {
				WebElement webElem = findElement(numRetries);
				Actions actions = new Actions(browser.getSeleniumWebDriver());
				actions.release(webElem).build().perform();
				break;
			} catch (MoveTargetOutOfBoundsException | ElementNotVisibleException ex) {
				browser.waitForSeconds(2);
			}
		}
	}

	public void dragAndDrop(DOMObject target, int numRetries) {
		try {
			for (int i = 0; i < 5; i++) {
				try {
					WebElement sourceElem = findElement(numRetries);
					WebElement targetElem = target.getValidator(browser, region).findElement(numRetries);

					Actions webActions = new Actions(browser.getSeleniumWebDriver());
					webActions.dragAndDrop(sourceElem, targetElem).build().perform();
					break;
				} catch (MoveTargetOutOfBoundsException | ElementNotVisibleException ex) {
					browser.waitForSeconds(2);
				}
			}
		} catch (Throwable th) {
			Assert.fail("Failed to perform dragAndDrop from source '" + domObject.getDisplayName() + "' to target '"
					+ target.getDisplayName() + "'.", th);
		}
	}

	public void performKeyDown(Keys keys, int numRetries) {
		for (int i = 0; i < 5; i++) {
			try {
				WebElement webElem = findElement(numRetries);
				Actions actions = new Actions(browser.getSeleniumWebDriver());
				actions.keyDown(webElem, keys).build().perform();
				break;
			} catch (MoveTargetOutOfBoundsException | ElementNotVisibleException ex) {
				browser.waitForSeconds(2);
			}
		}
	}

	public void performKeyUp(Keys keys, int numRetries) {
		for (int i = 0; i < 5; i++) {
			try {
				WebElement webElem = findElement(numRetries);
				Actions actions = new Actions(browser.getSeleniumWebDriver());
				actions.keyUp(webElem, keys).build().perform();
				break;
			} catch (MoveTargetOutOfBoundsException | ElementNotVisibleException ex) {
				browser.waitForSeconds(2);
			}
		}
	}

	public void performKeyPressed(Keys keys, int numRetries) {
		for (int i = 0; i < 5; i++) {
			try {
				WebElement webElem = findElement(numRetries);
				Actions actions = new Actions(browser.getSeleniumWebDriver());
				actions.keyDown(webElem, keys).keyUp(webElem, keys).build().perform();
				break;
			} catch (MoveTargetOutOfBoundsException | ElementNotVisibleException ex) {
				browser.waitForSeconds(2);
			}
		}
	}

	public void typeText(String text, NewTextLocation location, int numRetries) {
		for (int i = 0; i < 5; i++) {
			try {
				WebElement webElem = findElement(numRetries);
				switch (location) {
				case start:
					performKeyPressed(Keys.HOME, numRetries);
					break;
				case end:
					performKeyPressed(Keys.END, numRetries);
					break;
				case replace:
					performKeyDown(Keys.CONTROL, numRetries);
					Actions actions = new Actions(browser.getSeleniumWebDriver());
					actions.sendKeys("a", text).build().perform();
					performKeyUp(Keys.CONTROL, numRetries);
					break;
				}

				Actions actions = new Actions(browser.getSeleniumWebDriver());
				actions.sendKeys(webElem, text).build().perform();
				break;
			} catch (MoveTargetOutOfBoundsException | ElementNotVisibleException ex) {
				browser.waitForSeconds(2);
			}
		}
	}
}
