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
package com.induscorp.prime.testing.ui.core.config;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import com.induscorp.prime.testing.ui.core.config.webbrowser.WebBrowserType;

/**
 * 
 * @author Madhav Krishna
 *
 */
public class FirefoxDriverConfig {
	private String profilePath;
	private String driverFilePath;
	private Map<String, String> browserExtensions;
	private Map<String, String> browserPreferences;
	private Map<String, String> driverCapabilities;

	public FirefoxDriverConfig(String seleniumConfigDir, Properties properties) {
		browserExtensions = new HashMap<String, String>(3);
		browserPreferences = new HashMap<String, String>(3);
		init(seleniumConfigDir, properties);
	}

	private void init(String seleniumConfigDir, Properties properties) {
		driverFilePath = properties.getProperty("BROWSER_DRIVER_FILENAME");
		if (driverFilePath == null || "".equals(driverFilePath.trim())) {
		} else {
			driverFilePath = seleniumConfigDir + File.separator + "web-drivers" + File.separator
					+ WebBrowserType.firefox.name() + File.separator + driverFilePath.trim();
		}

		profilePath = seleniumConfigDir + File.separator + "web-drivers" + File.separator
				+ WebBrowserType.firefox.name() + File.separator + "profile";

		String prop, propValue;
		for (Object key : properties.keySet()) {
			prop = String.valueOf(key).trim();
			propValue = properties.getProperty(String.valueOf(key));

			// load extensions
			if (prop.startsWith("BROWSER-EXT_")) {
				browserExtensions.put(prop.substring("BROWSER-EXT_".length()),
						seleniumConfigDir + File.separator + "web-drivers" + File.separator
								+ WebBrowserType.firefox.name() + File.separator + "extensions" + File.separator
								+ propValue);
			}

			// load preferences
			if (prop.startsWith("BROWSER-PREF_")) {
				browserPreferences.put(prop.substring("BROWSER-PREF_".length()), propValue);
			}
		}
		
		// initialize driver capabilities
		driverCapabilities = new LinkedHashMap<String, String>();
		for(Object key : properties.keySet()) {
			String strKey = (String) key;
			if(strKey.startsWith("DriverCapability.")) {
				driverCapabilities.put(strKey.substring("DriverCapability.".length()), properties.getProperty(strKey));
			}
		}
	}

	public String getProfilePath() {
		return profilePath;
	}

	public String getDriverFilePath() {
		return driverFilePath;
	}

	public Map<String, String> getBrowserExtensions() {
		return browserExtensions;
	}

	public Map<String, String> getBrowserPrefs() {
		return browserPreferences;
	}
	
	public Map<String, String> getDriverCapabilities() {
		return driverCapabilities;
	}
}
