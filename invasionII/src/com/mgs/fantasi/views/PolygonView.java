package com.mgs.fantasi.views;

import com.mgs.fantasi.polygon.PolygonPointsIterator;
import com.mgs.fantasi.ui.wireframe.*;

public class PolygonView extends BaseView<PolygonView> {

	private PolygonView(PolygonPointsIterator polygonPointsIterator) {
		withShape(polygonPointsIterator);
	}

	public static PolygonView polygon(PolygonPointsIterator polygonPointsIterator) {
		return new PolygonView(polygonPointsIterator);
	}

	@Override
	public Wireframe<View> toWireframe() {
		return new EmptyWireframe<View>();
	}

	@Override
	public PolygonView copy() {
		return new PolygonView(getShape());
	}
}
