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
package com.induscorp.prime.testing.ui.core.objects.webpage;

import org.sikuli.script.Region;

import com.induscorp.prime.testing.ui.core.commons.UIObjectType;
import com.induscorp.prime.testing.ui.core.commons.LocatorType;
import com.induscorp.prime.testing.ui.core.config.webbrowser.WebBrowser;
import com.induscorp.prime.testing.ui.core.objects.UIObject;

/**
 * 
 * @author Madhav Krishna
 *
 */
public class WebPage extends UIObject {
	private UIObject title;
	private WebPagePath launchPath;
	private WebPageRecognitionItems pageRecognitionItems;
	private WebPageStatus status;

	/**
	 * 
	 * @param webPageTitle
	 *            Page title
	 * @param launchPath
	 *            The launchPath is a path to locate the web page by performing
	 *            certain operations.
	 * @param pageRecognitionItems
	 *            Parameters used to recognize the web page once after the page
	 *            is being opened by performing certain operations (that are
	 *            associated with launchPath).
	 */
	public WebPage(UIObject webPageTitle, WebPagePath launchPath,
			WebPageRecognitionItems pageRecognitionItems) {
		super(LocatorType.WEB_PAGE, UIObjectType.webPage, null);
		this.title = webPageTitle;
		this.launchPath = launchPath;
		this.pageRecognitionItems = pageRecognitionItems;
		status = WebPageStatus.Closed;
	}

	public UIObject getTitle() {
		return title;
	}

	public WebPagePath getLaunchPath() {
		return launchPath;
	}

	public WebPageRecognitionItems getPageRecognitionItems() {
		return pageRecognitionItems;
	}

	@Override
	public WebPageValidator getValidator(WebBrowser browser, Region region) {
		return new WebPageValidator(browser, this);
	}

	public WebPageStatus getStatus() {
		return status;
	}

	public void setStatus(WebPageStatus status) {
		this.status = status;
	}

	public String getReadablePath() {
		String readablePath = "";
		if (launchPath == null || launchPath.getPath() == null) {
			return readablePath;
		}

		for (PathItem pathItem : launchPath.getPath()) {
			if ("".equals(readablePath)) {
				readablePath = pathItem.getItem().getDisplayName() + "("
						+ pathItem.getEvent().getName() + ")";
			} else {
				readablePath = readablePath + " -> "
						+ pathItem.getItem().getDisplayName() + "("
						+ pathItem.getEvent().getName() + ")";
			}
		}
		return readablePath;
	}

	@Override
	public WebPage clone() {		
		return null;
	}

	@Override
	public UIObject updateLocatorParameterWithValue(String paramName, String value) {
		
		return this;
	}
}