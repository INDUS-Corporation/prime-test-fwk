package com.induscorp.prime.testing.ui.core.objects;

public class PullDownMenuInfo {
	protected int width;
	protected int height;
	protected PullDownMenuLocation location;
	
	/**
	 * 
	 * @param width in pixel. value < 1 indicate calculate automatically.
	 * @param height in pixel. value < 1 indicate calculate automatically.
	 * @param location
	 */
	public PullDownMenuInfo(int width, int height, PullDownMenuLocation location) {
		this.width = width;
		this.height = height;
		this.location = location;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public PullDownMenuLocation getLocation() {
		return location;
	}
}
