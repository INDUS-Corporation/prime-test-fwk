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
package com.induscorp.prime.testing.ui.core.objects.scrollbar;

import org.sikuli.script.Match;
import org.sikuli.script.Region;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

/**
 * 
 * @author Madhav Krishna
 *
 */
public class HorizontalScrollbar {
	private static Logger logger = LoggerFactory.getLogger(HorizontalScrollbar.class);
	protected String thumbGripLeftPartImage;
	protected String thumbGripLeftPartImageFocused;
	protected String thumbGripRightPartImage;
	protected String thumbGripRightPartImageFocused;
	protected String leftScrollImageEnabled;
	protected String leftScrollImageEnabledFocused;
	protected String rightScrollImageEnabled;
	protected String rightScrollImageEnabledFocused;
	protected String leftScrollImageDisabled;
	protected String rightScrollImageDisabled;

	public HorizontalScrollbar(String thumbGripLeftPartImage, String thumbGripLeftPartImageFocused,
			String thumbGripRightPartImage, String thumbGripRightPartImageFocused, String leftScrollImageEnabled,
			String leftScrollImageEnabledFocused, String rightScrollImageEnabled, String rightScrollImageEnabledFocused,
			String leftScrollImageDisabled, String rightScrollImageDisabled) {
		this.thumbGripLeftPartImage = thumbGripLeftPartImage;
		this.thumbGripLeftPartImageFocused = thumbGripLeftPartImageFocused;
		this.thumbGripRightPartImage = thumbGripRightPartImage;
		this.thumbGripRightPartImageFocused = thumbGripRightPartImageFocused;
		this.leftScrollImageEnabled = leftScrollImageEnabled;
		this.leftScrollImageEnabledFocused = leftScrollImageEnabledFocused;
		this.rightScrollImageEnabled = rightScrollImageEnabled;
		this.rightScrollImageEnabledFocused = rightScrollImageEnabledFocused;
		this.leftScrollImageDisabled = leftScrollImageDisabled;
		this.rightScrollImageDisabled = rightScrollImageDisabled;
	}

	public boolean isThumbGripLeftPartImageVisible(Region region) {
		try {
			Match thumbGripFound = region.find(thumbGripLeftPartImage);
			if (thumbGripFound != null) {
				return true;
			}
		} catch (Throwable th) {
		}

		try {
			Match thumbGripFound = region.find(thumbGripLeftPartImageFocused);
			if (thumbGripFound != null) {
				return true;
			}
		} catch (Throwable th1) {

		}
		return false;
	}

	public boolean isThumbGripRightPartImageVisible(Region region) {
		try {
			Match thumbGripFound = region.find(thumbGripRightPartImage);
			if (thumbGripFound != null) {
				return true;
			}
		} catch (Throwable th) {
		}

		try {
			Match thumbGripFound = region.find(thumbGripRightPartImageFocused);
			if (thumbGripFound != null) {
				return true;
			}
		} catch (Throwable th1) {

		}
		return false;
	}

	public boolean isLeftScrollImageVisible(Region region) {
		try {
			Match rightScrollFound = region.find(leftScrollImageEnabled);

			if (rightScrollFound != null) {
				return true;
			}
		} catch (Throwable th) {
		}

		try {
			Match rightScrollFound = region.find(leftScrollImageEnabledFocused);

			if (rightScrollFound != null) {
				return true;
			}
		} catch (Throwable th1) {
		}
		return false;
	}

	public boolean isRightScrollImageVisible(Region region) {
		try {
			Match rightScrollFound = region.find(rightScrollImageEnabled);

			if (rightScrollFound != null) {
				return true;
			}
		} catch (Throwable th) {
		}

		try {
			Match rightScrollFound = region.find(rightScrollImageEnabledFocused);

			if (rightScrollFound != null) {
				return true;
			}
		} catch (Throwable th1) {
		}
		return false;
	}

	public Match findThumbGripLeftPartImage(Region region) {
		try {
			Match match = region.find(thumbGripLeftPartImage);

			if (match != null) {
				return match;
			}
		} catch (Throwable th) {
		}

		try {
			Match match = region.find(thumbGripLeftPartImageFocused);

			if (match != null) {
				return match;
			}
		} catch (Throwable th1) {
		}
		return null;
	}

	public Match findThumbGripRightPartImage(Region region) {
		try {
			Match match = region.find(thumbGripRightPartImage);

			if (match != null) {
				return match;
			}
		} catch (Throwable th) {
		}

		try {
			Match match = region.find(thumbGripRightPartImageFocused);

			if (match != null) {
				return match;
			}
		} catch (Throwable th1) {
		}
		return null;
	}

	public Match findLeftScrollImage(Region region) {
		try {
			Match match = region.find(leftScrollImageEnabled);

			if (match != null) {
				return match;
			}
		} catch (Throwable th) {
		}

		try {
			Match match = region.find(leftScrollImageEnabledFocused);

			if (match != null) {
				return match;
			}
		} catch (Throwable th1) {
		}
		return null;
	}

	public Match findRightScrollImage(Region region) {
		try {
			Match match = region.find(rightScrollImageEnabled);

			if (match != null) {
				return match;
			}
		} catch (Throwable th) {
		}

		try {
			Match match = region.find(rightScrollImageEnabledFocused);

			if (match != null) {
				return match;
			}
		} catch (Throwable th1) {
		}
		return null;
	}

