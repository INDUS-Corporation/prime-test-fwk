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
package com.induscorp.prime.testing.ui.core.actions;

import java.util.ArrayList;
import java.util.List;

import com.induscorp.prime.testing.ui.core.config.webbrowser.WebBrowser;
import com.induscorp.prime.testing.ui.core.objects.UIObject;
import com.induscorp.prime.testing.ui.core.objects.webpage.WebPage;
import com.induscorp.prime.testing.ui.standard.imgobj.WebPageTitleSI;

/**
 * 
 * @author Madhav Krishna
 *
 */
public class InputActions {
	private String name;
	private List<InputAction> actions;
	private WebPage webPage;
	private List<UIObject> pageRecognitionItems;

	public InputActions(String name, WebPage webPage) {
		this.name = name;
		actions = new ArrayList<InputAction>(10);
		this.webPage = webPage;
		pageRecognitionItems = new ArrayList<UIObject>(2);
	}

	public String getName() {
		return name;
	}

	public InputActions append(InputAction action) {
		actions.add(action);
		return this;
	}

	public List<InputAction> getActions() {
		return actions;
	}

	public InputActionHandler getActionHandler(WebBrowser browser) {
		return new InputActionHandler(browser, this);
	}

	public WebPage getWebPage() {
		return webPage;
	}

	public void setWebPage(WebPage webPage) {
		this.webPage = webPage;
	}

	public InputActions appendPageRecognitionItem(UIObject item) {
		pageRecognitionItems.add(item);

		return this;
	}

	public List<UIObject> getPageRecognitionItems() {
		return pageRecognitionItems;
	}

	public String getReadablePath() {
		String readablePath = "";
		for (InputAction action : actions) {
			if ("".equals(readablePath)) {
				readablePath = action.getItem().getDisplayName() + "("
						+ action.getEvent().getName() + ")";
			} else {
				readablePath = readablePath + " -> "
						+ action.getItem().getDisplayName() + "("
						+ action.getEvent().getName() + ")";
			}
		}
		return readablePath;
	}

	/**
	 * This method returns the expected page title only. Other information will
	 * not be present. Title name will contain the whole path of the expected webpage.
	 * 
	 * @return
	 */
	public WebPage getExpectedWebPage() {
		return new WebPage(new WebPageTitleSI(this.webPage.getTitle()
				.getDisplayName() + ":> " + getReadablePath(), null), null,
				null);
	}
}
