package com.mgs.fantasi.ui.selectors;

import com.mgs.fantasi.ui.wireframe.Wireframe;

public class UISelectorBasedOnName implements UISelector {
	private final String name;

	public UISelectorBasedOnName(String name) {
		this.name = name;
	}

	@Override
	public boolean appliesTo(Wireframe wireframe) {
		return wireframe.getName().equals(name);
	}
}
