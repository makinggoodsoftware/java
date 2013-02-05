package com.mgs.fantasi.selectors;

import com.mgs.fantasi.properties.data.polygon.PolygonPointsIterator;
import com.mgs.fantasi.wireframeTreeBuilders.WireframeTreeBuilder;

public class UISelectorFactory {

	public static UISelector forType(Class<? extends WireframeTreeBuilder> type) {
		return new UISelectorBasedOnStructureType(type);
	}

	public static UISelector forShape(Class<? extends PolygonPointsIterator> type) {
		return new UISelectorBasedOnShape(type);
	}

	public static UISelector forName(String name) {
		return new UISelectorBasedOnName(name);
	}
}

