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
package com.induscorp.prime.testing.ui.core.objects.table;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Madhav Krishna
 *
 */
public class HeaderDefinition {	
	private List<HeaderColumnInfo> headerColumnDetails;
	
	public HeaderDefinition() {
		headerColumnDetails = new ArrayList<HeaderColumnInfo>();
	}
	
	public HeaderDefinition append(HeaderColumnInfo headerColumnInfo) {
		headerColumnDetails.add(headerColumnInfo);
		return this;
	}

	public List<HeaderColumnInfo> getHeaderColumnsDetail() {
		return headerColumnDetails;
	}	
	
	public HeaderColumnInfo getHeaderColumnInfo(int visibleIndex) {
		for(HeaderColumnInfo colInfo : headerColumnDetails) {
			if(colInfo.getHtmlColumnIndex() == visibleIndex) {
				return colInfo;
			}
		}
		return null;
	}
	
	public int getHeaderColumnInfoIndex(int visibleIndex) {
		HeaderColumnInfo colInfo;
		for(int i = 0; i < headerColumnDetails.size(); i++) {
			colInfo = headerColumnDetails.get(i);
			if(colInfo.getHtmlColumnIndex() == visibleIndex) {
				return i;
			}
		}
		return -1;
	}
}
