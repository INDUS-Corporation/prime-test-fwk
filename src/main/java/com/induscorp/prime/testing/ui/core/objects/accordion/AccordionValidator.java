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
package com.induscorp.prime.testing.ui.core.objects.accordion;

import org.sikuli.script.Region;

import com.induscorp.prime.testing.ui.core.commons.Sections;
import com.induscorp.prime.testing.ui.core.config.webbrowser.WebBrowser;
import com.induscorp.prime.testing.ui.core.objects.UIObjectValidator;

/**
 * 
 * @author Madhav Krishna
 *
 */
public abstract class AccordionValidator extends UIObjectValidator {
	private Accordion accordion;

	public AccordionValidator(WebBrowser browser, Accordion uiObject, Region region) {
		super(browser, uiObject, region);
		this.accordion = uiObject;
	}

	@Override
	public Accordion getUIObject() {
		return accordion;
	}

	public abstract void validateDisabledSections(Sections sections, int numRetries);

	public abstract void validateOpenedSections(Sections sections, int numRetries);

	public abstract void validateClosedSections(Sections sections, int numRetries);

	public abstract void vaidateSectionsPresence(Sections sections, int numRetries);

	public abstract void openSection(String section, int numRetries);

	public abstract void closeSection(String section, int numRetries);

}
