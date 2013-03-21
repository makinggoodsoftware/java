package com.mgs.fantasi.structure.structureBuilder.Layout;

import com.mgs.fantasi.structure.Structure;
import com.mgs.fantasi.wireframe.Wireframe;

import static com.mgs.fantasi.structure.Structures.emptyStructure;

public class EmptyLayout implements StructureLayout {
	private EmptyLayout() {
	}

	public static EmptyLayout emptyLayout() {
		return new EmptyLayout();
	}


	@Override
	public Structure buildStructure(String name, Wireframe wireframe) {
		return emptyStructure(wireframe, name, this.getClass());
	}
}
