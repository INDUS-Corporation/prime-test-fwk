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

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.sikuli.script.Region;
import org.testng.Assert;

import com.induscorp.prime.testing.ui.core.commons.ItemMap;
import com.induscorp.prime.testing.ui.core.config.webbrowser.WebBrowser;
import com.induscorp.prime.testing.ui.core.objects.DOMObject;
import com.induscorp.prime.testing.ui.core.objects.DOMObjectValidator;
import com.induscorp.prime.testing.ui.core.objects.radio.RadioButtonGroupValidator;
import com.induscorp.prime.testing.ui.core.objects.scrollbar.Scrollbar;

/**
 * 
 * @author Madhav Krishna
 *
 */
public class RadioButtonGroupValidatorSD extends RadioButtonGroupValidator {
	protected DOMObjectValidator domObjValidator;

	public RadioButtonGroupValidatorSD(WebBrowser browser, RadioButtonGroupSD uiObject, Region region) {
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
	public RadioButtonGroupValidatorSD scrollElementOnViewport(Scrollbar scrollbar) {
		domObjValidator.scrollElementOnViewport(scrollbar);
		return this;
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

	@Override
	public void validateDisabled(int numRetries) {
		List<WebElement> webElems = findElements(numRetries);
		List<String> enabledItems = new LinkedList<String>();
		for(WebElement elem : webElems) {
			if(elem.isEnabled()) {
				enabledItems.add(elem.getAttribute("value"));
			}
		}
		if(enabledItems.size() > 0) {
			Assert.fail("Radio button group '" + uiObject.getDisplayName() 
					+ "' is not disabled. It has the following enabled items: " + enabledItems);
		}
	}

	@Override
	public void validateEnabled(int numRetries) {
		List<WebElement> webElems = findElements(numRetries);
		List<String> disabledItems = new LinkedList<String>();
		for(WebElement elem : webElems) {
			if(!elem.isEnabled()) {
				disabledItems.add(elem.getAttribute("value"));
			}
		}
		if(disabledItems.size() > 0) {
			Assert.fail("Radio button group '" + uiObject.getDisplayName() 
					+ "' is not enabled. It has the following disabled items: " + disabledItems);
		}
	}

	@Override
	public void selectOption(String value, String displayValue, int numRetries) {
		List<WebElement> webElems = findElements(numRetries);
		String elemVal;		
		for(WebElement elem : webElems) {
			elemVal = elem.getAttribute("value");
			if(elemVal != null && elemVal.equals(value)) {
				elem.click();
				break;
			}
		}
		
		try {
			validateSelectedOption(value, displayValue, numRetries);
		} catch(Throwable th) {
			Assert.fail("Failed to select option with value '" 
					+ displayValue + "' in radio button group '" + uiObject.getDisplayName() + "'.");
		}
	}

	@Override
	public void validateSelectedOption(String value, String displayValue, int numRetries) {
		List<WebElement> webElems = findElements(numRetries);
		String elemVal;
		boolean optionFound = false;
		for(WebElement elem : webElems) {
			elemVal = elem.getAttribute("value");
			if(elemVal != null && elemVal.equals(value) && elem.isSelected()) {
				optionFound = true;
				break;
			}
		}
		Assert.assertTrue(optionFound, "Radio button with value '" + displayValue + "' is not selected.");
	}

	@Override
	public void validateNotSelectedOptions(ItemMap<String, String> options, int numRetries) {
		List<WebElement> webElems = findElements(numRetries);
		String elemVal;
		LinkedHashMap<String, String> selectedOptions = new LinkedHashMap<String, String>();
		for(String optionValue : options.getItems().keySet()) {
			for(WebElement elem : webElems) {
				elemVal = elem.getAttribute("value");
				if(elemVal != null && elemVal.equals(optionValue) && elem.isSelected()) {
					selectedOptions.put(optionValue, options.getItems().get(optionValue));
				}
			}
		}
		
		if(selectedOptions.size() > 0) {
			Assert.fail("Radio button group '" + uiObject.getDisplayName() + 
					"' has some of the options selected. Selected options: " + selectedOptions);
		}
	}

}
