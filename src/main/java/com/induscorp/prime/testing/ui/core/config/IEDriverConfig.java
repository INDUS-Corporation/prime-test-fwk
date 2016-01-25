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
import java.util.Properties;

import com.induscorp.prime.testing.ui.core.config.webbrowser.WebBrowserType;

/**
 * 
 * @author Madhav Krishna
 *
 */
public class IEDriverConfig {
	private String driverFilePath;

	public IEDriverConfig(String seleniumConfigDir, Properties properties) {
		init(seleniumConfigDir, properties);
	}

	private void init(String seleniumConfigDir, Properties properties) {
		driverFilePath = properties.getProperty("BROWSER_DRIVER_FILENAME");
		if (driverFilePath == null || "".equals(driverFilePath.trim())) {
		} else {
			driverFilePath = seleniumConfigDir + File.separator + "web-drivers" + File.separator
					+ WebBrowserType.internetExplorer.name() + File.separator + driverFilePath.trim();
		}
	}

	public String getDriverFilePath() {
		return driverFilePath;
	}
}
