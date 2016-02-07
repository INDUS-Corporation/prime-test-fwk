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

import java.awt.Rectangle;

import org.openqa.selenium.Keys;
import org.sikuli.script.Key;
import org.sikuli.script.Region;
import org.testng.Assert;

import com.induscorp.prime.testing.ui.core.commons.UIObjectType;
import com.induscorp.prime.testing.ui.core.config.webbrowser.WebBrowser;
import com.induscorp.prime.testing.ui.core.events.InputEvent;
import com.induscorp.prime.testing.ui.core.events.InputEventType;
import com.induscorp.prime.testing.ui.core.events.KeyboardEvent;
import com.induscorp.prime.testing.ui.core.events.MouseEvent;
import com.induscorp.prime.testing.ui.core.objects.scrollbar.Scrollbar;
import com.induscorp.prime.testing.ui.core.objects.validator.mechanisms.TextValidationMechanism;

/**
 * 
 * @author Madhav Krishna
 *
 */
public abstract class UIObjectValidator {
	protected WebBrowser browser;
	protected UIObject uiObject;
	protected Region region;

	public UIObjectValidator(WebBrowser browser, UIObject uiObject, Region region) {
		this.browser = browser;
		this.uiObject = uiObject;
		if (browser != null) {
			this.region = (region == null) ? Region.create(
					new Rectangle(0, 0, new Double(browser.getAppConfig().getBrowserWindowSize().getWidth()).intValue(),
							new Double(browser.getAppConfig().getBrowserWindowSize().getHeight()).intValue()))
					: region;

		}
	}

	public UIObject getUIObject() {
		return uiObject;
	}

	public UIObjectType geUIObjectType() {
		return uiObject.getType();
	}

	public Region getRegion() {
		return region;
	}

	public void validatePresent(int numRetries) {
		Assert.assertTrue(isPresent(numRetries), "Failed to find element '" + uiObject.getDisplayName() + "'");
	}

	public void validateNotPresent(int numRetries) {
		Assert.assertTrue(!isPresent(numRetries), "Element '" + uiObject.getDisplayName() + "' is already present.");
	}

	public <EVENTNAME> void performAction(InputEvent<EVENTNAME> event, int numRetries) {
		if (event.getType() == InputEventType.mouse) {
			MouseEvent mouseEvent = (MouseEvent) event;
			switch (mouseEvent.getName()) {
			case mouseClick:
				click(numRetries);
				break;
			case mouseRightClick:
				rightClick(numRetries);
				break;
			case mouseDoubleClick:
				doubleClick(numRetries);
				break;
			case mouseClickAndHold:
				clickAndHold(numRetries);
				break;
			case mouseRelease:
				release(numRetries);
				break;
			}
		} else if (event.getType() == InputEventType.keyBoard) {
			KeyboardEvent kbEvent = (KeyboardEvent) event;
			switch (kbEvent.getName()) {
			case kbKeyDown:
				if (kbEvent.getInputText() != null) {
					typeText(kbEvent.getInputText(), kbEvent.getTextLocation(), numRetries);
				}
				performKeyDown(kbEvent.getKey(), numRetries);
				break;
			case kbKeyUp:
				if (kbEvent.getInputText() != null) {
					typeText(kbEvent.getInputText(), kbEvent.getTextLocation(), numRetries);
				}
				performKeyUp(kbEvent.getKey(), numRetries);
				break;
			case keyPressed:
				if (kbEvent.getInputText() != null) {
					typeText(kbEvent.getInputText(), kbEvent.getTextLocation(), numRetries);
				}
				performKeyPressed(kbEvent.getKey(), numRetries);
				break;
			}
		}

	}

	protected boolean matchTextValue(String actualValue, String expectedValue,
			TextValidationMechanism validationMechanism) {
		boolean matched = false;

		if (actualValue == null) {
			return false;
		}

		switch (validationMechanism) {
		case startsWithExpectedValue:
			if (actualValue.startsWith(expectedValue)) {
				matched = true;
			}
			break;
		case containsExpectedValue:
			if (actualValue.contains(expectedValue)) {
				matched = true;
			}
			break;
		case endsWithExpectedValue:
			if (actualValue.contains(expectedValue)) {
				matched = true;
			}
			break;
		case exactMatchWithExpectedValue:
			if (actualValue.equals(expectedValue)) {
				matched = true;
			}
			break;
		case matchWithRegularExpression:
			if (actualValue.matches(expectedValue)) {
				matched = true;
			}
			break;
		case exactMatchWithExpectedValueWithRemovedWhiteSpace:
			if (actualValue.replaceAll(" ", "").equals(expectedValue.replaceAll(" ", ""))) {
				matched = true;
			}
			break;
		}

		return matched;
	}

