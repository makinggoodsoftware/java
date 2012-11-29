package com.mgs.fantasi.structures;

import com.mgs.fantasi.ui.wireframe.Grid;
import com.mgs.fantasi.ui.wireframe.Wireframe;

public class VerticalSlicesStructureBuilder extends BaseStructureBuilder {

	private final StructureBuilder contentBuilder;

	public VerticalSlicesStructureBuilder(StructureBuilder contentBuilder) {
		this.contentBuilder = contentBuilder;
	}

	@Override
	protected Wireframe generateContentFor(int x, int y) {
		return null;
	}

	@Override
	protected Grid<Wireframe> buildLayout() {
		return null;
	}
}
