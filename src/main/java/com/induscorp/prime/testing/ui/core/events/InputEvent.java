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
package com.induscorp.prime.testing.ui.core.events;

/**
 * 
 * @author Madhav Krishna
 *
 * @param <EVENTNAME>
 */
public abstract class InputEvent<EVENTNAME> {

	protected InputEventType type;
	protected EVENTNAME name;

	protected InputEvent() {

	}

	/**
	 * @return the event type.
	 */
	public InputEventType getType() {
		return type;
	}

	/**
	 * @return the event name.
	 */
	public EVENTNAME getName() {
		return name;
	}
}
