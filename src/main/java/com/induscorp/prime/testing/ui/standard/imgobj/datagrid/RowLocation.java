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
package com.induscorp.prime.testing.ui.standard.imgobj.datagrid;

/**
 * 
 * @author Madhav Krishna
 *
 */
public class RowLocation {
	protected Integer y1;
	protected Integer y2;
	
	public RowLocation(Integer y1, Integer y2) {
		this.y1 = y1;
		this.y2 = y2;
	}
	
	public Integer getY1() {
		return y1;
	}

	public Integer getY2() {
		return y2;
	}
	
	public int getRowHeight() {
		if(y1 != null && y2 != null) {
			return (y2 - y1);
		}
		return 0;
	}
}
