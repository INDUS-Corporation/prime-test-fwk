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

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.Keys;
import org.sikuli.script.Button;
import org.sikuli.script.Key;
import org.sikuli.script.KeyModifier;
import org.sikuli.script.Location;
import org.sikuli.script.Match;
import org.sikuli.script.Region;
import org.testng.Assert;

import com.induscorp.prime.testing.ui.core.commons.ImageSection;
import com.induscorp.prime.testing.ui.core.config.webbrowser.WebBrowser;
import com.induscorp.prime.testing.ui.core.objects.scrollbar.Scrollbar;
import com.induscorp.prime.testing.ui.core.objects.validator.mechanisms.TextValidationMechanism;
import com.induscorp.prime.testing.ui.core.utils.ClipboardUtil;

/**
 * 
 * @author Madhav Krishna
 *
 */
public class ImageObjectValidator extends UIObjectValidator {
	protected ImageObject imgLocator;

	public ImageObjectValidator(WebBrowser browser, ImageObject locator, Region region) {
		super(browser, locator, region);
		this.imgLocator = locator;
	}

	@Override
	public ImageObject getUIObject() {
		return imgLocator;
	}

	/**
	 * Finds first element with polling and it polls after 2 seconds for
	 * numRetries times.
	 * 
	 * @param numRetries
	 * @return
	 */
	@Override
	public Match findElement(int numRetries) {
		Match match = null;
		for (int i = 0; i <= numRetries; i++) {
			try {
				match = region.find(imgLocator.getImage());
				Assert.assertNotNull(match, "Unable to find element '" + imgLocator.getDisplayName() + "'.");
				break;
			} catch (Throwable th) {
				if (i == numRetries) {
					Assert.fail("Unable to find element '" + imgLocator.getDisplayName()
							+ "'. Reason timeout(waited for " + (numRetries * 2) + " seconds).", th);
					break;
				}
			}
			browser.waitForSeconds(2);
		}
		return match;
	}

