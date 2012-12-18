package com.mgs.fantasi.ui.wireframe;

public class DelegateStructure<T extends Structurable> implements Structure<T> {
	private Structure<T> content;

	public DelegateStructure(Structure<T> content) {
		this.content = content;
	}

	public Structure<T> getContent() {
		return content;
	}

	@Override
	public StructureFactory.StructureType getType() {
		return StructureFactory.StructureType.DELEGATE;
	}
}
