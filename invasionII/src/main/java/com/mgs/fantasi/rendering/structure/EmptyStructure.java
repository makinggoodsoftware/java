package com.mgs.fantasi.rendering.structure;

import com.mgs.fantasi.Structurable;

public class EmptyStructure<T extends Structurable> implements Structure<T> {
	@Override
	public StructureType getType() {
		return StructureType.EMPTY;
	}
}
