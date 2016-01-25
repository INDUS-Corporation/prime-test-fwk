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
package com.induscorp.prime.testing.ui.core.commons;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 
 * @author Madhav Krishna
 *
 * @param <T>
 */
public class ItemSet<T> {
	private Set<T> list;

	public ItemSet() {
		list = new LinkedHashSet<T>();
	}

	public ItemSet<T> add(T item) {
		list.add(item);
		return this;
	}

	public ItemSet<T> remove(T item) {
		list.remove(item);
		return this;
	}

	public int size() {
		return list.size();
	}

	public Set<T> getItems() {
		return list;
	}

	@Override
	public String toString() {
		return list.toString();
	}
}
