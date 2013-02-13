package com.mgs.fantasi.selectors;

import com.mgs.fantasi.wireframe.WireframeNode;
import com.mgs.fantasi.wireframe.builder.WireframeTreeBuilder;

public class UISelectorBasedOnStructureType implements UISelector {
	private final Class<? extends WireframeTreeBuilder> type;

	public UISelectorBasedOnStructureType(Class<? extends WireframeTreeBuilder> type) {
		this.type = type;
	}

	@Override
	public boolean appliesTo(WireframeNode wireframe) {
		return wireframe.getBuilder().equals(type);
	}
}
