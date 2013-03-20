package com.mgs.fantasi.structure.bluePrintPatterns;

import com.mgs.fantasi.structure.Structure;
import com.mgs.fantasi.structure.StructureBuilderFactory;
import com.mgs.fantasi.structure.bluePrint.GridStructureBuilder;
import com.mgs.fantasi.wireframe.Wireframe;

import java.awt.*;

public class VerticalRepeaterBuilder implements StructureContentBuilder {
	private StructureBuilderFactory.StructureBuilder structureBuilderPattern;
	private int numberOfGenerations;

	public VerticalRepeaterBuilder repeating(StructureBuilderFactory.StructureBuilder structureBuilder) {
		this.structureBuilderPattern = structureBuilder;
		return this;
	}

	public VerticalRepeaterBuilder repetitions(int numberOfGenerations) {
		this.numberOfGenerations = numberOfGenerations;
		return this;
	}

	@Override
	public Structure buildStructure(String name, Wireframe wireframe) {
		return new GridStructureBuilder()
				.withDimension(new Dimension(numberOfGenerations, 1))
				.allCellsWith(structureBuilderPattern)
				.buildStructure(name, wireframe, this.getClass());
	}
}
