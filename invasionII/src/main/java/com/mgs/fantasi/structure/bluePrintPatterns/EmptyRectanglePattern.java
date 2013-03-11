package com.mgs.fantasi.structure.bluePrintPatterns;

import com.mgs.fantasi.structure.BasicBluePrintBuildersFactory;
import com.mgs.fantasi.structure.bluePrint.BluePrint;
import com.mgs.fantasi.structure.bluePrint.NoChildrenBluePrint;
import com.mgs.fantasi.wireframe.Wireframe;

public class EmptyRectanglePattern implements BasicBluePrintBuildersFactory.BluePrintBuilder {
	private String name;
	private Wireframe wireframe;
	private BluePrint bluePrint;
	private int numberOfGenerations;

	@Override
	public void initialise(String name, Wireframe wireframe) {
		this.name = name;
		this.wireframe = wireframe;
	}

	@Override
	public BluePrint buildBlueprint() {
		return new NoChildrenBluePrint(name, wireframe);
	}
}
