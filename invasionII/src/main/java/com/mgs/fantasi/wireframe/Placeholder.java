package com.mgs.fantasi.wireframe;

import com.mgs.fantasi.properties.measurements.Fraction;

public class Placeholder<T> {
	private final T content;
	private final Fraction proportionOfParentHeight;
	private final Fraction proportionOfParentWeight;
	private final int zIndex;

	public Placeholder(T content, int zIndex, Fraction proportionOfParentWeight, Fraction proportionOfParentHeight) {
		this.content = content;
		this.zIndex = zIndex;
		this.proportionOfParentWeight = proportionOfParentWeight;
		this.proportionOfParentHeight = proportionOfParentHeight;
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
}
