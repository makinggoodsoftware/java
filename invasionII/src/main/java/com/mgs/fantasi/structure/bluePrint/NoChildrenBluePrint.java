package com.mgs.fantasi.structure.bluePrint;

import com.mgs.fantasi.structure.Structure;
import com.mgs.fantasi.wireframe.Wireframe;

import static com.mgs.fantasi.structure.Structures.emptyStructure;

public class NoChildrenBluePrint implements BluePrint {
	private final String name;
	private final Wireframe wireframe;

	public NoChildrenBluePrint(String name, Wireframe wireframe) {
		this.name = name;
		this.wireframe = wireframe;
	}

	@Override
	public Structure build() {
		return emptyStructure(wireframe, getName(), this.getClass());
	}

	@Override
	public String getName() {
		return name;
	}
}
