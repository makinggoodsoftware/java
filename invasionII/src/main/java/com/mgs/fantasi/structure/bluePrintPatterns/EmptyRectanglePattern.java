package com.mgs.fantasi.structure.bluePrintPatterns;

import com.mgs.fantasi.structure.bluePrint.BluePrint;
import com.mgs.fantasi.structure.bluePrint.NoChildrenBluePrint;
import com.mgs.fantasi.wireframe.Wireframe;

public class EmptyRectanglePattern implements BluePrintPattern {
	private String name;
	private Wireframe wireframe;

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
