package com.mgs.fantasi.ui.selectors;

import com.mgs.fantasi.ui.wireframe.Wireframe;
import com.mgs.fantasi.views.View;

public class UISelectorBasedOnStructureType implements UISelector {
	private final Class<? extends View> type;

	public UISelectorBasedOnStructureType(Class<? extends View> type) {
		this.type = type;
	}

	@Override
	public boolean appliesTo(Wireframe wireframe) {
		return wireframe.getStructureType().equals(type);
	}
}
