package com.mgs.fantasi.structure.bluePrintPatterns;

import com.mgs.fantasi.structure.BluePrintBuilderFactory;
import com.mgs.fantasi.structure.bluePrint.BluePrint;
import com.mgs.fantasi.structure.bluePrint.GridBluePrint;
import com.mgs.fantasi.wireframe.Wireframe;

import java.awt.*;

public class HorizontalRepeaterBuilderPattern implements BluePrintBuilderPattern {
	private String name;
	private Wireframe wireframe;
	private BluePrintBuilderFactory.BluePrintBuilder bluePrintBuilderPattern;
	private int numberOfGenerations;

	@Override
	public void initialise(String name, Wireframe wireframe) {
		this.name = name;
		this.wireframe = wireframe;
	}

	public HorizontalRepeaterBuilderPattern repeating(BluePrintBuilderFactory.BluePrintBuilder bluePrintBuilderPattern) {
		this.bluePrintBuilderPattern = bluePrintBuilderPattern;
		return this;
	}

	public HorizontalRepeaterBuilderPattern repetitions(int numberOfGenerations) {
		this.numberOfGenerations = numberOfGenerations;
		return this;
	}

	@Override
	public BluePrint buildBlueprint() {
		return new GridBluePrint(name, wireframe)
				.withDimension(new Dimension(1, numberOfGenerations))
				.allCellsWith(bluePrintBuilderPattern.build());
	}
}
