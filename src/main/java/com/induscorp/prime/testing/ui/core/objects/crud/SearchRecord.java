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
package com.induscorp.prime.testing.ui.core.objects.crud;

import java.util.ArrayList;
import java.util.List;

import com.induscorp.prime.testing.ui.core.actions.InputActions;
import com.induscorp.prime.testing.ui.core.config.webbrowser.WebBrowser;
import com.induscorp.prime.testing.ui.core.objects.webpage.WebPage;

/**
 * 
 * @author Madhav Krishna
 *
 */
public class SearchRecord {
	private WebPage webPage;
	private List<SearchRecord> searchRecordList;
	private InputActions preInputActions;
	private Record inputRecord;
	private InputActions postInputActions;
	private Record searchRecord;

	public SearchRecord(WebPage webPage) {
		searchRecordList = new ArrayList<SearchRecord>(1);
		this.webPage = webPage;
	}

	/**
	 * Used to set the search information (Generally on the popup dialog).
	 * 
	 * @param preInputActions
	 * @param inputRecord
	 * @param postInputActions
	 * @param selectableInfo
	 */
	public SearchRecord setSearchInfo(InputActions preInputActions, Record inputRecord,
			InputActions postInputActions, Record searchRecord) {
		this.preInputActions = preInputActions;
		this.inputRecord = inputRecord;
		this.postInputActions = postInputActions;
		this.searchRecord = searchRecord;
		return this;
	}

	public InputActions getPreInputActions() {
		return preInputActions;
	}

	public Record getInputRecord() {
		return inputRecord;
	}

	public InputActions getPostInputActions() {
		return postInputActions;
	}

	public Record getSearchRecord() {
		return searchRecord;
	}

	public List<SearchRecord> getSearchRecordList() {
		return searchRecordList;
	}

	/**
	 * Used to add another search record information. This search is performed
	 * before the previous one.
	 * 
	 * @param searchRecord
	 * @return
	 */
	public SearchRecord addSearchRecord(SearchRecord searchRecord) {
		searchRecordList.add(searchRecord);
		return this;
	}

	public SearchRecordHandler getSearchRecordHandler(WebBrowser browser) {
		return new SearchRecordHandler(browser, webPage, this);
	}

}
