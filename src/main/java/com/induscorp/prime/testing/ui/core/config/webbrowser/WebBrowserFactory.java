package com.induscorp.prime.testing.ui.core.config.webbrowser;

import java.io.File;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.sikuli.basics.Settings;
import org.testng.Assert;

import com.induscorp.prime.testing.ui.core.config.AppConfig;
import com.induscorp.prime.testing.ui.core.config.ChromeDriverConfig;
import com.induscorp.prime.testing.ui.core.config.FirefoxDriverConfig;
import com.induscorp.prime.testing.ui.core.config.IEDriverConfig;
import com.induscorp.prime.testing.ui.core.config.ProxyConfiguration;
import com.induscorp.prime.testing.ui.core.config.TestConfigManager;

/**
 * This is a factory class used to create web browser that are used to perform
 * operation on the web pages.
 * 
 * @author Madhav Krishna
 */
public class WebBrowserFactory {
	private static WebBrowserFactory instance = null;
	// Key: browserID, Value WebBrowser
	private Map<String, WebBrowser> appBrowserMap;
	private TestConfigManager testConfigMgr;

	public static WebBrowserFactory getInstance() {
		if (instance == null) {
			synchronized (WebBrowserFactory.class) {
				if (instance == null) {
					instance = new WebBrowserFactory();
				}
			}
		}
		return instance;
	}

	private WebBrowserFactory() {
		this.testConfigMgr = TestConfigManager.getInstance();
		appBrowserMap = new HashMap<String, WebBrowser>(3);
		initializeSikuli();
	}

	protected void initializeSikuli() {
		try {
			Map<String, String> settings = testConfigMgr.getSikuliSettings().getAllSettings();
			String value;
			for (String name : settings.keySet()) {
				value = settings.get(name);

				Field f = Settings.class.getDeclaredField(name);
				f.set(null, createObjectFromTypedValue(name, value));
			}
		} catch (Throwable th) {
			Assert.fail("Failed to initialize the sikuli driver.", th);
		}
	}

	protected Object createObjectFromTypedValue(String propertyName, String typedValue) {
		String typeValueArr[] = typedValue.split(":");
		Assert.assertTrue(typeValueArr.length > 1,
				"typedValue format is wrong for property '" + propertyName + "'. It should be <data-type>:<value>");
		
		switch(typeValueArr[0]) {
		case "integer":
			return Integer.parseInt(typeValueArr[1]);
		case "string":
			return typeValueArr[1];
		case "float":
			return Float.parseFloat(typeValueArr[1]);
		case "double":
			return Double.parseDouble(typeValueArr[1]);
		case "boolean":
			return Boolean.parseBoolean(typeValueArr[1]);
		}

		Assert.fail("'" + typeValueArr[0] + "' datatype is not supported for '" + propertyName + "' property.");
		return null;
	}

