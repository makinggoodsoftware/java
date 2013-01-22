package com.mgs.fantasi.selectors;

import com.mgs.fantasi.wireframe.Tree;
import com.mgs.fantasi.wireframe.Wireframe;

public class UISelectorBasedOnName implements UISelector {
	private final String name;

	public UISelectorBasedOnName(String name) {
		this.name = name;
	}

	@Override
	public boolean appliesTo(Tree<Wireframe> renderable) {
		return renderable.getContent().getName().equals(name);
	}
}
