package com.mgs.fantasi.wireframe;

import com.mgs.fantasi.properties.measurements.Fraction;

public class Placeholder<T> {
	private final T content;
	private final Fraction proportionOfParentHeight;
	private final Fraction proportionOfParentWeight;
	private final int zIndex;
	private final int coodinateX;
	private final int coodinateY;

	public Placeholder(T content, int zIndex, Fraction proportionOfParentWeight, Fraction proportionOfParentHeight, int coordinateX, int coordinateY) {
		this.content = content;
		this.zIndex = zIndex;
		this.proportionOfParentWeight = proportionOfParentWeight;
		this.proportionOfParentHeight = proportionOfParentHeight;
		this.coodinateX = coordinateX;
		this.coodinateY = coordinateY;
	}

	public T getContent() {
		return content;
	}

	public Fraction getProportionOfParentHeight() {
		return proportionOfParentHeight;
	}

	public Fraction getProportionOfParentWeight() {
		return proportionOfParentWeight;
	}

	public int getzIndex() {
		return zIndex;
	}

	public int getCoodinateX() {
		return coodinateX;
	}

	public int getCoodinateY() {
		return coodinateY;
	}
}
