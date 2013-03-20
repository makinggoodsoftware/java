package com.mgs.fantasi.structure.bluePrintPatterns;

import com.mgs.fantasi.structure.BluePrintBuilderFactory;
import com.mgs.fantasi.structure.bluePrint.BluePrint;
import com.mgs.fantasi.structure.bluePrint.GridBluePrint;
import com.mgs.fantasi.wireframe.Wireframe;

import java.awt.*;

public class HorizontalRepeaterBuilder implements BluePrintBuilder {
	private BluePrintBuilderFactory.BluePrintBuilderHelper bluePrintBuilderHelperPattern;
	private int numberOfGenerations;

	public HorizontalRepeaterBuilder repeating(BluePrintBuilderFactory.BluePrintBuilderHelper bluePrintBuilderHelperPattern) {
		this.bluePrintBuilderHelperPattern = bluePrintBuilderHelperPattern;
		return this;
	}

	public HorizontalRepeaterBuilder repetitions(int numberOfGenerations) {
		this.numberOfGenerations = numberOfGenerations;
		return this;
	}

	@Override
	public BluePrint buildBlueprint(String name, Wireframe wireframe) {
		return new GridBluePrint(name, wireframe)
				.withDimension(new Dimension(1, numberOfGenerations))
				.allCellsWith(bluePrintBuilderHelperPattern.build());
	}
}
