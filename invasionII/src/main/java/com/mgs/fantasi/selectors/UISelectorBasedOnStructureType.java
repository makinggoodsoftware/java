package com.mgs.fantasi.selectors;

import com.mgs.fantasi.views.WireframeTreeBuilder;
import com.mgs.fantasi.wireframe.Tree;
import com.mgs.fantasi.wireframe.Wireframe;

public class UISelectorBasedOnStructureType implements UISelector {
	private final Class<? extends WireframeTreeBuilder> type;

	public UISelectorBasedOnStructureType(Class<? extends WireframeTreeBuilder> type) {
		this.type = type;
	}

	@Override
	public boolean appliesTo(Tree<Wireframe> tree) {
		return tree.getContent().getBuilderClass().equals(type);
	}
}
