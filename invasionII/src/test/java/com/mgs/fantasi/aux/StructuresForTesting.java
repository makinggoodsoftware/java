package com.mgs.fantasi.aux;

import com.mgs.fantasi.structure.Structure;

import static com.mgs.fantasi.structure.structureBuilder.StructureBuilderFactory.createStructureBuilder;
import static com.mgs.fantasi.wireframe.Wireframes.basicRectangle;

public abstract class StructuresForTesting {
	public static Structure simpleStructure() {
		return createStructureBuilder("simpleStructure").withFrame(basicRectangle()).build();
	}
}
