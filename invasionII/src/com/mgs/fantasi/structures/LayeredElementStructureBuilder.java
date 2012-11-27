package com.mgs.fantasi.structures;

import com.mgs.fantasi.ui.wireframe.Wireframe;

public class LayeredElementStructureBuilder extends BaseStructureBuilder {
	private final StructureBuilder[] contentBuilder;

	public LayeredElementStructureBuilder(StructureBuilder... contentBuilder) {
		this.contentBuilder = contentBuilder;
	}

	@Override
	protected Wireframe buildContent() {
		return null;
	}
}
