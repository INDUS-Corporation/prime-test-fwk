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
package com.induscorp.prime.testing.ui.core.config.webbrowser;

import java.awt.Dimension;

import org.openqa.selenium.WebDriver;
import org.sikuli.script.Screen;

import com.induscorp.prime.testing.ui.core.config.AppConfig;
import com.induscorp.prime.testing.ui.core.config.TestConfigManager;

/**
 * This is class used to perform operation on the web browser and its web pages.
 * 
 * @author Madhav Krishna
 */
public class WebBrowser {
	private String appName;
	private String id;
	private WebDriver seleniumWebDriver;
	private boolean newInstance;
	private WebBrowserType type;
	private WebBrowserFactory browserFactory;
	private Screen sikuliDriver;
	private AppConfig appConfig;
	private TestConfigManager testCfgMgr;

	public WebBrowser(String id, String appName, AppConfig appConfig, TestConfigManager testCfgMgr,
			WebBrowserFactory factory, WebDriver seleniumWebDriver,
			WebBrowserType type) {
		this.id = id;
		this.appName = appName;
		this.appConfig = appConfig;
		this.testCfgMgr = testCfgMgr;
		this.browserFactory = factory;
		this.seleniumWebDriver = seleniumWebDriver;
		this.type = type;
		this.sikuliDriver = new Screen();
		this.newInstance = true;
	}
	
	public Dimension getWindowSize() {
		return appConfig.getBrowserWindowSize();
	}

	public String getAppName() {
		return appName;
	}
	
	public AppConfig getAppConfig() {
		return appConfig;
	}
	
	public TestConfigManager getTestConfigManager() {
		return testCfgMgr;
	}

	public String getId() {
		return id;
	}

	public WebDriver getSeleniumWebDriver() {
		return seleniumWebDriver;
	}

	public WebBrowserType getWebBrowserType() {
		return type;
	}

	public Screen getSikuliScreen() {
		return sikuliDriver;
	}

	public void openURL(String url) {
		seleniumWebDriver.navigate().to(url);
	}

	public void openDefaultURL() {
		openURL(appConfig.getAppLaunchUrl());
	}

	/**
	 * Returns true if this object is created first time else it will return
	 * false.
	 * 
	 * @return
	 */
	public boolean isNewInstance() {
		return newInstance;
	}

	/**
	 * NOTE: This API is used by WebBrowserFactory class. Please avoid to use
	 * this api other places.
	 * 
	 * Sets the flag to indicate weather the returning object is a new object or
	 * the existing object. Based on that Framework will do the automatic login.
	 * 
	 * @param newInstance
	 */
	public void setNewInstance(boolean newInstance) {
		if (this.newInstance != newInstance) {
			this.newInstance = newInstance;
		}
	}

	public void waitForMilliSeconds(long milliSeconds) {
		try {
			Thread.sleep(milliSeconds);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void waitForSeconds(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Returns the operating system name.
	 * @return the operating system name.
	 */
	public String getOSName() {
		return System.getProperty("os.name");		
	}
	
	/**
	 * Returns the operating system short name i.e. windows, linux
	 * @return the operating system short name.
	 */
	public String getOSShortName() {
		String osName = getOSName();				
		if(osName != null) {
			return osName.trim().split(" ")[0];
		}
		return null;
	}

	/**
	 * Refreshes the web browser.
	 */
	public void refresh() {
		seleniumWebDriver.navigate().refresh();		
	}

	/**
	 * This method is used to bring the window to front to perform the
	 * operation.
	 */
	public void setFocus() {
		// TODO: This api doesnt work. check for the alternative to fix it.
		seleniumWebDriver.switchTo().window(seleniumWebDriver.getWindowHandle());
	}

	/**
	 * Closes web browser window.
	 */
	public void quit() {
		browserFactory.destroyAppWebBrowser(appName, id);
	}
}
