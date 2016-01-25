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

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 
 * @author Madhav Krishna
 *
 * @param <K, V>
 */
public class ItemMap<K, V> {
	private LinkedHashMap<K, V> map;
	
	public ItemMap() {
		map = new LinkedHashMap<K, V>();
	}
	
	public ItemMap(LinkedHashMap<K, V> items) {
		map = new LinkedHashMap<K, V>();
		map.putAll(items);
	}
	
	public ItemMap<K, V> put(K key, V value) {
		map.put(key, value);
		return this;
	}
	
	public ItemMap<K, V> remove(K item) {
		map.remove(item);
		return this;
	}
	
	public ItemMap<K, V> removeAll(Map<K, V> items) {
		for(K key : items.keySet()) {
			remove(key);
		}		
		return this;
	}
	
	public int size() {
		return map.size();
	}
	
	public Map<K, V> getItems() {
		return map;
	}
	
	@Override
	public String toString() {
		return map.toString();
	}
}
