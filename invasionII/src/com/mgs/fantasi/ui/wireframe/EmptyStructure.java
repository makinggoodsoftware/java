package com.mgs.fantasi.ui.wireframe;

public class EmptyStructure<T extends Structurable> implements Structure<T> {
	@Override
	public <Z extends Structurable> Structure<Z> transformInto(StructureTransformer<T, Z> renderableZStructureTransformer, Structure<Z> into) {
		return null;
	}
}
