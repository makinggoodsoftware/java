package com.mgs.fantasi.views;

import com.mgs.fantasi.polygon.PolygonPointsIterator;
import com.mgs.fantasi.ui.wireframe.GridFactory;
import com.mgs.fantasi.ui.wireframe.Structure;
import com.mgs.fantasi.ui.wireframe.Wireframe;

public class PolygonView extends BaseView<PolygonView> {

	private PolygonView(PolygonPointsIterator polygonPointsIterator) {
		withShape(polygonPointsIterator);
	}

	public static PolygonView polygon(PolygonPointsIterator polygonPointsIterator) {
		return new PolygonView(polygonPointsIterator);
	}

	@Override
	protected boolean constraintsAreSatisfied() {
		return true;
	}

	@Override
	protected Structure buildLayoutAndChilds() {
		return GridFactory.empty(Wireframe.class);
	}

	@Override
	public PolygonView copy() {
		return new PolygonView(getShape());
	}
}
