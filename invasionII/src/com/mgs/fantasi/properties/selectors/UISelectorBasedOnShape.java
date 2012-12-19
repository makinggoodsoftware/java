package com.mgs.fantasi.properties.selectors;

import com.mgs.fantasi.properties.polygon.PolygonPointsIterator;
import com.mgs.fantasi.rendering.Renderable;

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
