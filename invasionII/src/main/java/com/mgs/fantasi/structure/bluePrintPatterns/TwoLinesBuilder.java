package com.mgs.fantasi.structure.bluePrintPatterns;

import com.mgs.fantasi.properties.data.measurements.Fraction;
import com.mgs.fantasi.properties.data.measurements.Fractions;
import com.mgs.fantasi.structure.Structure;
import com.mgs.fantasi.structure.StructureBuilderFactory;
import com.mgs.fantasi.structure.bluePrint.GridStructureBuilder;
import com.mgs.fantasi.wireframe.Wireframe;

import java.awt.*;

public class TwoLinesBuilder implements StructureContentBuilder {
	private Fraction firstLineHeightSizeRatio;
	private StructureBuilderFactory.StructureBuilder firstLine;
	private StructureBuilderFactory.StructureBuilder secondLine;

	public TwoLinesBuilder withFirstLineHeightSizeRatio(Fraction firstLineHeightSizeRatio) {
		this.firstLineHeightSizeRatio = firstLineHeightSizeRatio;
		return this;
	}

	public TwoLinesBuilder withFirstLineTreeBuilder(StructureBuilderFactory.StructureBuilder firstLine) {
		this.firstLine = firstLine;
		return this;
	}

	public TwoLinesBuilder withSecondLineTreeBuilder(StructureBuilderFactory.StructureBuilder secondLine) {
		this.secondLine = secondLine;
		return this;
	}

	@Override
	public Structure buildStructure(String name, Wireframe wireframe) {
		Fraction remainder = Fractions.allWithBase(firstLineHeightSizeRatio.getBase()).minus(firstLineHeightSizeRatio);
		return new GridStructureBuilder()
				.withDimension(new Dimension(1, 2))
				.withCell(new Point(0, 0), firstLineHeightSizeRatio, Fractions.all(), firstLine)
				.withCell(new Point(0, 1), remainder, Fractions.all(), secondLine)
				.fill()
				.buildStructure(name, wireframe, this.getClass());
	}
}
