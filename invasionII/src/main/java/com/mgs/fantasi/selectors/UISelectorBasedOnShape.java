package com.mgs.fantasi.selectors;

import com.mgs.fantasi.properties.data.polygon.PolygonPointsIterator;
import com.mgs.fantasi.wireframe.tree.WireframeNode;

public class UISelectorBasedOnShape implements UISelector {
	private final Class<? extends PolygonPointsIterator> type;

	public UISelectorBasedOnShape(Class<? extends PolygonPointsIterator> type) {
		this.type = type;
	}

	@Override
	public boolean appliesTo(WireframeNode renderable) {
		if ((renderable.getValue() == null) || (renderable.getValue() == null)) return false;
		return renderable.getShape().getClass().equals(type);
	}
}
