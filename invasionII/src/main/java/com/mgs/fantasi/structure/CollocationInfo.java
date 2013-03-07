package com.mgs.fantasi.structure;

import com.mgs.fantasi.properties.data.measurements.Fraction;

public class CollocationInfo {
	private final Fraction proportionOfParentHeight;
	private final Fraction proportionOfParentWeight;
	private final int zIndex;
	private final int coordinateX;
	private final int coordinateY;

	public CollocationInfo(int zIndex, Fraction proportionOfParentWeight, Fraction proportionOfParentHeight, int coordinateX, int coordinateY) {
		this.zIndex = zIndex;
		this.proportionOfParentWeight = proportionOfParentWeight;
		this.proportionOfParentHeight = proportionOfParentHeight;
		this.coordinateX = coordinateX;
		this.coordinateY = coordinateY;
	}

	public int getCoordinateX() {
		return coordinateX;
	}

	public int getCoordinateY() {
		return coordinateY;
	}

	public Fraction getProportionOfParentHeight() {
		return proportionOfParentHeight;
	}

	public Fraction getProportionOfParentWeight() {
		return proportionOfParentWeight;
	}

	public int getZIndex() {
		return zIndex;
	}
}