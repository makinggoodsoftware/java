package com.mgs.fantasi.ui.selectors;

import com.mgs.fantasi.polygon.PolygonPointsIterator;
import com.mgs.fantasi.structures.StructureBuilder;

public class UISelectorFactory {

	public static UISelector forType(Class<? extends StructureBuilder> type) {
		return new UISelectorBasedOnStructureType(type);
	}

	public static UISelector forShape(Class<? extends PolygonPointsIterator> type) {
		return new UISelectorBasedOnShape (type);
	}
}

