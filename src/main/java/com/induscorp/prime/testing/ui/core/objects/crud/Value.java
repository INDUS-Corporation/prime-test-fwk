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

import com.induscorp.prime.testing.ui.core.objects.validator.mechanisms.FillMechanism;

/**
 * 
 * @author Madhav Krishna
 *
 */
public class Value {
	private String value;
	private FillMechanism fillMechanism;

	public Value(String value, FillMechanism fillMechanism) {
		this.value = value;
		this.fillMechanism = fillMechanism;
	}

	public String getValue() {
		return value;
	}

	public FillMechanism getFillMechanism() {
		return fillMechanism;
	}
}
