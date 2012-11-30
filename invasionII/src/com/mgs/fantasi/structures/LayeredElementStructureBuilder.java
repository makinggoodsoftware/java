package com.mgs.fantasi.structures;

import com.mgs.fantasi.ui.wireframe.Grid;
import com.mgs.fantasi.ui.wireframe.Wireframe;

public class LayeredElementStructureBuilder extends BaseStructureBuilder {
	private final StructureBuilder[] contentBuilder;

	public LayeredElementStructureBuilder(StructureBuilder... contentBuilder) {
		this.contentBuilder = contentBuilder;
	}

	@Override
	protected boolean constraintsAreSatisfied() {
		return false;
	}

	@Override
	protected Grid<Wireframe> buildLayoutAndChilds() {
		return null;
	}
}
