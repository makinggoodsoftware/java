package com.mgs.fantasi.selectors;

import com.mgs.fantasi.structure.structureBuilder.Layout.StructureLayout;
import com.mgs.fantasi.structure.treeAux.WireframeNode;

public class UISelectorBasedOnStructureType implements UISelector {
	private final Class<? extends StructureLayout> type;

	public UISelectorBasedOnStructureType(Class<? extends StructureLayout> type) {
		this.type = type;
	}

	@Override
	public boolean appliesTo(WireframeNode wireframe) {
		return wireframe.getBuilder().equals(type);
	}
}
