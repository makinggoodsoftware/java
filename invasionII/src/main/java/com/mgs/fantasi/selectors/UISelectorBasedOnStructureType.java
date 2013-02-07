package com.mgs.fantasi.selectors;

import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.builder.WireframeTreeBuilder;

public class UISelectorBasedOnStructureType implements UISelector {
	private final Class<? extends WireframeTreeBuilder> type;

	public UISelectorBasedOnStructureType(Class<? extends WireframeTreeBuilder> type) {
		this.type = type;
	}

	@Override
	public boolean appliesTo(Wireframe wireframe) {
		return wireframe.getBuilderClass().equals(type);
	}
}
