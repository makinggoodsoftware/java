package com.mgs.fantasi.views;

import com.mgs.fantasi.properties.polygon.PolygonPointsIterator;
import com.mgs.fantasi.rendering.wireframe.EmptyWireframe;
import com.mgs.fantasi.rendering.wireframe.Wireframe;

public class PolygonView extends BaseView<PolygonView> {
	private PolygonView(PolygonPointsIterator polygonPointsIterator) {
		getUiProperties().setShape(polygonPointsIterator);
	}

	public static PolygonView polygon(PolygonPointsIterator polygonPointsIterator) {
		return new PolygonView(polygonPointsIterator);
	}

	@Override
	public Wireframe<View> buildChildViews() {
		return new EmptyWireframe<View>();
	}

	@Override
	public PolygonView copySpecifics() {
		return new PolygonView(getUiProperties().getShape());
	}
}
