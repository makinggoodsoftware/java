package com.mgs.fantasi.ui.wireframe;

public class DelegateStructure<T extends Structurable> implements Structure<T> {
	private Structure<T> content;

	public DelegateStructure(Structure<T> content) {
		this.content = content;
	}

	@Override
	public <Z extends Structurable> Structure<Z> transformInto(StructureTransformer<T, Z> tzStructureTransformer, Structure<Z> into) {
		return null;
	}

	public Structure<T> getContent() {
		return content;
	}
}
