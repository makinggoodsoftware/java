package com.mgs.fantasi.structure.bluePrintPatterns;

import com.mgs.fantasi.structure.BasicBluePrintBuildersFactory;
import com.mgs.fantasi.structure.bluePrint.BluePrint;
import com.mgs.fantasi.structure.bluePrint.GridBluePrint;
import com.mgs.fantasi.wireframe.Wireframe;

import java.awt.*;

public class VerticalRepeaterPattern implements BasicBluePrintBuildersFactory.BluePrintBuilder {
	private String name;
	private Wireframe wireframe;
	private BluePrint bluePrint;
	private int numberOfGenerations;

	@Override
	public void initialise(String name, Wireframe wireframe) {
		this.name = name;
		this.wireframe = wireframe;
	}

	public VerticalRepeaterPattern repeating(BluePrint bluePrint) {
		this.bluePrint = bluePrint;
		return this;
	}

	public VerticalRepeaterPattern repetitions(int numberOfGenerations) {
		this.numberOfGenerations = numberOfGenerations;
		return this;
	}

	@Override
	public BluePrint buildBlueprint() {
		return new GridBluePrint(name, wireframe)
				.withDimension(new Dimension(numberOfGenerations, 1))
				.allCellsWith(bluePrint);
	}
}