	public synchronized WebBrowser getAppWebBrowser(String appName, String browserId) {
		AppConfig appConfig = testConfigMgr.getAppConfig(appName);
		WebBrowserType type = appConfig.getAppWebBrowser();
		String loginURL = appConfig.getAppLaunchUrl();

		WebBrowser browser = appBrowserMap.get(appName + ":" + browserId);
		try {
			switch (type) {
			case firefox:
				FirefoxDriverConfig firefoxDriverCfg = testConfigMgr.getFirefoxDriverConfig();
				if (browser == null) {
					FirefoxDriver firefoxDriver = null;
					FirefoxProfile firefoxProfile = new FirefoxProfile(new File(firefoxDriverCfg.getProfilePath()));
					DesiredCapabilities dc = DesiredCapabilities.firefox();
					if (appConfig.isEnableWebBrowserExtension()) {
						File firebugExtnFile;
						for (String extn : firefoxDriverCfg.getBrowserExtensions().values()) {
							firebugExtnFile = new File(extn);
							firefoxProfile.addExtension(firebugExtnFile);
						}

						for (String key : firefoxDriverCfg.getBrowserPrefs().keySet()) {
							firefoxProfile.setPreference(key, firefoxDriverCfg.getBrowserPrefs().get(key));
						}
					}

					Proxy proxy = getProxyInfo(appConfig);
					if (proxy != null) {
						dc.setCapability(CapabilityType.PROXY, proxy);
					}

					firefoxDriver = new FirefoxDriver(new FirefoxBinary(), firefoxProfile, dc);

					browser = new WebBrowser(browserId, appName, appConfig, testConfigMgr, this, firefoxDriver, type);

					/*
					 * browser.getWebDriver().manage().timeouts()
					 * .implicitlyWait(5, TimeUnit.SECONDS);
					 */
					browser.getSeleniumWebDriver().manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
					browser.getSeleniumWebDriver().manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);

					// browser.getSeleniumWebDriver().manage().window().maximize();
					browser.getSeleniumWebDriver().manage().window().setPosition(new Point(0, 0));
					browser.getSeleniumWebDriver().manage().window()
							.setSize(new Dimension(new Double(appConfig.getBrowserWindowSize().getWidth()).intValue(),
									new Double(appConfig.getBrowserWindowSize().getHeight()).intValue()));

					browser.getSeleniumWebDriver().navigate().to(loginURL);
					appBrowserMap.put(appName + ":" + browserId, browser);
				} else {
					browser.setNewInstance(false);
				}

				break;
			case chrome:
				ChromeDriverConfig chromeDriverCfg = testConfigMgr.getChromeDriverConfig();
				System.setProperty("webdriver.chrome.driver", chromeDriverCfg.getDriverFilePath());
				if (browser == null) {
					ChromeDriver chromeDriver = null;
					DesiredCapabilities chromeCapabilities = DesiredCapabilities.chrome();
					if (appConfig.isEnableWebBrowserExtension()) {
						chromeDriver = new ChromeDriver(chromeCapabilities);
					} else {
						chromeDriver = new ChromeDriver(chromeCapabilities);
					}

					browser = new WebBrowser(browserId, appName, appConfig, testConfigMgr, this, chromeDriver, type);
					// browser.getWebDriver().manage().timeouts()
					// .implicitlyWait(3, TimeUnit.SECONDS);

					// browser.getSeleniumWebDriver().manage().window().maximize();
					browser.getSeleniumWebDriver().manage().window().setPosition(new Point(0, 0));
					browser.getSeleniumWebDriver().manage().window()
							.setSize(new Dimension(new Double(appConfig.getBrowserWindowSize().getWidth()).intValue(),
									new Double(appConfig.getBrowserWindowSize().getHeight()).intValue()));

					browser.getSeleniumWebDriver().navigate().to(loginURL);
					appBrowserMap.put(appName + ":" + browserId, browser);
				} else {
					browser.setNewInstance(false);
				}

				break;
			case internetExplorer:
				IEDriverConfig ieDriverCfg = testConfigMgr.getIEDriverConfig();
				System.setProperty("webdriver.ie.driver", ieDriverCfg.getDriverFilePath());
				if (browser == null) {
					DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
					ieCapabilities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, loginURL);
					ieCapabilities.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
					ieCapabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);

					InternetExplorerDriver ieDriver = null;
					if (appConfig.isEnableWebBrowserExtension()) {
						ieDriver = new InternetExplorerDriver(ieCapabilities);
					} else {
						ieDriver = new InternetExplorerDriver(ieCapabilities);
					}

					browser = new WebBrowser(browserId, appName, appConfig, testConfigMgr, this, ieDriver, type);
					// browser.getWebDriver().manage().timeouts()
					// .implicitlyWait(3, TimeUnit.SECONDS);

					// browser.getSeleniumWebDriver().manage().window().maximize();
					browser.getSeleniumWebDriver().manage().window().setPosition(new Point(0, 0));
					browser.getSeleniumWebDriver().manage().window()
							.setSize(new Dimension(new Double(appConfig.getBrowserWindowSize().getWidth()).intValue(),
									new Double(appConfig.getBrowserWindowSize().getHeight()).intValue()));

					// browser.getSeleniumWebDriver().navigate().to(loginURL);
					appBrowserMap.put(appName + ":" + browserId, browser);
				} else {
					browser.setNewInstance(false);
				}

