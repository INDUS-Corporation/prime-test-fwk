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

import java.awt.Rectangle;

import org.sikuli.script.Region;
import org.testng.Assert;

import com.induscorp.prime.testing.ui.core.commons.UIObjectType;
import com.induscorp.prime.testing.ui.core.config.webbrowser.WebBrowser;
import com.induscorp.prime.testing.ui.core.objects.UIObject;

/**
 * 
 * @author Madhav Krishna
 *
 */
public abstract class LoginSuccessPageValidator {
	protected WebBrowser browser;
	protected UIObject uiObject;
	protected Region region;
	
	public LoginSuccessPageValidator(UIObject locator, Region region) {
		this.uiObject = locator;
		if (browser != null) {
			this.region = (region == null) ? new Region(
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

	public void validate(String activeUserProfileName) {
		Assert.assertNotNull(browser, "WebBrowser instance is null. Please set before using this API.");
		validateInfo(activeUserProfileName);
	}

	public void logout(String activeUserProfileName) {
		Assert.assertNotNull(browser, "WebBrowser instance is null. Please set before using this API.");
		tryLogout(activeUserProfileName);		
	}

	public boolean isLoginSuccessPageVisible(String activeUserProfileName) {
		Assert.assertNotNull(browser, "WebBrowser instance is null. Please set before using this API.");
		return checkLoginSuccessPageVisible(activeUserProfileName);
	}

	public void setInitParams(WebBrowser browser) {
		this.browser = browser;
	}

	protected abstract void tryLogout(String activeUserProfileName);

	protected abstract void validateInfo(String activeUserProfileName);

	protected abstract boolean checkLoginSuccessPageVisible(String activeUserProfileName);

}
