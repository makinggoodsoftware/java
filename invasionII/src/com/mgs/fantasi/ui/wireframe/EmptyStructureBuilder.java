package com.mgs.fantasi.ui.wireframe;

public class EmptyStructureBuilder<T extends Structurable> implements StructureBuilder <T>
{
	@Override
	public Structure<T> build() {
		return new EmptyStructure<T>();
	}

	@Override
	public <Z extends Structurable> StructureBuilder<Z> transform(MyRenderer.StructureBuilderTransformer<T, Z> tzStructureBuilderTransformer) {
		return new EmptyStructureBuilder<Z>();
	}
}