				break;
			case remoteWebDriverProvider:
				if (browser == null) {
					RemoteWebDriver webDriver = appConfig.getRemoteWebDriverProvider().createRemoteWebDriver();
					browser = new WebBrowser(browserId, appName, appConfig, testConfigMgr, this, webDriver, type);
					browser.getSeleniumWebDriver().manage().window().setPosition(new Point(0, 0));
					browser.getSeleniumWebDriver().manage().window()
							.setSize(new Dimension(new Double(appConfig.getBrowserWindowSize().getWidth()).intValue(),
									new Double(appConfig.getBrowserWindowSize().getHeight()).intValue()));

					browser.getSeleniumWebDriver().navigate().to(loginURL);
					appBrowserMap.put(appName + ":" + browserId, browser);
				} else {
					browser.setNewInstance(false);
				}
				break;
			default:
				throw new IllegalArgumentException("Web browser '" + type + "' is not supported.");
			}

		} catch (Exception ex) {
			Assert.fail("Failed to initialize " + type.name() + " web browser. Going to exit... ", ex);
			System.exit(1);
		}

		return browser;
	}

	public void destroyAppWebBrowser(String appName, String browserId) {
		WebBrowser browser = appBrowserMap.get(appName + ":" + browserId);
		if (browser != null) {
			browser.getSeleniumWebDriver().quit();
			appBrowserMap.remove(appName + ":" + browserId);
		}
	}

	public void destroyAllWebBrowser(String appName) {
		List<String> remBrowsers = new LinkedList<String>();
		for (String browser : appBrowserMap.keySet()) {
			if (browser.startsWith(appName)) {
				remBrowsers.add(browser);
			}
		}

		for (String browser : remBrowsers) {
			appBrowserMap.get(browser).getSeleniumWebDriver().quit();
			appBrowserMap.remove(browser);
		}
	}

	public void destroyAllWebBrowser() {
		List<String> remBrowsers = new LinkedList<String>();
		for (String browser : appBrowserMap.keySet()) {
			remBrowsers.add(browser);
		}

		for (String browser : remBrowsers) {
			appBrowserMap.get(browser).getSeleniumWebDriver().quit();
			appBrowserMap.remove(browser);
		}
	}

	private Proxy getProxyInfo(AppConfig appConfig) {
		ProxyConfiguration proxyConfig = appConfig.getProxyConfig();
		Proxy proxy = new Proxy();

		switch (proxyConfig.getProxyConfigType()) {
		case NO_PROXY:
			proxy = null;
			break;
		case AUTO_DETECT:
			proxy.setProxyType(ProxyType.AUTODETECT);
			proxy.setAutodetect(true);
			break;
		case USE_SYSTEM_PROXY:
			proxy.setProxyType(ProxyType.SYSTEM);
			break;
		case MANUAL_PROXY:
			proxy.setProxyType(ProxyType.MANUAL);
			proxy.setHttpProxy(proxyConfig.getHttpProxyHostname() + ":" + proxyConfig.getHttpProxyPort());
			proxy.setSslProxy(proxyConfig.getSslProxyHostname() + ":" + proxyConfig.getSslProxyPort());
			proxy.setFtpProxy(proxyConfig.getFtpProxyHostname() + ":" + proxyConfig.getFtpProxyPort());

			proxy.setSocksProxy(proxyConfig.getSocksHostname() + ":" + proxyConfig.getSocksPort());
			proxy.setSocksUsername(proxyConfig.getSocksUsername());
			proxy.setSocksPassword(proxyConfig.getSocksPassword());

			proxy.setNoProxy(proxyConfig.getNoProxyFor());

			break;
		}

		return proxy;
	}
}
