package com.mgs.fantasi.ui.wireframe;

public class SimpleStructure<T extends Structurable> implements Structure<T> {
	private final T content;


	public SimpleStructure(T content) {
		this.content = content;
	}

	public T getContent() {
		return content;
	}

	@Override
	public <Z extends Structurable> Structure<Z> transformInto(StructureTransformer<T, Z> tzStructureTransformer, Structure<Z> into) {
		return null;
	}
}
