package com.mgs.fantasi.structure.bluePrintPatterns;

import com.mgs.fantasi.properties.data.measurements.Fraction;
import com.mgs.fantasi.properties.data.measurements.Fractions;
import com.mgs.fantasi.structure.bluePrint.BluePrint;
import com.mgs.fantasi.structure.bluePrint.GridBluePrint;
import com.mgs.fantasi.wireframe.Wireframe;

import java.awt.*;

public class TwoLinesPattern implements BluePrintPattern {
	private String name;
	private Wireframe wireframe;
	private Fraction firstLineHeightSizeRatio;
	private BluePrint firstLineTreeBuilder;
	private BluePrint secondLineTreeBuilder;

	public TwoLinesPattern withFirstLineHeightSizeRatio(Fraction firstLineHeightSizeRatio) {
		this.firstLineHeightSizeRatio = firstLineHeightSizeRatio;
		return this;
	}

	public TwoLinesPattern withFirstLineTreeBuilder(BluePrint firstLineTreeBuilder) {
		this.firstLineTreeBuilder = firstLineTreeBuilder;
		return this;
	}

	public TwoLinesPattern withSecondLineTreeBuilder(BluePrint secondLineTreeBuilder) {
		this.secondLineTreeBuilder = secondLineTreeBuilder;
		return this;
	}

	@Override
	public void initialise(String name, Wireframe wireframe) {
		this.name = name;
		this.wireframe = wireframe;
	}

	@Override
	public BluePrint buildBlueprint() {
		Fraction remainder = Fractions.allWithBase(firstLineHeightSizeRatio.getBase()).minus(firstLineHeightSizeRatio);
		return new GridBluePrint(name, wireframe)
				.withDimension(new Dimension(1, 2))
				.withCell(new Point(0, 0), firstLineHeightSizeRatio, Fractions.all(), firstLineTreeBuilder)
				.withCell(new Point(0, 1), remainder, Fractions.all(), secondLineTreeBuilder)
				.fill();
	}
}
