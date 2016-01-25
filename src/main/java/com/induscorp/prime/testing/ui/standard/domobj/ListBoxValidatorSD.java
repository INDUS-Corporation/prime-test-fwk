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

import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.sikuli.script.Region;
import org.testng.Assert;

import com.induscorp.prime.testing.ui.core.commons.ItemList;
import com.induscorp.prime.testing.ui.core.config.webbrowser.WebBrowser;
import com.induscorp.prime.testing.ui.core.objects.DOMObject;
import com.induscorp.prime.testing.ui.core.objects.DOMObjectValidator;
import com.induscorp.prime.testing.ui.core.objects.listbox.ListBoxValidator;
import com.induscorp.prime.testing.ui.core.objects.scrollbar.Scrollbar;
import com.induscorp.prime.testing.ui.core.objects.validator.mechanisms.TextValidationMechanism;

/**
 * 
 * @author Madhav Krishna
 *
 */
public class ListBoxValidatorSD extends ListBoxValidator {
	protected DOMObjectValidator domObjValidator;

	public ListBoxValidatorSD(WebBrowser browser, ListBoxSD uiObject, Region region) {
		super(browser, uiObject, region);
		domObjValidator = new DOMObjectValidator(browser,
				new DOMObject(uiObject.getDisplayName(), uiObject.getLocatorXPath()), region);
	}

	@Override
	public void validateDisabled(int numRetries) {
		Assert.assertTrue(domObjValidator.isDisabled(numRetries),
				"'" + uiObject.getDisplayName() + "' element is not disabled.");
	}

