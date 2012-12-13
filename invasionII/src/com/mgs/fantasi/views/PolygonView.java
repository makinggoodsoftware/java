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
	public boolean renderConstraintsAreSatisfied() {
		return true;
	}

	@Override
	public Structure<View> getContent() {
		return GridFactory.empty(View.class);
	}

	@Override
	public StructureFactory.StructureType getContentStructureType() {
		return null;
	}

	@Override
	public ContentStructureStrategy getContentStructureStrategy() {
		return new EmptyContentStructureStrategy();
	}

	@Override
	public PolygonView copy() {
		return new PolygonView(getShape());
	}
}
