package com.mgs.fantasi.properties.selectors;

import com.mgs.fantasi.views.View;

public class UISelectorBasedOnName implements UISelector {
	private final String name;

	public UISelectorBasedOnName(String name) {
		this.name = name;
	}

	@Override
	public boolean appliesTo(View renderable) {
		return renderable.getName().equals(name);
	}
}
