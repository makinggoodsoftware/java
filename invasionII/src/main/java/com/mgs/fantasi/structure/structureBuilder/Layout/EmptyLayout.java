package com.mgs.fantasi.structure.structureBuilder.Layout;

import com.mgs.fantasi.structure.Structure;
import com.mgs.fantasi.wireframe.Wireframe;

import static com.mgs.fantasi.structure.Structures.emptyStructure;

public class EmptyLayout implements StructureLayout {
	@Override
	public Structure buildStructure(String name, Wireframe wireframe) {
		return emptyStructure(wireframe, name, this.getClass());
	}
}
