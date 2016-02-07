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

import org.openqa.selenium.Keys;

import com.induscorp.prime.testing.ui.core.objects.NewTextLocation;

/**
 * 
 * @author Madhav Krishna
 *
 */
public class KeyboardEvent extends InputEvent<KeyboardEventName> {
	protected Keys key;
	protected String inputTextBeforeKeyAction;
	protected NewTextLocation textLocation;
	
	public KeyboardEvent(KeyboardEventName name, Keys key, String inputTextBeforeKeyAction, NewTextLocation textLocation) {
		this.type = InputEventType.keyBoard;
		this.name = name;
		this.key = key;
		this.inputTextBeforeKeyAction = inputTextBeforeKeyAction;
		this.textLocation = textLocation;
	}
	
	/**	
	 * @return the keyboard key.
	 */
	public Keys getKey() {
		return key;
	}
	
	/**
	 * @return the input text that is associated with the key.
	 */
	public String getInputText() {
		return inputTextBeforeKeyAction;
	}
	
	/**
	 * Location where to type the text.
	 * @return
	 */
	public NewTextLocation getTextLocation() {
		return textLocation;
	}
}
