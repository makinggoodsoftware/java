package com.mgs.fantasi.wireframe;

import com.mgs.fantasi.properties.measurements.Fraction;

public class WireframeChildElement<T, Z> {
	private final Tree<T, Z> content;
	private final CollocationInfo collocationInfo;

	public WireframeChildElement(Tree<T, Z> content, int zIndex, Fraction proportionOfParentWeight, Fraction proportionOfParentHeight, int coordinateX, int coordinateY) {
		this.content = content;
		this.collocationInfo = new CollocationInfo(zIndex, proportionOfParentWeight, proportionOfParentHeight, coordinateX, coordinateY);
	}

	public CollocationInfo getCollocationInfo() {
		return collocationInfo;
	}

	public Tree<T, Z> getChild() {
		return content;
	}

}
