package com.induscorp.prime.testing.ui.core.config;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import org.testng.Assert;


public class SikuliSettings {
	private String sikuliSettingsFilePath;
	private Properties props;

	public SikuliSettings(String sikuliSettingsFilePath, Properties props) {
		this.sikuliSettingsFilePath = sikuliSettingsFilePath;
		this.props = props;
	}

	public String getPropertyValue(String propName) {
		if (!(propName.startsWith("_") || propName.startsWith("Settings."))) {
			Assert.fail("Property name should start with prefix underscore ( _ ) or Settings.");
		}
		
		if (!this.props.containsKey(propName)) {
			Assert.fail("Please specify the property '" + propName + "' in '" + sikuliSettingsFilePath + "' file.");
		}
		return this.props.getProperty(propName);
	}

	public Map<String, String> getAllSettings() {
		Map<String, String> keyValuePairs = new LinkedHashMap<String, String>();
		for(Object key : props.keySet()) {
			String strKey = (String) key;
			if(strKey.startsWith("Settings.")) {
				keyValuePairs.put(strKey.substring("Settings.".length()), props.getProperty(strKey));
			}
		}
		
		return keyValuePairs;
	}
}
