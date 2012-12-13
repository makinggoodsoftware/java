package com.mgs.fantasi.ui.selectors;

import com.mgs.fantasi.ui.wireframe.Renderable;
import com.mgs.fantasi.polygon.PolygonPointsIterator;

public class UISelectorBasedOnShape implements UISelector {
	private final Class<? extends PolygonPointsIterator> type;

	public UISelectorBasedOnShape(Class<? extends PolygonPointsIterator> type) {
		this.type = type;
	}

	@Override
	public boolean appliesTo(Renderable renderable) {
		return renderable.getShape().getClass().equals(type);
	}
}
