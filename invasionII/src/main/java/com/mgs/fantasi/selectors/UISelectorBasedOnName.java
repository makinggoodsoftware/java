package com.mgs.fantasi.selectors;

import com.mgs.fantasi.wireframe.WireframeTree;

public class UISelectorBasedOnName implements UISelector {
	private final String name;

	public UISelectorBasedOnName(String name) {
		this.name = name;
	}

	@Override
	public boolean appliesTo(WireframeTree renderable) {
		return renderable.getName().equals(name);
	}
}
