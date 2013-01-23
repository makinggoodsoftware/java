package com.mgs.fantasi.selectors;

import com.mgs.fantasi.properties.polygon.PolygonPointsIterator;
import com.mgs.fantasi.wireframe.CollocationInfo;
import com.mgs.fantasi.wireframe.Tree;
import com.mgs.fantasi.wireframe.Wireframe;

public class UISelectorBasedOnShape implements UISelector {
	private final Class<? extends PolygonPointsIterator> type;

	public UISelectorBasedOnShape(Class<? extends PolygonPointsIterator> type) {
		this.type = type;
	}

	@Override
	public boolean appliesTo(Tree<Wireframe, CollocationInfo> renderable) {
		if ((renderable == null) || (renderable.getContent().getUiProperties() == null)) return false;
		return renderable.getContent().getUiProperties().getShape().getClass().equals(type);
	}
}
