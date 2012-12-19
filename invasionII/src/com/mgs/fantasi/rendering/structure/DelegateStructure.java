package com.mgs.fantasi.rendering.structure;

import com.mgs.fantasi.Structurable;

public class DelegateStructure<T extends Structurable> implements Structure<T> {
	private Structure<T> content;

	public DelegateStructure(Structure<T> content) {
		this.content = content;
	}

	public Structure<T> getContent() {
		return content;
	}

	@Override
	public StructureType getType() {
		return StructureType.DELEGATE;
	}
}
