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
import org.sikuli.script.Match;
import org.sikuli.script.Region;

import com.induscorp.prime.testing.ui.core.config.webbrowser.WebBrowser;
import com.induscorp.prime.testing.ui.core.objects.button.ButtonValidator;
import com.induscorp.prime.testing.ui.core.objects.scrollbar.Scrollbar;
import com.induscorp.prime.testing.ui.core.objects.validator.mechanisms.TextValidationMechanism;

/**
 * 
 * @author Madhav Krishna
 *
 */
public class ButtonValidatorSI extends ButtonValidator {
	protected ButtonSI btnImgObj;

	public ButtonValidatorSI(WebBrowser browser, ButtonSI uiObject, Region region) {
		super(browser, uiObject, region);
		btnImgObj = uiObject;
	}

	@Override
	public void validateName(String expectedName, TextValidationMechanism validationMechanism, int numRetries) {
		// TODO Auto-generated method stub

	}

	@Override
	public void validateDisabled(int numRetries) {
		// TODO Auto-generated method stub

	}

	@Override
	public void validateEnabled(int numRetries) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isPresent(int numRetries) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isVisible(int numRetries) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void click(int numRetries) {
		// TODO Auto-generated method stub

	}

	@Override
	public void doubleClick(int numRetries) {
		// TODO Auto-generated method stub

	}

	@Override
	public void rightClick(int numRetries) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clickAndHold(int numRetries) {
		// TODO Auto-generated method stub

	}

	@Override
	public void release(int numRetries) {
		// TODO Auto-generated method stub

	}

	@Override
	public void performKeyDown(Keys keys, int numRetries) {
		// TODO Auto-generated method stub

	}

	@Override
	public void performKeyUp(Keys keys, int numRetries) {
		// TODO Auto-generated method stub

	}

	@Override
	public void performKeyPressed(Keys keys, int numRetries) {
		// TODO Auto-generated method stub

	}

	@Override
	public void typeText(String text, int numRetries) {
		// TODO Auto-generated method stub

	}

	@Override
	public ButtonValidatorSI scrollElementOnViewport(Scrollbar scrollbar) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public Match findElement(int numRetries) {
		return null;
	}

	@Override
	public Match findElementNoException(int numRetries) {
		return null;
	}

	@Override
	public List<Match> findElements(int numRetries) {
		return null;
	}

}
