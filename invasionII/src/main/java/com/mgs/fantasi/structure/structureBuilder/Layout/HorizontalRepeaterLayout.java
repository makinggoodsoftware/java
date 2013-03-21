package com.mgs.fantasi.structure.structureBuilder.Layout;

import com.mgs.fantasi.structure.Structure;
import com.mgs.fantasi.structure.structureBuilder.StructureBuilder;
import com.mgs.fantasi.wireframe.Wireframe;

import java.awt.*;

public class HorizontalRepeaterLayout implements StructureLayout {
	private StructureBuilder structureBuilderPattern;
	private int numberOfGenerations;

	public HorizontalRepeaterLayout repeating(StructureBuilder structureBuilderPattern) {
		this.structureBuilderPattern = structureBuilderPattern;
		return this;
	}

	public HorizontalRepeaterLayout repetitions(int numberOfGenerations) {
		this.numberOfGenerations = numberOfGenerations;
		return this;
	}

	@Override
	public Structure buildStructure(String name, Wireframe wireframe) {
		return new GridLayoutBuilder()
				.withDimension(new Dimension(1, numberOfGenerations))
				.allCellsWith(structureBuilderPattern)
				.buildStructure(name, wireframe, this.getClass());
	}
}
