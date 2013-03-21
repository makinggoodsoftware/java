package com.mgs.fantasi.structure.structureBuilder;

public abstract class StructureBuilderFactory {

	public static StructureBuilder createStructureBuilder(String name) {
		return new StructureBuilder(name);
	}

}
