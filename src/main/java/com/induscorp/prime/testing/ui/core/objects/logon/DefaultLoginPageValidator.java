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
package com.induscorp.prime.testing.ui.core.objects.logon;

/**
 * 
 * @author Madhav Krishna
 *
 */
public class DefaultLoginPageValidator extends LoginPageValidator {
	public DefaultLoginPageValidator() {
		super(null, null);
	}

	@Override
	protected void tryLogin(String activeUserProfileName) {
		// Doing nothing
	}

	@Override
	protected void validateInfo(String activeUserProfileName) {
		// Doing nothing
	}

	@Override
	public boolean checkLoginPageVisible(String activeUserProfileName) {
		return true;
	}
}
