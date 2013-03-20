package com.mgs.fantasi.structure.bluePrintPatterns;

import com.mgs.fantasi.structure.Structure;
import com.mgs.fantasi.structure.StructureBuilderFactory;
import com.mgs.fantasi.structure.bluePrint.GridStructureBuilder;
import com.mgs.fantasi.wireframe.Wireframe;

import java.awt.*;

public class HorizontalRepeaterBuilder implements StructureContentBuilder {
	private StructureBuilderFactory.StructureBuilder structureBuilderPattern;
	private int numberOfGenerations;

	public HorizontalRepeaterBuilder repeating(StructureBuilderFactory.StructureBuilder structureBuilderPattern) {
		this.structureBuilderPattern = structureBuilderPattern;
		return this;
	}

	public HorizontalRepeaterBuilder repetitions(int numberOfGenerations) {
		this.numberOfGenerations = numberOfGenerations;
		return this;
	}

	@Override
	public Structure buildStructure(String name, Wireframe wireframe) {
		return new GridStructureBuilder()
				.withDimension(new Dimension(1, numberOfGenerations))
				.allCellsWith(structureBuilderPattern)
				.buildStructure(name, wireframe, this.getClass());
	}
}
