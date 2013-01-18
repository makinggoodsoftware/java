package com.mgs.fantasi.selectors;

import com.mgs.fantasi.properties.polygon.PolygonPointsIterator;
import com.mgs.fantasi.wireframe.WireframeTree;

public class UISelectorBasedOnShape implements UISelector {
	private final Class<? extends PolygonPointsIterator> type;

	public UISelectorBasedOnShape(Class<? extends PolygonPointsIterator> type) {
		this.type = type;
	}

	@Override
	public boolean appliesTo(WireframeTree renderable) {
		if (renderable == null || renderable.getUiProperties() == null) return false;
		return renderable.getUiProperties().getShape().getClass().equals(type);
	}
}
