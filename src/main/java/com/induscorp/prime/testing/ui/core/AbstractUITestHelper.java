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
package com.induscorp.prime.testing.ui.core;

import java.awt.Point;
import java.awt.Rectangle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import com.induscorp.prime.testing.ui.core.config.AppConfig;
import com.induscorp.prime.testing.ui.core.config.TestConfigManager;
import com.induscorp.prime.testing.ui.core.config.UserProfile;
import com.induscorp.prime.testing.ui.core.config.database.DatabaseQueryHandler;
import com.induscorp.prime.testing.ui.core.config.webbrowser.WebBrowser;
import com.induscorp.prime.testing.ui.core.objects.logon.LoginPageValidator;
import com.induscorp.prime.testing.ui.core.objects.logon.LoginSuccessPageValidator;
import com.induscorp.prime.testing.ui.core.objects.webpage.WebPage;
import com.induscorp.prime.testing.ui.core.utils.ScreenCaptureUtil;

import cucumber.api.Scenario;

/**
 * 
 * @author Madhav Krishna
 *
 */
public abstract class AbstractUITestHelper {
	protected Logger logger;

	protected boolean logonTest;
	protected TestConfigManager testConfigManager;

	protected String initAppName;
	protected String initUserProfileName;
	protected String initWebBrowserId;
	protected static WebBrowser initWebBrowser;
	protected WebPage initWebPage;
	protected AppConfig initAppConfig;
	protected UserProfile initUserProfile;

	protected String activeUserProfileName;

	protected AbstractUITestHelper() {
		logger = LoggerFactory.getLogger(this.getClass());
		logonTest = false;
	}

	public void init(String appName, String webBrowserId, WebPage webPage, String userProfileName) {
		

		testConfigManager = TestConfigManager.getInstance();
		initAppConfig = testConfigManager.getAppConfig(appName);
		initUserProfile = testConfigManager.getAppConfig(appName).getUserProfile(userProfileName);

		this.initAppName = appName;
		this.initUserProfileName = userProfileName;
		this.activeUserProfileName = userProfileName;
		this.initWebPage = webPage;
		this.initWebBrowserId = webBrowserId;
	}

	/**
	 * Opens the new browser if it is not opened by the web driver associated to
	 * it. As soon as the browser is opened, this will login to the system
	 * automatically.
	 */
	public void scenarioSetup() {
		//checkLogoutAndLoginAgain(activeUserProfileName);
	}

	public void scenarioTearDown() {

	}

	public String getInitAppName() {
		return initAppName;
	}
	
	public String getInitUserProfileName() {
		return initUserProfileName;
	}

	protected LoginPageValidator createNewLoginPageValidator(WebBrowser browser) {
		String loginPageValidatorCls = testConfigManager.getAppConfig(browser.getAppName())
				.getAppLoginPageValidatorClass();
		LoginPageValidator loginPageValidator = null;
		try {
			loginPageValidator = (LoginPageValidator) Class.forName(loginPageValidatorCls).newInstance();
			loginPageValidator.setInitParams(browser);
		} catch (Throwable th) {
			Assert.fail("Failed to load login page validator class '" + loginPageValidatorCls + "'.", th);
		}
		return loginPageValidator;
	}

	protected LoginSuccessPageValidator createNewLoginSuccessPageValidator(WebBrowser browser) {
		String loginSuccessPageValidatorCls = testConfigManager.getAppConfig(browser.getAppName())
				.getAppLoginSuccessPageValidatorClass();
		LoginSuccessPageValidator loginSuccessPageValidator = null;
		try {
			loginSuccessPageValidator = (LoginSuccessPageValidator) Class.forName(loginSuccessPageValidatorCls)
					.newInstance();
			loginSuccessPageValidator.setInitParams(browser);
		} catch (Throwable th) {
			Assert.fail("Failed to load login page validator class '" + loginSuccessPageValidatorCls + "'.", th);
		}
		return loginSuccessPageValidator;
	}

