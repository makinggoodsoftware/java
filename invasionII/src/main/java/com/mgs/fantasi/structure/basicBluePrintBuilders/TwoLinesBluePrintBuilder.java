package com.mgs.fantasi.structure.basicBluePrintBuilders;

import com.mgs.fantasi.properties.data.measurements.Fraction;
import com.mgs.fantasi.properties.data.measurements.Fractions;
import com.mgs.fantasi.structure.Structure;
import com.mgs.fantasi.structure.bluePrint.BluePrint;
import com.mgs.fantasi.structure.bluePrint.GridBluePrint;
import com.mgs.fantasi.wireframe.Wireframe;

import java.awt.*;

public class TwoLinesBluePrintBuilder implements BluePrint {
	private final String name;
	private final Wireframe wireframe;
	private BluePrint firstLine;
	private BluePrint secondLine;
	private Fraction firstLineHeightSizeRatio;

	public TwoLinesBluePrintBuilder(String name, Wireframe wireframe) {
		this.name = name;
		this.wireframe = wireframe;
	}

	public TwoLinesBluePrintBuilder line1(BluePrint firstLine) {
		this.firstLine = firstLine;
		return this;
	}

	public TwoLinesBluePrintBuilder line2(BluePrint secondLine) {
		this.secondLine = secondLine;
		return this;
	}

	public TwoLinesBluePrintBuilder firstLineHeightSizeRatio(Fraction firstLineHeightSizeRatio) {
		this.firstLineHeightSizeRatio = firstLineHeightSizeRatio;
		return this;
	}

	@Override
	public Structure buildStructure() {
		GridBluePrint gridWireframeTreeBuilder = new GridBluePrint(name, wireframe);
		Fraction remainder = Fractions.allWithBase(firstLineHeightSizeRatio.getBase()).minus(firstLineHeightSizeRatio);
		return gridWireframeTreeBuilder
				.withDimension(new Dimension(1, 2))
				.withCell(new Point(0, 0), firstLineHeightSizeRatio, Fractions.all(), firstLine)
				.withCell(new Point(0, 1), remainder, Fractions.all(), secondLine)
				.fill()
				.buildStructure();
	}

	@Override
	public String getName() {
		return name;
	}
}