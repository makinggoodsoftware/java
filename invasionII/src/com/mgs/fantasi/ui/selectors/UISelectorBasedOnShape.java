package com.mgs.fantasi.ui.selectors;

import com.mgs.fantasi.ui.wireframe.Wireframe;
import com.mgs.fantasi.polygon.PolygonPointsIterator;

public class UISelectorBasedOnShape implements UISelector {
	private final Class<? extends PolygonPointsIterator> type;

	public UISelectorBasedOnShape(Class<? extends PolygonPointsIterator> type) {
		this.type = type;
	}

	@Override
	public boolean appliesTo(Wireframe wireframe) {
		return wireframe.getShape().getClass().equals(type);
	}
}
