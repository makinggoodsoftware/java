package com.mgs.fantasi.structures;

import com.mgs.fantasi.ui.wireframe.Wireframe;

public class PijamaRowsStructureBuilder extends BaseStructureBuilder {
	private final StructureBuilder mainRowBuilder;
	private final StructureBuilder emptyRowBuilder;

	public PijamaRowsStructureBuilder(StructureBuilder mainRowBuilder, StructureBuilder emptyRowBuilder) {
		this.mainRowBuilder = mainRowBuilder;
		this.emptyRowBuilder = emptyRowBuilder;
	}

	@Override
	protected Wireframe buildContent() {
		return null;
	}
}