	public void checkLogoutAndLoginAgain(String userProfileName) {
		initWebBrowser = testConfigManager.getWebBrowser(initAppName, initWebBrowserId);
		LoginPageValidator loginPageValidator = createNewLoginPageValidator(initWebBrowser);
		LoginSuccessPageValidator loginSuccessPageValidator = createNewLoginSuccessPageValidator(initWebBrowser);

		if (loginPageValidator.isLoginPageVisible(userProfileName)) {
			loginPageValidator.login(userProfileName);
			loginSuccessPageValidator.validate(userProfileName);
		} else if (!loginSuccessPageValidator.isLoginSuccessPageVisible(userProfileName)) {
			loginPageValidator.login(userProfileName);
			loginSuccessPageValidator.validate(userProfileName);
		}
		
		if(initWebPage != null) {
			initWebPage.getValidator(initWebBrowser, null).openWebPage();
		}
	}

	public TestConfigManager getTestConfigManager() {
		return TestConfigManager.getInstance();
	}

	public AppConfig getAppConfig(String appName) {
		return testConfigManager.getAppConfig(appName);
	}

	public String getActiveUserProfileName() {
		return activeUserProfileName;
	}

	public void setActiveUserProfileName(String userProfileName) {
		if (!initUserProfileName.equals(userProfileName)) {
			relogin(userProfileName);
			this.activeUserProfileName = userProfileName;
		}
	}	

	/**
	 * Logout first and login again. It does not close the browser.
	 */
	private void relogin(String userProfileName) {
		logoutAndNoQuit();
		checkLogoutAndLoginAgain(userProfileName);
	}

	public void relogin() {
		logoutAndNoQuit();
		checkLogoutAndLoginAgain(activeUserProfileName);
	}

	/**
	 * Logout and does not close browser but the child windows/browsers will get
	 * closed.
	 */
	public void logoutAndNoQuit() {
		initWebBrowser = testConfigManager.getWebBrowser(initAppName, initWebBrowserId);
		LoginPageValidator loginPageValidator = createNewLoginPageValidator(initWebBrowser);
		LoginSuccessPageValidator loginSuccessPageValidator = createNewLoginSuccessPageValidator(initWebBrowser);

		if (loginSuccessPageValidator.isLoginSuccessPageVisible(activeUserProfileName)) {
			loginSuccessPageValidator.logout(activeUserProfileName);
			loginPageValidator.validate(activeUserProfileName);
		}
	}

	/**
	 * Logout and closes the browser and the child windows/browsers. NOTE: To
	 * execute further testcases, new browser will be opened.
	 */
	public void logoutAndQuit() {
		logoutAndNoQuit();

		initWebBrowser.quit();
	}

	public WebBrowser getInitWebBrowser() {
		return initWebBrowser;
	}

	/**
	 * Returns the base web page associated with the test class.
	 * 
	 * @return
	 */
	public WebPage getInitWebPage() {
		return initWebPage;
	}

	public DatabaseQueryHandler getDatabaseQueryHandler(String appName, String dbProfileName) {
		return testConfigManager.getDatabaseQueryHandler(appName, dbProfileName);
	}
	
	public void captureScreenshot(Scenario scenario) {		
		String fileNameHint = scenario.getName().substring(0, 40).replace(" ", "_") + "-" + scenario.getStatus();
		
		Rectangle screenArea = null;
		if(initWebBrowser != null) {
			screenArea = new Rectangle(new Point(0, 0), initWebBrowser.getWindowSize());
		}
		
		ScreenCaptureUtil.capture(testConfigManager.getAppScreenCaptureDirectory(), null,
				fileNameHint, screenArea);
	}
	
	public void captureScreenshot(Scenario scenario, String status) {		
		String fileNameHint;
		if(scenario.getName().length() > 40) {
			fileNameHint = scenario.getName().substring(0, 40).replace(" ", "_");
		} else {
			fileNameHint = scenario.getName().replace(" ", "_");
		}
		
		if(status != null) {
			fileNameHint = fileNameHint + "-" + status;
		}
		
		Rectangle screenArea = null;
		if(initWebBrowser != null) {
			screenArea = new Rectangle(new Point(0, 0), initWebBrowser.getWindowSize());
		}
		
		ScreenCaptureUtil.capture(testConfigManager.getAppScreenCaptureDirectory(), null,
				fileNameHint, screenArea);
	}

	public void captureScreenshot(String fileNameHint) {
		Rectangle screenArea = null;
		if(initWebBrowser != null) {
			screenArea = new Rectangle(new Point(0, 0), initWebBrowser.getWindowSize());
		}
		
		ScreenCaptureUtil.capture(testConfigManager.getAppScreenCaptureDirectory(), null,
				fileNameHint, screenArea);
	}

}
