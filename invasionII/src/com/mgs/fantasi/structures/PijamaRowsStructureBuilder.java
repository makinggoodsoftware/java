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
	protected boolean constraintsAreSatisfied() {
		return false;
	}

	@Override
	protected Grid<Wireframe> buildLayoutAndChilds() {
		return null;
	}
}
