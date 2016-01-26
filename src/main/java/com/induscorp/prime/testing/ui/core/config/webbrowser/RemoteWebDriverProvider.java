package com.induscorp.prime.testing.ui.core.config.webbrowser;

import org.openqa.selenium.remote.RemoteWebDriver;

import com.induscorp.prime.testing.ui.core.config.AppConfig;

public abstract class RemoteWebDriverProvider {
	protected AppConfig appConfig;

	public RemoteWebDriverProvider() {

	}

	public void setAppConfig(AppConfig appConfig) {
		this.appConfig = appConfig;
	}

	public abstract RemoteWebDriver createRemoteWebDriver() throws Exception;
	public abstract String getWebBrowserName();
}
