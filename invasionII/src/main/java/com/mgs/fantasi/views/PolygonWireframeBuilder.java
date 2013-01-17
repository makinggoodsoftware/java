package com.mgs.fantasi.views;

import com.mgs.fantasi.properties.polygon.PolygonPointsIterator;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.WireframeContentFactory;

public class PolygonWireframeBuilder extends BaseWireframeBuilder<PolygonWireframeBuilder> {
	private PolygonWireframeBuilder(PolygonPointsIterator polygonPointsIterator) {
		getUiProperties().setShape(polygonPointsIterator);
	}

	public static PolygonWireframeBuilder polygon(PolygonPointsIterator polygonPointsIterator) {
		return new PolygonWireframeBuilder(polygonPointsIterator);
	}

	@Override
	public Wireframe build(WireframeContentFactory wireframeContentFactory) {
		return new Wireframe(wireframeContentFactory.empty(), getUiProperties(), getName(), this.getClass());
	}
}
