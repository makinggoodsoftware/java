package com.mgs.fantasi.selectors;

import com.mgs.fantasi.views.View;

public class UISelectorBasedOnStructureType implements UISelector {
	private final Class<? extends View> type;

	public UISelectorBasedOnStructureType(Class<? extends View> type) {
		this.type = type;
	}

	@Override
	public boolean appliesTo(View view) {
		return view.getClass().equals(type);
	}
}
