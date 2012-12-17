package com.mgs.fantasi.ui.wireframe;

public interface StructureBuilder<T extends Structurable> {
	Structure<T> build();

	<Z extends Structurable> StructureBuilder<Z> transform (MyRenderer.StructureBuilderTransformer<T, Z> structureBuilderTransformer);
}
