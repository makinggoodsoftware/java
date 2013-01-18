package com.mgs.fantasi.selectors;

import com.mgs.fantasi.views.WireframeBuilder;
import com.mgs.fantasi.wireframe.WireframeTree;

public class UISelectorBasedOnStructureType implements UISelector {
	private final Class<? extends WireframeBuilder> type;

	public UISelectorBasedOnStructureType(Class<? extends WireframeBuilder> type) {
		this.type = type;
	}

	@Override
	public boolean appliesTo(WireframeTree wireframeTree) {
		return wireframeTree.getBuilderClass().equals(type);
	}
}
