package com.mgs.fantasi.selectors;

import com.mgs.fantasi.views.WireframeBuilder;
import com.mgs.fantasi.wireframe.Wireframe;

public class UISelectorBasedOnStructureType implements UISelector {
	private final Class<? extends WireframeBuilder> type;

	public UISelectorBasedOnStructureType(Class<? extends WireframeBuilder> type) {
		this.type = type;
	}

	@Override
	public boolean appliesTo(Wireframe wireframe) {
		return wireframe.getBuilderClass().equals(type);
	}
}
