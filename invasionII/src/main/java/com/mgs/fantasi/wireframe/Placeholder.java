package com.mgs.fantasi.wireframe;

import com.mgs.fantasi.properties.measurements.Fraction;

public class Placeholder {
	private final Wireframe content;
	private final CollocationInfo collocationInfo;

	public Placeholder(Wireframe content, int zIndex, Fraction proportionOfParentWeight, Fraction proportionOfParentHeight, int coordinateX, int coordinateY) {
		this.content = content;
		this.collocationInfo = new CollocationInfo(zIndex, proportionOfParentWeight, proportionOfParentHeight, coordinateX, coordinateY);
	}

	public Wireframe getContent() {
		return content;
	}

	public Fraction getProportionOfParentHeight() {
		return collocationInfo.getProportionOfParentHeight();
	}

	public Fraction getProportionOfParentWeight() {
		return collocationInfo.getProportionOfParentWeight();
	}

	public int getzIndex() {
		return collocationInfo.getzIndex();
	}

	public int getCoodinateX() {
		return collocationInfo.getCoordinateX();
	}

	public int getCoodinateY() {
		return collocationInfo.getCoordinateY();
	}
}
