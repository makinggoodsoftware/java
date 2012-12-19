package com.mgs.fantasi.properties.selectors;

import com.mgs.fantasi.rendering.Renderable;

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
