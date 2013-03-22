package com.mgs.fantasi.aux;

import com.mgs.fantasi.structure.Structure;
import com.mgs.fantasi.wireframe.Wireframe;

import static com.mgs.fantasi.structure.structureBuilder.StructureBuilderFactory.createStructureBuilder;

public abstract class StructuresForTesting {
	public static Structure simpleStructure(Wireframe wireframe) {
		return createStructureBuilder("simpleStructure").withFrame(wireframe).build();
	}
}
