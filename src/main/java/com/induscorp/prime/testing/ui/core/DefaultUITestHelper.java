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
package com.induscorp.prime.testing.ui.core;

import cucumber.api.Scenario;

/**
 * 
 * @author Madhav Krishna
 *
 */
public class DefaultUITestHelper extends AbstractUITestHelper {
	public DefaultUITestHelper() {
		super();
	}
	
	public void beforeScenario(Scenario scenario) {
		logger.info("Feature Scenario Started. TestClass: " + scenario.getName());
		super.scenarioSetup();
		logger.info("Feature Scenario Done. TestClass: " + scenario);

	}

	public void afterScenario(Scenario scenario) {
		logger.info("Feature Scenario TearDown Started. TestClass: " + this.getClass());
		super.scenarioTearDown();
		logger.info("Feature Scenario TearDown Done. TestClass: " + this.getClass());
	}
	
}
