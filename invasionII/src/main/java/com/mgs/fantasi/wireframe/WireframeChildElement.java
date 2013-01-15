package com.mgs.fantasi.wireframe;

import com.mgs.fantasi.properties.measurements.Fraction;

public class WireframeChildElement {
	private final Wireframe content;
	private final CollocationInfo collocationInfo;

	public WireframeChildElement(Wireframe content, int zIndex, Fraction proportionOfParentWeight, Fraction proportionOfParentHeight, int coordinateX, int coordinateY) {
		this.content = content;
		this.collocationInfo = new CollocationInfo(zIndex, proportionOfParentWeight, proportionOfParentHeight, coordinateX, coordinateY);
	}

	public CollocationInfo getCollocationInfo() {
		return collocationInfo;
	}

	public Wireframe getChild() {
		return content;
	}

}
