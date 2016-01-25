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

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.testng.Assert;

/**
 * 
 * @author Madhav Krishna
 *
 */
public class UserProfile {
	private String appName;
	private String name;
	private String appLoginUserId;
	private String appLoginUserPassword;
	private String userAccountType;
	private String userAccountTypeCode;
	private List<String> userRoles;
	private Map<String, String> additionalProps;

	public UserProfile(String appName, String profileName, Properties properties) {
		this.appName = appName;
		userRoles = new LinkedList<String>();
		additionalProps = new HashMap<String, String>();
		initUserProfile(appName, profileName, properties);
	}

	private void initUserProfile(String appName, String profileName, Properties properties) {
		name = properties.getProperty("PROFILE_NAME");
		if (name == null || !profileName.equals(name.trim())) {
			Assert.fail("FATAL: User profile name '" + name
					+ "' specified in file is not same as configured in AppConfig.properties file. AppName - '"
					+ appName + "'. Exiting ...");
			System.exit(1);
		} else {
			name = name.trim();
		}

		appLoginUserId = properties.getProperty("APP_LOGIN_USER_ID");
		if (appLoginUserId == null || "".equals(appLoginUserId.trim())) {
			Assert.fail("FATAL: Please specify APP_LOGIN_USER_ID in " + profileName + ".properties file. AppName: "
					+ appName + ". Exiting ...");
			System.exit(1);
		} else {
			appLoginUserId = appLoginUserId.trim();
		}

		appLoginUserPassword = properties.getProperty("APP_LOGIN_USER_PASSWORD");
		if (appLoginUserPassword == null || "".equals(appLoginUserPassword.trim())) {
			Assert.fail("FATAL: Please specify appLoginUserPassword in " + profileName + ".properties file. AppName: "
					+ appName + ". Exiting ...");
			System.exit(1);
		} else {
			appLoginUserPassword = appLoginUserPassword.trim();
		}

		userAccountType = properties.getProperty("USER_ACCOUNT_TYPE");
		if (userAccountType == null || "".equals(userAccountType.trim())) {
			Assert.fail("FATAL: Please specify USER_ACCOUNT_TYPE in " + profileName + ".properties file. AppName: "
					+ appName + ". Exiting ...");
			System.exit(1);
		} else {
			userAccountType = userAccountType.trim();
		}

		userAccountTypeCode = properties.getProperty("USER_ACCOUNT_TYPE_CODE");
		if (userAccountTypeCode == null || "".equals(userAccountTypeCode.trim())) {
			Assert.fail("FATAL: Please specify USER_ACCOUNT_TYPE_CODE in " + profileName + ".properties file. AppName: "
					+ appName + ". Exiting ...");
			System.exit(1);
		} else {
			userAccountTypeCode = userAccountTypeCode.trim();
		}

		String propValue = properties.getProperty("USER_ROLES");
		if (propValue == null || "".equals(propValue.trim())) {
			// do nothing
		} else {
			String[] arr = propValue.split(",");
			String keyStr;
			for (String item : arr) {
				keyStr = item.trim();
				if ("".equals(keyStr)) {
					continue;
				}
				userRoles.add(keyStr);
			}
		}

		String keyStr;
		for (Object key : properties.keySet()) {
			keyStr = String.valueOf(key);
			if (keyStr.startsWith("_")) {
				additionalProps.put(keyStr, properties.getProperty(keyStr));
			}
		}
	}

	public String getName() {
		return name;
	}

	public String getAppLoginUserId() {
		return appLoginUserId;
	}

	public String getAppLoginUserPassword() {
		return appLoginUserPassword;
	}

	public String getUserAccountType() {
		return userAccountType;
	}

	public String getUserAccountTypeCode() {
		return userAccountTypeCode;
	}

	public List<String> getUserRoles() {
		return userRoles;
	}

	public String getAdditionalPropertyValue(String propName) {
		Assert.assertTrue(additionalProps.containsKey(propName), "Please specify the additional property '" + propName
				+ "' in application '" + appName + "' user profile '" + name + ".properties' file.");
		return additionalProps.get(propName);
	}
}
