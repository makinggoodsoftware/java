package com.mgs.fantasi.ui.wireframe;

public class DelegateStructureBuilder<T extends Structurable> implements StructureBuilder<T>{
	private StructureBuilder<T> content;

	@Override
	public Structure<T> build() {
		return new DelegateStructure<T>(content.build());
	}

	@Override
	public <Z extends Structurable> StructureBuilder<Z> transform(MyRenderer.StructureBuilderTransformer<T, Z> transformer) {
		return content.transform(transformer);
	}

	public DelegateStructureBuilder<T> withContent(StructureBuilder<T> content) {
		this.content = content;
		return this;
	}
}
