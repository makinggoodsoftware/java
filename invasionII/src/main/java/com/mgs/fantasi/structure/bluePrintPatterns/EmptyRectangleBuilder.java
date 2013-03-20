package com.mgs.fantasi.structure.bluePrintPatterns;

import com.mgs.fantasi.structure.bluePrint.BluePrint;
import com.mgs.fantasi.structure.bluePrint.NoChildrenBluePrint;
import com.mgs.fantasi.wireframe.Wireframe;

public class EmptyRectangleBuilder implements BluePrintBuilder {
	@Override
	public BluePrint buildBlueprint(String name, Wireframe wireframe) {
		return new NoChildrenBluePrint(name, wireframe);
	}
}
