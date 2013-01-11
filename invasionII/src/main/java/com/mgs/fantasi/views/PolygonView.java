package com.mgs.fantasi.views;

import com.mgs.fantasi.properties.polygon.PolygonPointsIterator;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.WireframeFactory;

public class PolygonView extends BaseView<PolygonView> {
	private PolygonView(PolygonPointsIterator polygonPointsIterator) {
		getUiProperties().setShape(polygonPointsIterator);
	}

	public static PolygonView polygon(PolygonPointsIterator polygonPointsIterator) {
		return new PolygonView(polygonPointsIterator);
	}

	@Override
	public Wireframe<View> buildLayout(WireframeFactory<View> wireframeFactory) {
		return wireframeFactory.createEmptyWireframe();
	}
}
