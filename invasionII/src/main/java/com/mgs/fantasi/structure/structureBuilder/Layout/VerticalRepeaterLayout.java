package com.mgs.fantasi.structure.structureBuilder.Layout;

import com.mgs.fantasi.structure.Structure;
import com.mgs.fantasi.structure.structureBuilder.StructureBuilder;
import com.mgs.fantasi.wireframe.Wireframe;

import java.awt.*;

public class VerticalRepeaterLayout implements StructureLayout {
	private StructureBuilder structureBuilderPattern;
	private int numberOfGenerations;

	private VerticalRepeaterLayout() {
	}

	public static VerticalRepeaterLayout verticalRepeater() {
		return new VerticalRepeaterLayout();
	}

	public VerticalRepeaterLayout repeating(StructureBuilder structureBuilder) {
		this.structureBuilderPattern = structureBuilder;
		return this;
	}

	public VerticalRepeaterLayout repetitions(int numberOfGenerations) {
		this.numberOfGenerations = numberOfGenerations;
		return this;
	}

	@Override
	public Structure buildStructure(String name, Wireframe wireframe) {
		return GridLayoutBuilder.gridLayoutBuilder()
				.withDimension(new Dimension(numberOfGenerations, 1))
				.allCellsWith(structureBuilderPattern)
				.buildStructure(name, wireframe, this.getClass());
	}
}
