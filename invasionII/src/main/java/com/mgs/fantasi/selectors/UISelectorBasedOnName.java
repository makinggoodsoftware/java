package com.mgs.fantasi.selectors;

import com.mgs.fantasi.structure.treeAux.WireframeNode;

public class UISelectorBasedOnName implements UISelector {
	private final String name;

	public UISelectorBasedOnName(String name) {
		this.name = name;
	}

	@Override
	public boolean appliesTo(WireframeNode renderable) {
		return renderable.getName().equals(name);
	}
}
