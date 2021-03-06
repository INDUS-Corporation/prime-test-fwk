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

import java.util.List;

import org.openqa.selenium.Keys;
import org.sikuli.script.Button;
import org.sikuli.script.Location;
import org.sikuli.script.Match;
import org.sikuli.script.Region;
import org.testng.Assert;

import com.induscorp.prime.testing.ui.core.commons.ImageSection;
import com.induscorp.prime.testing.ui.core.commons.UIObjectType;
import com.induscorp.prime.testing.ui.core.config.webbrowser.WebBrowser;
import com.induscorp.prime.testing.ui.core.objects.ImageObject;
import com.induscorp.prime.testing.ui.core.objects.NewTextLocation;
import com.induscorp.prime.testing.ui.core.objects.image.ImageValidator;
import com.induscorp.prime.testing.ui.core.objects.scrollbar.Scrollbar;

/**
 * 
 * @author Madhav Krishna
 *
 */
public class ImageValidatorSI extends ImageValidator {
	protected ImageSI imageObj;

	public ImageValidatorSI(WebBrowser browser, ImageSI uiObject, Region region) {
		super(browser, uiObject, region);
		this.imageObj = uiObject;
	}

	@Override
	public boolean isPresent(int numRetries) {
		Match m = findElementNoException(numRetries);
		return (m != null);
	}

	@Override
	public boolean isVisible(int numRetries) {
		return isPresent(numRetries);
	}

	@Override
	public void click(int numRetries) {
		try {
			Match match = findElement(numRetries);
			match.click();
		} catch (Throwable th) {
			Assert.fail("Failed to perform mouse click on Image '" + imageObj.getDisplayName() + "'.", th);
		}
	}

	public void click(ImageSection imageSection, int numRetries) {
		try {
			Match match = findElement(numRetries);
			getImageSection(match, imageSection).click();
		} catch (Throwable th) {
			Assert.fail("Failed to perform mouse click on Image '" + imageObj.getDisplayName() + "'.", th);
		}
	}

	@Override
	public void doubleClick(int numRetries) {
		try {
			Match match = findElement(numRetries);
			match.doubleClick();
		} catch (Throwable th) {
			Assert.fail("Failed to perform mouse double click on Image '" + imageObj.getDisplayName() + "'.", th);
		}
	}

	public void doubleClick(ImageSection imageSection, int numRetries) {
		try {
			Match match = findElement(numRetries);
			getImageSection(match, imageSection).doubleClick();
		} catch (Throwable th) {
			Assert.fail("Failed to perform mouse double click on Image '" + imageObj.getDisplayName() + "'.", th);
		}
	}

	@Override
	public void rightClick(int numRetries) {
		try {
			Match match = findElement(numRetries);
			match.rightClick();
		} catch (Throwable th) {
			Assert.fail("Failed to perform mouse right click on Image '" + imageObj.getDisplayName() + "'.", th);
		}
	}

	public void rightClick(ImageSection imageSection, int numRetries) {
		try {
			Match match = findElement(numRetries);
			getImageSection(match, imageSection).rightClick();
		} catch (Throwable th) {
			Assert.fail("Failed to perform mouse right click on Image '" + imageObj.getDisplayName() + "'.", th);
		}
	}

	@Override
	public void clickAndHold(int numRetries) {
		try {
			Match match = findElement(numRetries);
			match.mouseDown(Button.LEFT);
		} catch (Throwable th) {
			Assert.fail("Failed to perform mouse clickAndHold on Image '" + imageObj.getDisplayName() + "'.", th);
		}
	}

	@Override
	public void release(int numRetries) {
		try {
			Match match = findElement(numRetries);
			match.mouseDown(Button.LEFT);
		} catch (Throwable th) {
			Assert.fail("Failed to perform mouse clickAndHold on Image '" + imageObj.getDisplayName() + "'.", th);
		}
	}

	@Override
	public void performKeyDown(Keys keys, int numRetries) {
		try {
			Match match = findElement(numRetries);
			match.click();
			match.keyDown(seleniumToSikuliKeyConverter(keys));
		} catch (Throwable th) {
			Assert.fail("Failed to perform keyDown on Image '" + imageObj.getDisplayName() + "'.", th);
		}

	}

	@Override
	public void performKeyUp(Keys keys, int numRetries) {
		try {
			Match match = findElement(numRetries);
			match.click();
			match.keyUp(seleniumToSikuliKeyConverter(keys));
		} catch (Throwable th) {
			Assert.fail("Failed to perform keyUp ('" + seleniumToSikuliKeyConverter(keys) + "') on Image '"
					+ imageObj.getDisplayName() + "'.", th);
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
			Assert.fail("Failed to perform keyPressed ('" + seleniumToSikuliKeyConverter(keys) + "') on Image '"
					+ imageObj.getDisplayName() + "'.", th);
		}
	}

	@Override
	@Deprecated
	public void typeText(String text, NewTextLocation location, int numRetries) {
		Assert.fail("typeText() API is not supported for Image element.");

	}

	@Override
	public ImageValidatorSI scrollElementOnViewport(Scrollbar scrollbar) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public Match findElement(int numRetries) {
		Match match = null;
		for (int i = 0; i <= numRetries; i++) {
			try {
				Region region = imageObj.getImageLocation().getRegionOfImageObject(browser, imageObj.getImage());
				Assert.assertNotNull(region, "Failed to find Image '" + imageObj.getDisplayName() + "'.");
				match = new Match(region, 1);
				break;
			} catch (Throwable th) {
				if (i == numRetries) {
					Assert.fail("Unable to find Image '" + imageObj.getDisplayName() + "'. Reason timeout(waited for "
							+ (numRetries * 2) + " seconds).", th);
					break;
				}
			}
			browser.waitForSeconds(2);
		}
		return match;
	}

	@Override
	public Match findElementNoException(int numRetries) {
		Match match = null;
		try {
			match = findElement(numRetries);
		} catch (Throwable th) {
			// Do nothing
		}
		return match;
	}

	@Override
	public List<Match> findElements(int numRetries) {
		Region r = imageObj.getImageLocation().getRegion(browser);

		return new ImageObject(UIObjectType.image, imageObj.getDisplayName(), imageObj.getImage())
				.getValidator(browser, r).findElements(numRetries);
	}

	public void dragAndDrop(ImageObject target, Region targetRegion, int numRetries) {
		try {
			Match sourceElem = findElement(numRetries);
			Match targetElem = target.getValidator(browser, targetRegion).findElement(numRetries);

			Assert.assertNotNull(sourceElem, "Failed to find Image '" + imageObj.getDisplayName() + "'.");
			Assert.assertNotNull(targetElem, "Failed to find element '" + target.getDisplayName() + "'.");

			sourceElem.drag(targetElem);
			sourceElem.dropAt(targetElem);
		} catch (Throwable th) {
			Assert.fail("Failed to perform dragAndDrop from source '" + imageObj.getDisplayName() + "' to target '"
					+ target.getDisplayName() + "'.", th);
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
			return imageMatch.getCenter();
		}
		return null;
	}

}