	public boolean isFullScrollbarVisible(Region region) {
		try {
			if (isLeftScrollImageVisible(region) && isRightScrollImageVisible(region)) {
				return true;
			}
		} catch (Throwable th) {
		}
		return false;
	}

	public void validateFullScrollbarVisible(Region region) {
		Assert.assertTrue(isLeftScrollImageVisible(region) && isRightScrollImageVisible(region),
				"Full horizontal scrollbar is not visible.");
	}

	public boolean isScrollbarDisabled(Region region) {
		Match found;
		try {
			found = region.find(leftScrollImageDisabled);
			if (found != null) {
				return true;
			}
		} catch (Throwable th) {
		}

		try {
			found = region.find(rightScrollImageDisabled);
			if (found != null) {
				return true;
			}
		} catch (Throwable th1) {
		}
		return false;
	}

	public void validateScrollbarDisabled(Region region) {
		Assert.assertTrue(isScrollbarDisabled(region), "Horizontal scrollbar is not disabled.");
	}

	private boolean isImagesCollidedHorizontally(Match m1, Match m2) {
		if (m1 == null || m2 == null) {
			return false;
		}

		double p1 = m1.getRect().getX() + m1.getRect().getWidth();
		double p2 = m2.getRect().getX();
		if (p2 - p1 <= 5) {
			return true;
		}

		p1 = m2.getRect().getX() + m2.getRect().getWidth();
		p2 = m1.getRect().getX();
		if (p2 - p1 <= 5) {
			return true;
		}

		return false;
	}

	/**
	 * Clicks left scroll image n times and returns true if leftThumbGrip is
	 * scrolled to extreme left else returns false.
	 * 
	 * @param region
	 * @param times
	 * @return
	 */
	public boolean clickLeftScrollImage(Region region, int times) {
		try {
			Match found = findLeftScrollImage(region);
			if (found != null) {
				for (int i = 0; i < times; i++) {
					found.click();
				}

				Match thumbGripLeftPartImgFound = findThumbGripLeftPartImage(region);
				return isImagesCollidedHorizontally(found, thumbGripLeftPartImgFound);
			} else {
				logger.error("Error during clickLeftScrollImage.");
				Assert.fail("Error during clickLeftScrollImage.");
			}
		} catch (Throwable th) {
			logger.error("Error during clickLeftScrollImage.", th);
			Assert.fail("Error during clickLeftScrollImage.", th);
		}
		return false;
	}

	public boolean clickRightScrollImage(Region region, int times) {
		try {
			Match found = findRightScrollImage(region);
			if (found != null) {
				for (int i = 0; i < times; i++) {
					found.click();
				}
				Match thumbGripRightPartImgFound = findThumbGripRightPartImage(region);
				return isImagesCollidedHorizontally(thumbGripRightPartImgFound, found);
			}
		} catch (Throwable th) {
			logger.error("Error during clickRightScrollImage.", th);
			Assert.fail("Error during clickRightScrollImage.", th);
		}
		return false;
	}

	public void scrollThumbGripToExtremeLeft(Region region) {
		try {
			Match thumbGripLeftPartImageFound = findThumbGripLeftPartImage(region);
			Match leftScrollImageEnabledFound = findLeftScrollImage(region);
			if (thumbGripLeftPartImageFound != null && leftScrollImageEnabledFound != null) {
				thumbGripLeftPartImageFound.drag(leftScrollImageEnabledFound);
				//thumbGripLeftPartImageFound.dropAt(leftScrollImageEnabledFound);
			} else if (leftScrollImageEnabledFound != null) {
				do {
					leftScrollImageEnabledFound.click();
					thumbGripLeftPartImageFound = findThumbGripLeftPartImage(region);
					if (thumbGripLeftPartImageFound != null) {
						thumbGripLeftPartImageFound.drag(leftScrollImageEnabledFound);
						//thumbGripLeftPartImageFound.dropAt(leftScrollImageEnabledFound);
						break;
					}
				} while (true);
			}
		} catch (Throwable th) {
			logger.error("Error during scrollThumbGripToExtremeLeft.", th);
			Assert.fail("Error during scrollThumbGripToExtremeLeft.", th);
		}
	}

	public void scrollThumbGripToExtremeRight(Region region) {
		try {
			Match thumbGripRightPartImageFound = findThumbGripRightPartImage(region);
			Match rightScrollImageEnabledFound = findRightScrollImage(region);
			if (thumbGripRightPartImageFound != null && rightScrollImageEnabledFound != null) {
				thumbGripRightPartImageFound.drag(rightScrollImageEnabledFound);
				//thumbGripRightPartImageFound.dropAt(rightScrollImageEnabledFound);
			} else if (rightScrollImageEnabledFound != null) {
				do {
					rightScrollImageEnabledFound.click();
					thumbGripRightPartImageFound = findThumbGripRightPartImage(region);
					if (thumbGripRightPartImageFound != null) {
						thumbGripRightPartImageFound.drag(thumbGripRightPartImageFound);
						//thumbGripRightPartImageFound.dropAt(rightScrollImageEnabledFound);
						break;
					}
				} while (true);
			}
		} catch (Throwable th) {
			logger.error("Error during scrollThumbGripToExtremeRight.", th);
			Assert.fail("Error during scrollThumbGripToExtremeRight.", th);
		}
	}
}
