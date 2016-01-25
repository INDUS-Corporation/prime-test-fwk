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

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author Madhav Krishna
 *
 * @param <T>
 */
public class ItemList<T> {
	private List<T> list;
	
	public ItemList() {
		list = new LinkedList<T>();
	}
	
	public ItemList(List<T> items) {
		list = new ArrayList<T>();
		list.addAll(items);
	}
	
	public ItemList<T> add(T item) {
		list.add(item);
		return this;
	}
	
	public ItemList<T> remove(T item) {
		list.remove(item);
		return this;
	}
	
	public ItemList<T> removeAll(Collection<T> items) {
		list.removeAll(items);
		return this;
	}
	
	public int size() {
		return list.size();
	}
	
	public List<T> getItems() {
		return list;
	}
	
	@Override
	public String toString() {
		return list.toString();
	}
}
