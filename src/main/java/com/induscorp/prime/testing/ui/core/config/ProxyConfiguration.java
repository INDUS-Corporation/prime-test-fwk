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

import java.util.Properties;

import org.testng.Assert;

/**
 * 
 * @author Madhav Krishna
 *
 */
public class ProxyConfiguration {
	private ProxyConfigurationType proxyConfigType;
		
	private String httpProxyHostname;
	private String httpProxyPort;
	
	private String sslProxyHostname;
	private String sslProxyPort;
	
	private String ftpProxyHostname;
	private String ftpProxyPort;
	
	private String socksHostname;
	private String socksPort;
	private String socksVersion;
	
	private String socksUsername;
	private String socksPassword;
	
	private String noProxyFor;
	
	public ProxyConfiguration(String appName, Properties properties) {
		proxyConfigType = ProxyConfigurationType.NO_PROXY;
		initProxyConfig(appName, properties);
	}
	
	private void initProxyConfig(String appName, Properties properties) {
		String propValue = properties.getProperty("PROXY_CONFIGURATION");
		if (propValue == null || "".equals(propValue.trim())) {
			Assert.fail("FATAL: Please specify PROXY_CONFIGURATION in AppConfig.properties. AppName: " + appName
					+ ". Exiting ...");
			System.exit(1);
		} else {
			propValue = propValue.trim();
			proxyConfigType = ProxyConfigurationType.valueOf(propValue);
		}
		
		httpProxyHostname = properties.getProperty("HTTP_PROXY_HOSTNAME");
		if (httpProxyHostname == null || "".equals(httpProxyHostname.trim())) {			
		} else {
			httpProxyHostname = httpProxyHostname.trim();
		}
		
		httpProxyPort = properties.getProperty("HTTP_PROXY_PORT");
		if (httpProxyPort == null || "".equals(httpProxyPort.trim())) {
		} else {
			httpProxyPort = httpProxyPort.trim();
		}
		
		sslProxyHostname = properties.getProperty("SSL_PROXY_HOSTNAME");
		if (sslProxyHostname == null || "".equals(sslProxyHostname.trim())) {
		} else {
			sslProxyHostname = sslProxyHostname.trim();
		}
		
		sslProxyPort = properties.getProperty("SSL_PROXY_PORT");
		if (sslProxyPort == null || "".equals(sslProxyPort.trim())) {
		} else {
			sslProxyPort = sslProxyPort.trim();
		}
		
		ftpProxyHostname = properties.getProperty("FTP_PROXY_HOSTNAME");
		if (ftpProxyHostname == null || "".equals(ftpProxyHostname.trim())) {
		} else {
			ftpProxyHostname = ftpProxyHostname.trim();
		}
		
		ftpProxyPort = properties.getProperty("FTP_PROXY_PORT");
		if (ftpProxyPort == null || "".equals(ftpProxyPort.trim())) {
		} else {
			ftpProxyPort = ftpProxyPort.trim();
		}
		
		socksHostname = properties.getProperty("SOCKS_HOSTNAME");
		if (socksHostname == null || "".equals(socksHostname.trim())) {
		} else {
			socksHostname = socksHostname.trim();
		}
		
		socksPort = properties.getProperty("SOCKS_PORT");
		if (socksPort == null || "".equals(socksPort.trim())) {
		} else {
			socksPort = socksPort.trim();
		}
		
		socksVersion = properties.getProperty("SOCKS_VERSION");
		if (socksVersion == null || "".equals(socksVersion.trim())) {
		} else {
			socksVersion = socksVersion.trim();
		}
		
		socksUsername = properties.getProperty("SOCKS_USERNAME");
		if (socksUsername == null || "".equals(socksUsername.trim())) {
		} else {
			socksUsername = socksUsername.trim();
		}
		
		socksPassword = properties.getProperty("SOCKS_PASSWORD");
		if (socksPassword == null || "".equals(socksPassword.trim())) {
		} else {
			socksPassword = socksPassword.trim();
		}
		
		noProxyFor = properties.getProperty("NO_PROXY_FOR");
		if (noProxyFor == null || "".equals(noProxyFor.trim())) {
		} else {
			noProxyFor = noProxyFor.trim();
		}
	}

	public ProxyConfigurationType getProxyConfigType() {
		return proxyConfigType;
	}

	public String getHttpProxyHostname() {
		return httpProxyHostname;
	}

	public String getHttpProxyPort() {
		return httpProxyPort;
	}

	public String getSslProxyHostname() {
		return sslProxyHostname;
	}

	public String getSslProxyPort() {
		return sslProxyPort;
	}

	public String getFtpProxyHostname() {
		return ftpProxyHostname;
	}

	public String getFtpProxyPort() {
		return ftpProxyPort;
	}

	public String getSocksHostname() {
		return socksHostname;
	}

	public String getSocksPort() {
		return socksPort;
	}

	public String getSocksVersion() {
		return socksVersion;
	}

	public String getSocksUsername() {
		return socksUsername;
	}

	public String getSocksPassword() {
		return socksPassword;
	}

	public String getNoProxyFor() {
		return noProxyFor;
	}
	
}
