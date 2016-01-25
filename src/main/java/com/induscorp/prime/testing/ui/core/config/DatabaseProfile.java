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

import com.induscorp.prime.testing.ui.core.config.database.DatabaseQueryHandler;

/**
 * 
 * @author Madhav Krishna
 *
 */
public class DatabaseProfile {
	private String appName;
	private String name;
	private String profileFilePath;
	private DatabaseQueryHandler queryHandler;
	
	public DatabaseProfile(String appName, String profileName, String profileFilePath) {
		this.appName = appName;
		this.name = profileName;
		this.profileFilePath = profileFilePath;
		queryHandler = new DatabaseQueryHandler(profileFilePath);
	}
	
	public String getAppName() {
		return appName;
	}

	public String getName() {
		return name;
	}

	public String getProfileFilePath() {
		return profileFilePath;
	}

	public DatabaseQueryHandler getQueryHandler() {
		return queryHandler;
	}
}
