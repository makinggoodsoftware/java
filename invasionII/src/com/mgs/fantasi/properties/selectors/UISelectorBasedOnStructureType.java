package com.mgs.fantasi.properties.selectors;

import com.mgs.fantasi.rendering.Renderable;
import com.mgs.fantasi.views.View;

public class UISelectorBasedOnStructureType implements UISelector {
	private final Class<? extends View> type;

	public UISelectorBasedOnStructureType(Class<? extends View> type) {
		this.type = type;
	}

	@Override
	public boolean appliesTo(Renderable renderable) {
		return renderable.getView().equals(type);
	}
}
