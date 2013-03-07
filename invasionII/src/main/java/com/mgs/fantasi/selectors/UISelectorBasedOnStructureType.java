package com.mgs.fantasi.selectors;

import com.mgs.fantasi.structure.bluePrint.BluePrint;
import com.mgs.fantasi.structure.treeAux.WireframeNode;

public class UISelectorBasedOnStructureType implements UISelector {
	private final Class<? extends BluePrint> type;

	public UISelectorBasedOnStructureType(Class<? extends BluePrint> type) {
		this.type = type;
	}

	@Override
	public boolean appliesTo(WireframeNode wireframe) {
		return wireframe.getBuilder().equals(type);
	}
}
