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
	public StructureFactory.StructureType getType() {
		return StructureFactory.StructureType.SIMPLE;
	}
}
