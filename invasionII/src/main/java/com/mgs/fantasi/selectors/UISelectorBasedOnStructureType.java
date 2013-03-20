package com.mgs.fantasi.selectors;

import com.mgs.fantasi.structure.bluePrintPatterns.StructureContentBuilder;
import com.mgs.fantasi.structure.treeAux.WireframeNode;

public class UISelectorBasedOnStructureType implements UISelector {
	private final Class<? extends StructureContentBuilder> type;

	public UISelectorBasedOnStructureType(Class<? extends StructureContentBuilder> type) {
		this.type = type;
	}

	@Override
	public boolean appliesTo(WireframeNode wireframe) {
		return wireframe.getBuilder().equals(type);
	}
}
