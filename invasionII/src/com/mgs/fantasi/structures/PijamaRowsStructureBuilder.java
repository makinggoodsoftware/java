package com.mgs.fantasi.structures;

import com.mgs.fantasi.ui.wireframe.Grid;
import com.mgs.fantasi.ui.wireframe.Wireframe;

public class PijamaRowsStructureBuilder extends BaseStructureBuilder {
	private final StructureBuilder mainRowBuilder;
	private final StructureBuilder emptyRowBuilder;

	public PijamaRowsStructureBuilder(StructureBuilder mainRowBuilder, StructureBuilder emptyRowBuilder) {
		this.mainRowBuilder = mainRowBuilder;
		this.emptyRowBuilder = emptyRowBuilder;
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