	/**
	 * Finds first element with polling and it polls after 2 seconds for
	 * numRetries times. It does not throw any exception
	 * 
	 * @param numRetries
	 * @return
	 */
	@Override
	public Match findElementNoException(int numRetries) {
		Match match = null;
		for (int i = 0; i <= numRetries; i++) {
			try {
				match = region.find(imgLocator.getImage());
				if (match != null) {
					break;
				}
			} catch (Throwable th) {
				match = null;
				if (i == numRetries) {
					break;
				}
			}
			browser.waitForSeconds(2);
		}
		return match;
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
	public List<Match> findElements(int numRetries) {
		List<Match> list = new LinkedList<Match>();
		for (int i = 0; i <= numRetries; i++) {
			try {
				Iterator<Match> matches = region.findAll(imgLocator.getImage());

				Assert.assertNotNull(matches, "Unable to find elements for '" + imgLocator.getDisplayName() + "'.");
				while (matches.hasNext()) {
					list.add(matches.next());
				}
				Assert.assertTrue(list.size() > 0,
						"Unable to find elements for '" + imgLocator.getDisplayName() + "' locator.");
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
		return list;
	}

	@Override
	public ImageObjectValidator scrollElementOnViewport(Scrollbar scrollbar) {
		if (scrollbar == null) {
			return this;
		}

		return this;
	}

	/**
	 * Return true only if first element is present but it might not be visible.
	 * 
	 * @param numRetries
	 * @return
	 */
	public boolean isPresent(int numRetries) {
		boolean elemPresent = false;
		Match match = null;
		match = findElement(numRetries);
		if (match != null) {
			elemPresent = true;
		}
		return elemPresent;
	}

	/**
	 * Return true only if first element is visible.
	 * 
	 * @param numRetries
	 * @return
	 */
	public boolean isVisible(int numRetries) {
		return isPresent(numRetries);
	}

	public void click(int numRetries) {
		try {
			Match match = findElement(numRetries);
			match.click();
		} catch (Throwable th) {
			Assert.fail("Failed to perform mouse click on element '" + imgLocator.getDisplayName() + "'.", th);
		}
	}

	protected Location getImageSection(Match imageMatch, ImageSection imageSection) {
		switch (imageSection) {
		case topLeft:
			return imageMatch.getTopLeft();
		case topRight:
			return imageMatch.getTopRight();
		case bottomLeft:
			return imageMatch.getBottomLeft();
		case bottomRight:
			return imageMatch.getBottomRight();
		case center:
			return imageMatch.checkMatch();
		}
		return null;
	}

	public void click(ImageSection imageSection, int numRetries) {
		try {
			Match match = findElement(numRetries);
			getImageSection(match, imageSection).click();
		} catch (Throwable th) {
			Assert.fail("Failed to perform mouse click on element '" + imgLocator.getDisplayName() + "'.", th);
		}
	}

	public void doubleClick(int numRetries) {
		try {
			Match match = findElement(numRetries);
			match.doubleClick();
		} catch (Throwable th) {
			Assert.fail("Failed to perform mouse double click on element '" + imgLocator.getDisplayName() + "'.", th);
		}
	}

	public void doubleClick(ImageSection imageSection, int numRetries) {
		try {
			Match match = findElement(numRetries);
			getImageSection(match, imageSection).doubleClick();
		} catch (Throwable th) {
			Assert.fail("Failed to perform mouse double click on element '" + imgLocator.getDisplayName() + "'.", th);
		}
	}

	public void rightClick(int numRetries) {
		try {
			Match match = findElement(numRetries);
			match.rightClick();
		} catch (Throwable th) {
			Assert.fail("Failed to perform mouse right click on element '" + imgLocator.getDisplayName() + "'.", th);
		}
	}

	public void rightClick(ImageSection imageSection, int numRetries) {
		try {
			Match match = findElement(numRetries);
			getImageSection(match, imageSection).rightClick();
		} catch (Throwable th) {
			Assert.fail("Failed to perform mouse right click on element '" + imgLocator.getDisplayName() + "'.", th);
		}
	}

	@Override
	public void clickAndHold(int numRetries) {
		try {
			Match match = findElement(numRetries);
			match.mouseDown(Button.LEFT);
		} catch (Throwable th) {
			Assert.fail("Failed to perform mouse clickAndHold on element '" + imgLocator.getDisplayName() + "'.", th);
		}
	}

	@Override
	public void release(int numRetries) {
		try {
			Match match = findElement(numRetries);
			match.mouseUp(Button.LEFT);
		} catch (Throwable th) {
			Assert.fail("Failed to perform mouse release on element '" + imgLocator.getDisplayName() + "'.", th);
		}
	}

	public void dragAndDrop(ImageObject target, Region targetRegion, int numRetries) {
		try {
			Match sourceElem = findElement(numRetries);
			Match targetElem = target.getValidator(browser, targetRegion).findElement(numRetries);

			Assert.assertNotNull(sourceElem, "Failed to find element '" + imgLocator.getDisplayName() + "'.");
			Assert.assertNotNull(targetElem, "Failed to find element '" + target.getDisplayName() + "'.");

			sourceElem.dropAt(targetElem);
		} catch (Throwable th) {
			Assert.fail("Failed to perform dragAndDrop from source '" + imgLocator.getDisplayName() + "' to target '"
					+ target.getDisplayName() + "'.", th);
		}
	}

	@Override
	public void performKeyDown(Keys keys, int numRetries) {
		try {
			Match match = findElement(numRetries);
			match.click();
			match.keyDown(seleniumToSikuliKeyConverter(keys));
		} catch (Throwable th) {
			Assert.fail("Failed to perform keyDown('" + seleniumToSikuliKeyConverter(keys) + "') on element '"
					+ imgLocator.getDisplayName() + "'.", th);
		}
	}

	@Override
	public void performKeyUp(Keys keys, int numRetries) {
		try {
			Match match = findElement(numRetries);
			match.click();
			match.keyUp(seleniumToSikuliKeyConverter(keys));
		} catch (Throwable th) {
			Assert.fail("Failed to perform keyUp ('" + seleniumToSikuliKeyConverter(keys) + "') on element '"
					+ imgLocator.getDisplayName() + "'.", th);
		}
	}

	@Override
	public void performKeyPressed(Keys keys, int numRetries) {
		try {
			Match match = findElement(numRetries);
			match.click();
			match.keyDown(seleniumToSikuliKeyConverter(keys));
			match.keyUp(seleniumToSikuliKeyConverter(keys));
		} catch (Throwable th) {
			Assert.fail("Failed to perform keyPressed ('" + seleniumToSikuliKeyConverter(keys) + "') on element '"
					+ imgLocator.getDisplayName() + "'.", th);
		}
	}

	@Override
	public void typeText(String text, NewTextLocation location, int numRetries) {
		try {
			Match match = findElement(numRetries);
			match.click();
			switch (location) {
			case start:
				match.type(Key.HOME);
				break;
			case end:
				match.type(Key.END);
				break;
			case replace:
				match.type("a", KeyModifier.CTRL);
				break;
			}

			match.type(text);
			//validateValue(text, TextValidationMechanism.containsExpectedValue, 0);
		} catch (Throwable th) {
			Assert.fail("Failed to perform keyPressed on element '" + imgLocator.getDisplayName() + "'.", th);
		}
	}

	public void validateValue(String expectedValue, TextValidationMechanism validationMechanism, int numRetries) {
		Match match = findElement(numRetries);
		validateTextValue(match.text(), expectedValue, validationMechanism);
	}

	public String getText(int numRetries) {
		Match match = findElement(numRetries);
		return match.text();
	}

	public String getEditableFieldTextUsingClipboard(int numRetries) {
		Match match = findElement(numRetries);
		match.click();
		match.type("a", KeyModifier.CTRL);
		match.type("c", KeyModifier.CTRL);
		match.click();
		String contents = ClipboardUtil.getContents();
		ClipboardUtil.clearContents();
		return contents;
	}
}
