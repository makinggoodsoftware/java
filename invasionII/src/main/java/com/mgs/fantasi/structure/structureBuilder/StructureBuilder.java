package com.mgs.fantasi.structure.structureBuilder;

import com.mgs.fantasi.structure.Structure;
import com.mgs.fantasi.structure.structureBuilder.Layout.StructureLayout;
import com.mgs.fantasi.wireframe.Wireframe;

public class StructureBuilder {
	private final String name;
	private Wireframe wireframe;
	private StructureLayout layout;

	StructureBuilder(String name) {
		this.name = name;
	}

	public StructureBuilder withLayout(StructureLayout builder) {
		this.layout = builder;
		return this;
	}

	public StructureBuilder withFrame(Wireframe wireframe) {
		this.wireframe = wireframe;
		return this;
	}

	public Structure build() {
		return layout.buildStructure(name, wireframe);
	}
}
