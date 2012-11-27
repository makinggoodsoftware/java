package com.mgs.fantasi.ui.selectors;

import com.mgs.fantasi.ui.wireframe.Wireframe;
import com.mgs.fantasi.structures.StructureBuilder;

public class UISelectorBasedOnStructureType implements UISelector {
	private final Class<? extends StructureBuilder> type;

	public UISelectorBasedOnStructureType(Class<? extends StructureBuilder> type) {
		this.type = type;
	}

	@Override
	public boolean appliesTo(Wireframe wireframe) {
		return wireframe.getStructureType().equals(type);
	}
}
