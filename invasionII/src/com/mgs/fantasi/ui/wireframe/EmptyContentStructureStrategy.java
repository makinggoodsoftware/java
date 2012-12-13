package com.mgs.fantasi.ui.wireframe;

public class EmptyContentStructureStrategy implements ContentStructureStrategy {
	@Override
	public StructureFactory.StructureType getContentStructureType() {
		return StructureFactory.StructureType.EMPTY;
	}
}