	protected void validateTextValue(String actualValue, String expectedValue,
			TextValidationMechanism validationMechanism) {
		switch (validationMechanism) {
		case startsWithExpectedValue:
			if (!matchTextValue(actualValue, expectedValue, validationMechanism)) {
				Assert.fail("Actual value '" + actualValue + "' does not starts with expected value '" + expectedValue
						+ "'.");
			}
			break;
		case containsExpectedValue:
			if (!matchTextValue(actualValue, expectedValue, validationMechanism)) {
				Assert.fail(
						"Actual value '" + actualValue + "' does not contain expected value '" + expectedValue + "'.");
			}
			break;
		case endsWithExpectedValue:
			if (!matchTextValue(actualValue, expectedValue, validationMechanism)) {
				Assert.fail("Actual value '" + actualValue + "' does not ends with expected value '" + expectedValue
						+ "'.");
			}
			break;
		case exactMatchWithExpectedValue:
			if (!matchTextValue(actualValue, expectedValue, validationMechanism)) {
				Assert.fail(
						"Actual value '" + actualValue + "' does not equal to expected value '" + expectedValue + "'.");
			}
			break;
		case matchWithRegularExpression:
			if (!matchTextValue(actualValue, expectedValue, validationMechanism)) {
				Assert.fail("Actual value '" + actualValue + "' does not equal to expected regular expression value '"
						+ expectedValue + "'.");
			}
			break;
		case exactMatchWithExpectedValueWithRemovedWhiteSpace:
			if (!matchTextValue(actualValue, expectedValue, validationMechanism)) {
				Assert.fail(
						"Actual value '" + actualValue + "' does not equal to expected value '" + expectedValue + "'.");
			}
			break;
		}
	}

	public String seleniumToSikuliKeyConverter(Keys key) {
		switch (key.name()) {
		case "HOME":
			return Key.HOME;
		case "END":
			return Key.END;
		case "TAB":
			return Key.TAB;
		case "BACK_SPACE":
			return Key.BACKSPACE;
		case "RETURN":
			return Key.ENTER;
		case "ENTER":
			return Key.ENTER;
		case "SHIFT":
			return Key.SHIFT;
		case "CONTROL":
			return Key.CTRL;
		case "ALT":
			return Key.ALT;
		case "ESCAPE":
			return Key.ESC;
		case "SPACE":
			return Key.SPACE;
		case "PAGE_UP":
			return Key.PAGE_UP;
		case "PAGE_DOWN":
			return Key.PAGE_DOWN;
		case "LEFT":
		case "ARROW_LEFT":
			return Key.LEFT;
		case "UP":
		case "ARROW_UP":
			return Key.UP;
		case "RIGHT":
		case "ARROW_RIGHT":
			return Key.RIGHT;
		case "DOWN":
		case "ARROW_DOWN":
			return Key.DOWN;
		case "INSERT":
			return Key.INSERT;
		case "DELETE":
			return Key.DELETE;
		case "SEMICOLON":
			return ";";
		case "F1":
			return Key.F1;
		case "F2":
			return Key.F2;
		case "F3":
			return Key.F3;
		case "F4":
			return Key.F4;
		case "F5":
			return Key.F5;
		case "F6":
			return Key.F6;
		case "F7":
			return Key.F7;
		case "F8":
			return Key.F8;
		case "F9":
			return Key.F9;
		case "F10":
			return Key.F10;
		case "F11":
			return Key.F11;
		case "F12":
			return Key.F12;
		case "META":
			return Key.META;
		case "COMMAND":
			return Key.CMD;
		}
		return null;
	}

	public abstract boolean isPresent(int numRetries);

	public abstract boolean isVisible(int numRetries);

	public abstract void click(int numRetries);

	public abstract void doubleClick(int numRetries);

	public abstract void rightClick(int numRetries);

	public abstract void clickAndHold(int numRetries);

	public abstract void release(int numRetries);

	public abstract void performKeyDown(Keys keys, int numRetries);

	public abstract void performKeyUp(Keys keys, int numRetries);

	public abstract void performKeyPressed(Keys keys, int numRetries);

	public abstract void typeText(String text, NewTextLocation location, int numRetries);

	public abstract UIObjectValidator scrollElementOnViewport(Scrollbar scrollbar);

	public abstract Object findElement(int numRetries);

	public abstract Object findElementNoException(int numRetries);

	public abstract Object findElements(int numRetries);
}
