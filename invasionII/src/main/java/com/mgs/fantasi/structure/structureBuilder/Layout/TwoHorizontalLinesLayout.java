package com.mgs.fantasi.structure.structureBuilder.Layout;

import com.mgs.fantasi.properties.data.measurements.Fraction;
import com.mgs.fantasi.properties.data.measurements.Fractions;
import com.mgs.fantasi.structure.Structure;
import com.mgs.fantasi.structure.structureBuilder.StructureBuilder;
import com.mgs.fantasi.wireframe.Wireframe;

import java.awt.*;

public class TwoHorizontalLinesLayout implements StructureLayout {
	private Fraction firstLineHeightSizeRatio;
	private StructureBuilder firstLine;
	private StructureBuilder secondLine;

	private TwoHorizontalLinesLayout() {
	}

	public static TwoHorizontalLinesLayout twoHorizontalLines() {
		return new TwoHorizontalLinesLayout();
	}

	public TwoHorizontalLinesLayout withFirstLineHeightSizeRatio(Fraction firstLineHeightSizeRatio) {
		this.firstLineHeightSizeRatio = firstLineHeightSizeRatio;
		return this;
	}

	public TwoHorizontalLinesLayout withFirstLineTreeBuilder(StructureBuilder firstLine) {
		this.firstLine = firstLine;
		return this;
	}

	public TwoHorizontalLinesLayout withSecondLineTreeBuilder(StructureBuilder secondLine) {
		this.secondLine = secondLine;
		return this;
	}

	@Override
	public Structure buildStructure(String name, Wireframe wireframe) {
		Fraction remainder = Fractions.allWithBase(firstLineHeightSizeRatio.getBase()).minus(firstLineHeightSizeRatio);
		return GridLayoutBuilder.gridLayoutBuilder()
				.withDimension(new Dimension(1, 2))
				.withCell(new Point(0, 0), firstLineHeightSizeRatio, Fractions.all(), firstLine)
				.withCell(new Point(0, 1), remainder, Fractions.all(), secondLine)
				.fill()
				.buildStructure(name, wireframe, this.getClass());
	}
}
