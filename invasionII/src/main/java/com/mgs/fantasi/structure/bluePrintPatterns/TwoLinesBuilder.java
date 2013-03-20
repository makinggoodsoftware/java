package com.mgs.fantasi.structure.bluePrintPatterns;

import com.mgs.fantasi.properties.data.measurements.Fraction;
import com.mgs.fantasi.properties.data.measurements.Fractions;
import com.mgs.fantasi.structure.BluePrintBuilderFactory;
import com.mgs.fantasi.structure.bluePrint.BluePrint;
import com.mgs.fantasi.structure.bluePrint.GridBluePrint;
import com.mgs.fantasi.wireframe.Wireframe;

import java.awt.*;

public class TwoLinesBuilder implements BluePrintBuilder {
	private Fraction firstLineHeightSizeRatio;
	private BluePrintBuilderFactory.BluePrintBuilderHelper firstLine;
	private BluePrintBuilderFactory.BluePrintBuilderHelper secondLine;

	public TwoLinesBuilder withFirstLineHeightSizeRatio(Fraction firstLineHeightSizeRatio) {
		this.firstLineHeightSizeRatio = firstLineHeightSizeRatio;
		return this;
	}

	public TwoLinesBuilder withFirstLineTreeBuilder(BluePrintBuilderFactory.BluePrintBuilderHelper firstLine) {
		this.firstLine = firstLine;
		return this;
	}

	public TwoLinesBuilder withSecondLineTreeBuilder(BluePrintBuilderFactory.BluePrintBuilderHelper secondLine) {
		this.secondLine = secondLine;
		return this;
	}

	@Override
	public BluePrint buildBlueprint(String name, Wireframe wireframe) {
		Fraction remainder = Fractions.allWithBase(firstLineHeightSizeRatio.getBase()).minus(firstLineHeightSizeRatio);
		return new GridBluePrint(name, wireframe)
				.withDimension(new Dimension(1, 2))
				.withCell(new Point(0, 0), firstLineHeightSizeRatio, Fractions.all(), firstLine.build())
				.withCell(new Point(0, 1), remainder, Fractions.all(), secondLine.build())
				.fill();
	}
}
