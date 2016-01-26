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
package com.induscorp.prime.testing.ui.core.objects;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;

import org.sikuli.script.Match;
import org.sikuli.script.Region;
import org.testng.Assert;

import com.induscorp.prime.testing.ui.core.commons.UIObjectType;
import com.induscorp.prime.testing.ui.core.config.webbrowser.WebBrowser;

/**
 * 
 * @author Madhav Krishna
 * TODO: Implementation is not yet completed.
 */
@SuppressWarnings("unused")
public class ObjectLocation {
	private ObjectLocationType objLocType;
	private int x, y, width, height;	
	private UIObject refObject;
	private ReferenceObjectPosition refObjectPosition;
	private int skips;
		
	public ObjectLocation(int x, int y, int width, int height) {
		objLocType = ObjectLocationType.WITHIN_LIMIT;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public ObjectLocation(Rectangle area) {
		objLocType = ObjectLocationType.WITHIN_LIMIT;
		this.x = area.x;
		this.y = area.y;
		this.width = area.width;
		this.height = area.height;
	}

	public ObjectLocation(UIObject refObject, ReferenceObjectPosition refObjectPosition, int skips) {
		objLocType = ObjectLocationType.RELATIVE_TO_REF_OBJ;
		this.refObject = refObject;
		this.refObjectPosition = refObjectPosition;
		this.skips = skips;
	}
	
	public Rectangle getRectangleOfImageObject(WebBrowser browser, 
			String topLeftCornerImageOfImageObject,			
			String bottomRightCornerImageOfImageObject) {
		switch(objLocType) {
		case WITHIN_LIMIT:
			Region region = new Region(x, y, width, height);
			region.setAutoWaitTimeout(1);
			ImageObject obj = new ImageObject(UIObjectType.topLeftCornerImage, 
					"Top Left Corner Image", topLeftCornerImageOfImageObject);
			List<Match> m1 = obj.getValidator(browser, region).findElements(3);			
			obj = new ImageObject(UIObjectType.bottomRightCornerImage, 
					"Bottom Right Corner Image", bottomRightCornerImageOfImageObject);
			List<Match> m2 = obj.getValidator(browser, region).findElements(3);
			
			Rectangle objDim = new Rectangle();
			objDim.x = m1.get(0).x;
			objDim.y = m1.get(0).y;
			objDim.width = m2.get(0).x - objDim.x;
			if(m2.size() == 1) {
				objDim.height = m2.get(0).y - objDim.y;
			} else {
				objDim.height = m2.get(1).y - objDim.y;
			}
			
			return objDim;
		case RELATIVE_TO_REF_OBJ:
			break;
		}
		return null;
	}
	
	@SuppressWarnings("incomplete-switch")
	private Point calculateLeftCornerImagePoint(WebBrowser browser, String topLeftCornerImageOfImageObject) {
		Point p = null;
		
		Region region = new Region(x, y, width, height);
		region.setAutoWaitTimeout(1);
		ImageObject obj = new ImageObject(UIObjectType.topLeftCornerImage, 
				"Top Left Corner Image", topLeftCornerImageOfImageObject);
		Match m1 = obj.getValidator(browser, region).findElement(3);
		
		switch(refObjectPosition) {
		case RIGHT_MIDDLE:
			
			break;
		}
		
		return p;
	}
	
	/**
	 * This is applicable only for WITHIN LIMIT object.
	 * @return
	 */
	public Region getRegion() {
		if(ObjectLocationType.WITHIN_LIMIT == objLocType) {
			return new Region(x, y, width, height);
		}
		
		Assert.fail("getRegion() api is applicable only for absolute location.");
		return null;
	}
	
	public Region getRegionOfImageObject(WebBrowser browser, 
			String topLeftCornerImageOfImageObject,			
			String bottomRightCornerImageOfImageObject) {
		if(ObjectLocationType.WITHIN_LIMIT == objLocType) {
			return new Region(x, y, width, height);
		} else {
			return new Region(getRectangleOfImageObject(browser, 
					topLeftCornerImageOfImageObject,					
					bottomRightCornerImageOfImageObject));
		}
	}
}
