package com.mgs.fantasi.structure.bluePrintPatterns;

import com.mgs.fantasi.structure.bluePrint.BluePrint;
import com.mgs.fantasi.structure.bluePrint.GridBluePrint;
import com.mgs.fantasi.wireframe.Wireframe;

import java.awt.*;

public class HorizontalRepeaterPattern implements BluePrintPattern {
	private String name;
	private Wireframe wireframe;
	private BluePrintPattern bluePrintPattern;
	private int numberOfGenerations;

	@Override
	public void initialise(String name, Wireframe wireframe) {
		this.name = name;
		this.wireframe = wireframe;
	}

	public HorizontalRepeaterPattern repeating(BluePrintPattern bluePrintPattern) {
		this.bluePrintPattern = bluePrintPattern;
		return this;
	}

	public HorizontalRepeaterPattern repetitions(int numberOfGenerations) {
		this.numberOfGenerations = numberOfGenerations;
		return this;
	}

	@Override
	public BluePrint buildBlueprint() {
		return new GridBluePrint(name, wireframe)
				.withDimension(new Dimension(1, numberOfGenerations))
				.allCellsWith(bluePrintPattern.buildBlueprint());
	}
}