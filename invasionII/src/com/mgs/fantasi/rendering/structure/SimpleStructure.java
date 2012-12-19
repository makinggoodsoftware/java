package com.mgs.fantasi.rendering.structure;

import com.mgs.fantasi.Structurable;

public class SimpleStructure<T extends Structurable> implements Structure<T> {
	private final T content;


	public SimpleStructure(T content) {
		this.content = content;
	}

	public T getContent() {
		return content;
	}

	@Override
	public StructureType getType() {
		return StructureType.SIMPLE;
	}
}