	@Override
	public void validateEnabled(int numRetries) {
		Assert.assertFalse(domObjValidator.isDisabled(numRetries),
				"'" + uiObject.getDisplayName() + "' element is not enabled.");
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
	public ListBoxValidatorSD scrollElementOnViewport(Scrollbar scrollbar) {
		domObjValidator.scrollElementOnViewport(scrollbar);
		return this;
	}

	@Override
	public void validateSelectedItem(String expectedSelectedValue, TextValidationMechanism validationMechanism,
			int numRetries) {
		WebElement selectElement = domObjValidator.findElement(numRetries);

		List<WebElement> options = selectElement.findElements(By.xpath("./option"));
		Assert.assertNotNull(options, "Failed to find items for ListBox '" + uiObject.getDisplayName() + "'.");
		Assert.assertTrue(options.size() > 0,
				"Failed to find items in ListBox '" + uiObject.getDisplayName() + "'. Found 0 items.");

		String optionTextValue;
		boolean found = false;
		for (WebElement option : options) {
			optionTextValue = option.getText();
			if (optionTextValue != null && option.isSelected()
					&& matchTextValue(optionTextValue.trim(), expectedSelectedValue, validationMechanism)) {
				found = true;
				break;
			}
		}

		if (!found) {
			Assert.fail("Failed to find selected item '" + expectedSelectedValue + "' in ListBox '"
					+ uiObject.getDisplayName() + "'.");
		}
	}

	@Override
	public String getSelectedItem(int numRetries) {
		WebElement selectElement = domObjValidator.findElement(numRetries);

		List<WebElement> options = selectElement.findElements(By.xpath("./option"));
		Assert.assertNotNull(options, "Failed to find items for ListBox '" + uiObject.getDisplayName() + "'.");
		Assert.assertTrue(options.size() > 0,
				"Failed to find items in ListBox '" + uiObject.getDisplayName() + "'. Found 0 items.");

		String optionTextValue;
		for (WebElement option : options) {
			optionTextValue = option.getText();
			if (optionTextValue != null && option.isSelected()) {
				return optionTextValue;
			}
		}
		return null;
	}

	@Override
	public List<String> getSelectedItems(int numRetries) {
		WebElement selectElement = domObjValidator.findElement(numRetries);

		List<WebElement> options = selectElement.findElements(By.xpath("./option"));
		Assert.assertNotNull(options, "Failed to find items for ListBox '" + uiObject.getDisplayName() + "'.");
		Assert.assertTrue(options.size() > 0,
				"Failed to find items in ListBox '" + uiObject.getDisplayName() + "'. Found 0 items.");

		List<String> selectedItems = new LinkedList<String>();
		String optionTextValue;
		for (WebElement option : options) {
			optionTextValue = option.getText();
			if (optionTextValue != null && option.isSelected()) {
				selectedItems.add(optionTextValue);
			}
		}
		return selectedItems;
	}

	@Override
	public void selectFirstItem(int numRetries) {
		WebElement selectElement = domObjValidator.findElement(numRetries);

		List<WebElement> options = selectElement.findElements(By.xpath("./option"));
		Assert.assertNotNull(options, "Failed to find items for ListBox '" + uiObject.getDisplayName() + "'.");
		Assert.assertTrue(options.size() > 0,
				"Failed to find items in ListBox '" + uiObject.getDisplayName() + "'. Found 0 items.");

		options.get(0).click();
	}

	@Override
	public void selectLastItem(int numRetries) {
		WebElement selectElement = domObjValidator.findElement(numRetries);

		List<WebElement> options = selectElement.findElements(By.xpath("./option"));
		Assert.assertNotNull(options, "Failed to find items for ListBox '" + uiObject.getDisplayName() + "'.");
		Assert.assertTrue(options.size() > 0,
				"Failed to find items in ListBox '" + uiObject.getDisplayName() + "'. Found 0 items.");

		options.get(options.size() - 1).click();
	}

	@Override
	public void selectItem(String itemName, int numRetries) {
		WebElement selectElement = domObjValidator.findElement(numRetries);
		
		List<WebElement> options = selectElement.findElements(By.xpath("./option"));
		Assert.assertNotNull(options, "Failed to find items for ListBox '" + uiObject.getDisplayName() + "'.");

		String optionTextValue;
		boolean found = false;

		//performAction(new KeyboardEvent(KeyboardEventName.kbKeyUp, Keys.CONTROL, null), 0);
		for (WebElement option : options) {
			optionTextValue = option.getText();
			if (optionTextValue != null && itemName.equals(optionTextValue.trim())) {
				if(!option.isSelected()) {
					option.click();				
				}
				found = true;
				//break;
			} else if(option.isSelected()) {
				option.click();
			}
		}

		if (!found) {
			Assert.fail("Failed to find item '" + itemName + "' in ListBox '" + uiObject.getDisplayName() + "'.");
		}
	}

	@Override
	public void selectItems(ItemList<String> itemsToBeSelected, int numRetries) {
		WebElement selectElement = domObjValidator.findElement(numRetries);

		List<WebElement> options = selectElement.findElements(By.xpath("./option"));
		Assert.assertNotNull(options, "Failed to find items for ListBox '" + uiObject.getDisplayName() + "'.");

		String optionTextValue;
		List<String> foundItemList = new LinkedList<String>();
		int itemNum = 1;		
		for (WebElement option : options) {
			optionTextValue = option.getText();
			if (optionTextValue != null && itemsToBeSelected.getItems().contains(optionTextValue.trim())) {
				try {
					//if(itemNum != 1) {
					//	performAction(new KeyboardEvent(KeyboardEventName.kbKeyDown, Keys.CONTROL, null), 0);
					//}
					option.click();
					foundItemList.add(optionTextValue.trim());
					//if(itemNum != 1) {
					//	performAction(new KeyboardEvent(KeyboardEventName.kbKeyUp, Keys.CONTROL, null), 0);
					//}
				} catch (Throwable th) {
					//if(itemNum != 1) {
					//performAction(new KeyboardEvent(KeyboardEventName.kbKeyUp, Keys.CONTROL, null), 0);
					//}
					throw th;
				}
				itemNum++;
			}
		}

		if (foundItemList.size() != itemsToBeSelected.size()) {
			itemsToBeSelected.removeAll(foundItemList);
			Assert.fail("Failed to find item(s) '" + itemsToBeSelected + "' in ListBox '" + uiObject.getDisplayName() + "'.");
		}
	}

	@Override
	public void validateItemsPresent(ItemList<String> items, int numRetries) {
		WebElement selectElement = domObjValidator.findElement(numRetries);

		List<WebElement> options = selectElement.findElements(By.xpath("./option"));
		Assert.assertNotNull(options, "Failed to find items for ListBox '" + uiObject.getDisplayName() + "'.");
		Assert.assertTrue(options.size() > 0,
				"Failed to find items in ListBox '" + uiObject.getDisplayName() + "'. Found 0 items.");

		String optionTextValue;
		for (String item : items.getItems()) {
			boolean found = false;
			for (WebElement option : options) {
				optionTextValue = option.getText();
				if (optionTextValue != null && matchTextValue(optionTextValue.trim(), item,
						TextValidationMechanism.exactMatchWithExpectedValue)) {
					found = true;
					break;
				}
			}

			if (!found) {
				Assert.fail("Failed to find item '" + item + "' in ListBox '" + uiObject.getDisplayName() + "'.");
			}
		}
	}

	@Override
	public void validateItemsNotPresent(ItemList<String> items, int numRetries) {
		WebElement selectElement = domObjValidator.findElement(numRetries);

		List<WebElement> options = selectElement.findElements(By.xpath("./option"));
		Assert.assertNotNull(options, "Failed to find items for ListBox '" + uiObject.getDisplayName() + "'.");
		Assert.assertTrue(options.size() > 0,
				"Failed to find items in ListBox '" + uiObject.getDisplayName() + "'. Found 0 items.");

		String optionTextValue;
		for (String item : items.getItems()) {
			boolean found = false;
			for (WebElement option : options) {
				optionTextValue = option.getText();
				if (optionTextValue != null && matchTextValue(optionTextValue.trim(), item,
						TextValidationMechanism.exactMatchWithExpectedValue)) {
					found = true;
					break;
				}
			}

			if (found) {
				Assert.fail("Item '" + item + "' is present in ListBox '" + uiObject.getDisplayName() + "'.");
			}
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
