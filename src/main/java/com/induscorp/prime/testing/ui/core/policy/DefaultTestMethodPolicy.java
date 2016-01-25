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
package com.induscorp.prime.testing.ui.core.policy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.induscorp.prime.testing.ui.core.AbstractUITestHelper;

/**
 * 
 * @author Madhav Krishna
 *
 */
public class DefaultTestMethodPolicy implements ITestListener {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void onTestStart(ITestResult result) {
		AbstractUITestHelper baseTest = (AbstractUITestHelper) result.getInstance();
				
		logger.info("TESTCASE(Started): '"
				+ baseTest.getClass().getSimpleName() + "-"
				+ result.getMethod().getMethodName()
				+ "'. Wait for completion...");
		
		baseTest.captureScreenshot(result.getMethod().getMethodName()
				+ "-Initial");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		AbstractUITestHelper baseTest = (AbstractUITestHelper) result.getInstance();
		baseTest.captureScreenshot(result.getMethod().getMethodName()
				+ "-Success");
		logger.info("TESTCASE(Finished-Successful): '"
				+ baseTest.getClass().getSimpleName() + "-"
				+ result.getMethod().getMethodName() + "'.");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		AbstractUITestHelper baseTest = (AbstractUITestHelper) result.getInstance();
		baseTest.captureScreenshot(result.getMethod().getMethodName()
				+ "-Failed");
		logger.info("TESTCASE(Finished-Failed): '"
				+ baseTest.getClass().getSimpleName() + "-"
				+ result.getMethod().getMethodName() + "'.",
				result.getThrowable());

		/* if(!baseTest.getActiveUserProfileName().equals(baseTest.getInitUserProfileName())) {
			baseTest.setActiveUserProfileName(baseTest.getInitUserProfileName());
		} else {
			baseTest.checkLogoutAndLoginAgain(baseTest.getActiveUserProfileName());
		} */
		
		baseTest.getInitWebBrowser().refresh();
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		AbstractUITestHelper baseTest = (AbstractUITestHelper) result.getInstance();
		baseTest.captureScreenshot(result.getMethod().getMethodName()
				+ "-Skipped");
		logger.info("TESTCASE(Finished-Skipped): '"
				+ baseTest.getClass().getSimpleName() + "-"
				+ result.getMethod().getMethodName() + "'.",
				result.getThrowable());

		baseTest.getInitWebBrowser().refresh();
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		AbstractUITestHelper baseTest = (AbstractUITestHelper) result.getInstance();
		baseTest.captureScreenshot(result.getMethod().getMethodName()
				+ "-Failed");
		logger.info("TESTCASE(Finished-Failed): '"
				+ baseTest.getClass().getSimpleName() + "-"
				+ result.getMethod().getMethodName() + "'.",
				result.getThrowable());

		baseTest.getInitWebBrowser().refresh();
	}

	@Override
	public void onStart(ITestContext context) {
		// DO NOT DO ANYTHING
	}

	@Override
	public void onFinish(ITestContext context) {
		// DO NOT DO ANYTHING
	}


}
