package com.mgs.fantasi.ui.selectors;

import com.mgs.fantasi.ui.wireframe.Renderable;

public class UISelectorBasedOnName implements UISelector {
	private final String name;

	public UISelectorBasedOnName(String name) {
		this.name = name;
	}

	@Override
	public boolean appliesTo(Renderable renderable) {
		return renderable.getName().equals(name);
	}
}
