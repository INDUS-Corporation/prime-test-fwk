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
package com.induscorp.prime.testing.ui.core.objects.logon;

import org.sikuli.script.Region;
import org.testng.Assert;

import com.induscorp.prime.testing.ui.core.config.webbrowser.WebBrowser;
import com.induscorp.prime.testing.ui.core.objects.UIObject;
import com.induscorp.prime.testing.ui.core.objects.UIObjectValidator;

/**
 * 
 * @author Madhav Krishna
 *
 */
public abstract class LoginPageValidator extends UIObjectValidator {

	public LoginPageValidator(UIObject locator, Region region) {
		super(null, locator, region);
	}

	public void validate(String activeUserProfileName) {
		Assert.assertNotNull(browser, "WebBrowser instance is null. Please set before using this API.");
		validateInfo(activeUserProfileName);
	}

	public void login(String activeUserProfileName) {
		tryLogin(activeUserProfileName);		
	}

	public boolean isLoginPageVisible(String activeUserProfileName) {
		Assert.assertNotNull(browser, "WebBrowser instance is null. Please set before using this API.");
		return checkLoginPageVisible(activeUserProfileName);
	}

	public abstract void setInitParams(WebBrowser browser);

	protected abstract void tryLogin(String activeUserProfileName);

	protected abstract void validateInfo(String activeUserProfileName);

	public abstract boolean checkLoginPageVisible(String activeUserProfileName);

}
