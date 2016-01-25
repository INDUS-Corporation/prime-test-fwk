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
package com.induscorp.prime.testing.ui.standard.domobj;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.sikuli.script.Region;
import org.testng.Assert;

import com.induscorp.prime.testing.ui.core.config.webbrowser.WebBrowser;
import com.induscorp.prime.testing.ui.core.objects.DOMObject;
import com.induscorp.prime.testing.ui.core.objects.DOMObjectValidator;
import com.induscorp.prime.testing.ui.core.objects.checkbox.CheckBoxValidator;
import com.induscorp.prime.testing.ui.core.objects.scrollbar.Scrollbar;

/**
 * 
 * @author Madhav Krishna
 *
 */
public class CheckBoxValidatorSD extends CheckBoxValidator {
	protected DOMObjectValidator domObjValidator;

	public CheckBoxValidatorSD(WebBrowser browser, CheckBoxSD uiObject, Region region) {
		super(browser, uiObject, region);
		domObjValidator = new DOMObjectValidator(browser,
				new DOMObject(uiObject.getDisplayName(), uiObject.getLocatorXPath()), region);
	}

	@Override
	public void typeText(String textToType, int numRetries) {
		domObjValidator.typeText(textToType, numRetries);
	}

	@Override
	public boolean isPresent(int numRetries) {
		return domObjValidator.isPresent(numRetries);
	}

	@Override
	public boolean isVisible(int numRetries) {
		return domObjValidator.isVisible(numRetries);
	}

	@Override
	public void click(int numRetries) {
		domObjValidator.click(numRetries);
	}

	@Override
	public void doubleClick(int numRetries) {
		domObjValidator.doubleClick(numRetries);
	}

	@Override
	public void rightClick(int numRetries) {
		domObjValidator.rightClick(numRetries);
	}

	@Override
	public void clickAndHold(int numRetries) {
		domObjValidator.clickAndHold(numRetries);
	}

	@Override
	public void release(int numRetries) {
		domObjValidator.release(numRetries);
	}

	@Override
	public void performKeyDown(Keys keys, int numRetries) {
		domObjValidator.performKeyDown(keys, numRetries);
	}

	@Override
	public void performKeyUp(Keys keys, int numRetries) {
		domObjValidator.performKeyUp(keys, numRetries);
	}

	@Override
	public void performKeyPressed(Keys keys, int numRetries) {
		domObjValidator.performKeyPressed(keys, numRetries);
	}

	@Override
	public CheckBoxValidatorSD scrollElementOnViewport(Scrollbar scrollbar) {
		domObjValidator.scrollElementOnViewport(scrollbar);
		return this;
	}

	@Override
	public boolean isCheckBoxChecked(int numRetries) {
		WebElement webElem = findElement(numRetries);
		return webElem.isSelected();
	}

	@Override
	public void validateCheckBoxChecked(int numRetries) {
		Assert.assertTrue(isCheckBoxChecked(numRetries), "Checkbox '" + uiObject.getDisplayName() + "' is not checked.");	
	}

	@Override
	public void validateCheckBoxUnchecked(int numRetries) {
		Assert.assertFalse(isCheckBoxChecked(numRetries), "Checkbox '" + uiObject.getDisplayName() + "' is checked.");
	}

	@Override
	public void checkAndValidateChecked(int numRetries) {
		WebElement webElem = findElement(numRetries);
		webElem.click();
		
		try {
			validateCheckBoxChecked(numRetries);
		} catch(Throwable th) {
			Assert.fail("Failed to check the Checkbox '" + uiObject.getDisplayName() + "'.");
		}
	}

	@Override
	public void uncheckAndValidateUnchecked(int numRetries) {
		WebElement webElem = findElement(numRetries);
		webElem.click();
		try {
			validateCheckBoxUnchecked(numRetries);
		} catch(Throwable th) {
			Assert.fail("Failed to uncheck the Checkbox '" + uiObject.getDisplayName() + "'.");
		}
	}
	
	@Override
	public WebElement findElement(int numRetries) {		
		return domObjValidator.findElement(numRetries);
	}

	@Override
	public WebElement findElementNoException(int numRetries) {
		return domObjValidator.findElementNoException(numRetries);
	}

	@Override
	public List<WebElement> findElements(int numRetries) {
		return domObjValidator.findElements(numRetries);
	}
}
