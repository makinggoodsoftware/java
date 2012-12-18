package com.mgs.fantasi.ui.wireframe;

public class EmptyStructure<T extends Structurable> implements Structure<T> {
	@Override
	public StructureFactory.StructureType getType() {
		return StructureFactory.StructureType.EMPTY;
	}
}
