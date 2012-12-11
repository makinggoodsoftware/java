package com.mgs.fantasi.ui.selectors;

import com.mgs.fantasi.polygon.PolygonPointsIterator;
import com.mgs.fantasi.views.View;

public class UISelectorFactory {

	public static UISelector forType(Class<? extends View> type) {
		return new UISelectorBasedOnStructureType(type);
	}

	public static UISelector forShape(Class<? extends PolygonPointsIterator> type) {
		return new UISelectorBasedOnShape (type);
	}

	public static UISelector forName(String name) {
		return new UISelectorBasedOnName (name);
	}
}

