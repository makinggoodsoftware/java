package com.mgs.fantasi.selectors;

import com.mgs.fantasi.views.WireframeTreeBuilder;
import com.mgs.fantasi.wireframe.WireframeTree;

public class UISelectorBasedOnStructureType implements UISelector {
	private final Class<? extends WireframeTreeBuilder> type;

	public UISelectorBasedOnStructureType(Class<? extends WireframeTreeBuilder> type) {
		this.type = type;
	}

	@Override
	public boolean appliesTo(WireframeTree wireframeTree) {
		return wireframeTree.getBuilderClass().equals(type);
	}
}
