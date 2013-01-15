package com.mgs.fantasi.views;

import com.mgs.fantasi.properties.polygon.PolygonPointsIterator;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.WireframeFactory;

public class PolygonWireframeBuilder extends BaseWireframeBuilder<PolygonWireframeBuilder> {
	private PolygonWireframeBuilder(PolygonPointsIterator polygonPointsIterator) {
		getUiProperties().setShape(polygonPointsIterator);
	}

	public static PolygonWireframeBuilder polygon(PolygonPointsIterator polygonPointsIterator) {
		return new PolygonWireframeBuilder(polygonPointsIterator);
	}

	@Override
	public Wireframe<Wireframe> build(WireframeFactory<Wireframe> wireframeFactory) {
		return wireframeFactory.createEmptyWireframe(getUiProperties(), getName(), this.getClass());
	}
}
