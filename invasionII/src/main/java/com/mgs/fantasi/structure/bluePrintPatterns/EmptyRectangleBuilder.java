package com.mgs.fantasi.structure.bluePrintPatterns;

import com.mgs.fantasi.structure.Structure;
import com.mgs.fantasi.wireframe.Wireframe;

import static com.mgs.fantasi.structure.Structures.emptyStructure;

public class EmptyRectangleBuilder implements StructureContentBuilder {
	@Override
	public Structure buildStructure(String name, Wireframe wireframe) {
		return emptyStructure(wireframe, name, this.getClass());
	}
}
