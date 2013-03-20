package com.mgs.fantasi.structure.bluePrintPatterns;

import com.mgs.fantasi.structure.BluePrintBuilderFactory;
import com.mgs.fantasi.structure.bluePrint.BluePrint;
import com.mgs.fantasi.structure.bluePrint.GridBluePrint;
import com.mgs.fantasi.wireframe.Wireframe;

import java.awt.*;

public class VerticalRepeaterBuilder implements BluePrintBuilder {
	private BluePrintBuilderFactory.BluePrintBuilderHelper bluePrintBuilderHelperPattern;
	private int numberOfGenerations;

	public VerticalRepeaterBuilder repeating(BluePrintBuilderFactory.BluePrintBuilderHelper bluePrintBuilderHelper) {
		this.bluePrintBuilderHelperPattern = bluePrintBuilderHelper;
		return this;
	}

	public VerticalRepeaterBuilder repetitions(int numberOfGenerations) {
		this.numberOfGenerations = numberOfGenerations;
		return this;
	}

	@Override
	public BluePrint buildBlueprint(String name, Wireframe wireframe) {
		return new GridBluePrint(name, wireframe)
				.withDimension(new Dimension(numberOfGenerations, 1))
				.allCellsWith(bluePrintBuilderHelperPattern.build());
	}
}
