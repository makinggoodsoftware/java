package com.mgs.fantasi.wireframe;

import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.properties.data.polygon.PolygonPointsIterator;

public class Wireframe {
	private final UIProperties uiProperties;
	private final PolygonPointsIterator shape;

	public Wireframe(UIProperties uiProperties, PolygonPointsIterator shape) {
		this.uiProperties = uiProperties;
		this.shape = shape;
	}

	public UIProperties getUiProperties() {
		return uiProperties;
	}

	public PolygonPointsIterator getShape() {
		return shape;
	}
}