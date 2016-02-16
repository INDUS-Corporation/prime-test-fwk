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
package com.induscorp.prime.testing.ui.core.utils;

import org.testng.Assert;

import com.induscorp.prime.testing.ui.core.objects.validator.mechanisms.TextValidationMechanism;

/**
 * 
 * @author Madhav Krishna
 *
 */
public class DataMatchUtil {

	public static boolean matchTextValue(String actualValue, String expectedValue,
			TextValidationMechanism validationMechanism) {
		boolean matched = false;

		if (actualValue == null) {
			return false;
		}

		switch (validationMechanism) {
		case startsWithExpectedValue:
			if (actualValue.startsWith(expectedValue)) {
				matched = true;
			}
			break;
		case containsExpectedValue:
			if (actualValue.contains(expectedValue)) {
				matched = true;
			}
			break;
		case endsWithExpectedValue:
			if (actualValue.contains(expectedValue)) {
				matched = true;
			}
			break;
		case exactMatchWithExpectedValue:
			if (actualValue.equals(expectedValue)) {
				matched = true;
			}
			break;
		case matchWithRegularExpression:
			if (actualValue.matches(expectedValue)) {
				matched = true;
			}
			break;
		case exactMatchWithExpectedValueWithRemovedWhiteSpace:
			if (actualValue.replaceAll(" ", "").equals(expectedValue.replaceAll(" ", ""))) {
				matched = true;
			}
			break;
		}

		return matched;
	}

	public static void validateTextValue(String actualValue, String expectedValue,
			TextValidationMechanism validationMechanism) {
		switch (validationMechanism) {
		case startsWithExpectedValue:
			if (!matchTextValue(actualValue, expectedValue, validationMechanism)) {
				Assert.fail("Actual value '" + actualValue + "' does not starts with expected value '" + expectedValue
						+ "'.");
			}
			break;
		case containsExpectedValue:
			if (!matchTextValue(actualValue, expectedValue, validationMechanism)) {
				Assert.fail(
						"Actual value '" + actualValue + "' does not contain expected value '" + expectedValue + "'.");
			}
			break;
		case endsWithExpectedValue:
			if (!matchTextValue(actualValue, expectedValue, validationMechanism)) {
				Assert.fail("Actual value '" + actualValue + "' does not ends with expected value '" + expectedValue
						+ "'.");
			}
			break;
		case exactMatchWithExpectedValue:
			if (!matchTextValue(actualValue, expectedValue, validationMechanism)) {
				Assert.fail(
						"Actual value '" + actualValue + "' does not equal to expected value '" + expectedValue + "'.");
			}
			break;
		case matchWithRegularExpression:
			if (!matchTextValue(actualValue, expectedValue, validationMechanism)) {
				Assert.fail("Actual value '" + actualValue + "' does not equal to expected regular expression value '"
						+ expectedValue + "'.");
			}
			break;
		case exactMatchWithExpectedValueWithRemovedWhiteSpace:
			if (!matchTextValue(actualValue, expectedValue, validationMechanism)) {
				Assert.fail(
						"Actual value '" + actualValue + "' does not equal to expected value '" + expectedValue + "'.");
			}
			break;
		}
	}
}
