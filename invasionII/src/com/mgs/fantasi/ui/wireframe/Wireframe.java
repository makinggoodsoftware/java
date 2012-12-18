package com.mgs.fantasi.ui.wireframe;

public interface Wireframe<T extends Structurable> {
	Structure<T> build();

	<Z extends Structurable> Wireframe<Z> transformContent(MyRenderer.WireframeTransformer<T, Z> wireframeTransformer);
}
