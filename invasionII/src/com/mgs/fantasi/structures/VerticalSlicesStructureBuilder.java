package com.mgs.fantasi.structures;

import com.mgs.fantasi.ui.wireframe.Wireframe;

public class VerticalSlicesStructureBuilder extends BaseStructureBuilder {

	private final StructureBuilder contentBuilder;

	public VerticalSlicesStructureBuilder(StructureBuilder contentBuilder) {
		this.contentBuilder = contentBuilder;
	}

	@Override
	protected Wireframe buildContent() {
		return null;
	}
}
