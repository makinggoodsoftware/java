package com.mgs.fantasi.selectors;

import com.mgs.fantasi.wireframe.CollocationInfo;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.tree.Tree;

public class UISelectorBasedOnName implements UISelector {
	private final String name;

	public UISelectorBasedOnName(String name) {
		this.name = name;
	}

	@Override
	public boolean appliesTo(Tree<Wireframe, CollocationInfo> renderable) {
		return renderable.getContent().getName().equals(name);
	}
}
