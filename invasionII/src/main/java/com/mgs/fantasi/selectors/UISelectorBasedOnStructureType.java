package com.mgs.fantasi.selectors;

import com.mgs.fantasi.wireframe.tree.WireframeNode;
import com.mgs.fantasi.wireframe.tree.builder.WireframeTreeBuilderOld;

public class UISelectorBasedOnStructureType implements UISelector {
	private final Class<? extends WireframeTreeBuilderOld> type;

	public UISelectorBasedOnStructureType(Class<? extends WireframeTreeBuilderOld> type) {
		this.type = type;
	}

	@Override
	public boolean appliesTo(WireframeNode wireframe) {
		return wireframe.getBuilder().equals(type);
	}
}
