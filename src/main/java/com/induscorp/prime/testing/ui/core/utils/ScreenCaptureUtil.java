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

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.testng.Assert;

/**
 * 
 * @author Madhav Krishna
 *
 */
public class ScreenCaptureUtil {
	private static int screenshotId = 0;
	
	public static Dimension getScreenSize() {
		return Toolkit.getDefaultToolkit().getScreenSize();
	}
	
	public static void capture(String dir, String testClassName,
			String testCaseName, Rectangle screenArea) {
		try {
			Rectangle screenRectangle = screenArea;
			if(screenArea == null) {
				Dimension screenSize = getScreenSize();
				screenRectangle = new Rectangle(screenSize);
			}
			
			Robot robot = new Robot();
			BufferedImage image = robot.createScreenCapture(screenRectangle);

			File dirObj = new File(dir);

			if (!dirObj.exists()) {
				dirObj.mkdirs();
//				Assert.assertTrue(dirObj.mkdirs(),
//						"Failed to create the directory '" + dir
//								+ "' for saving the screenshots.");
			}

			String imageFile = null;
			if(testClassName == null) {
				imageFile = dir + File.separator
						+ testCaseName + "-" + getNextScreenshotId() + ".png";
			} else {
				imageFile = dir + File.separator + testClassName + "-"
						+ testCaseName + "-" + getNextScreenshotId() + ".png";
			}

			ImageIO.write(image, "png", new File(imageFile));
		} catch (Exception ex) {
			Assert.fail("Failed to take screenshot.", ex);
		}
	}

	private synchronized static int getNextScreenshotId() {
		return ++screenshotId;
	}

}
