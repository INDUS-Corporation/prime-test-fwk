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

/**
 * 
 * @author Madhav Krishna
 *
 */
public class Records {
	
	public List<Record> records;
	
	public Records() {
		records = new ArrayList<Record>();
	}
	
	public Records append(Record record) {
		records.add(record);
		return this;
	}
	
	public List<Record> getRecords() {
		return records;
	}

}
