package com.mgs.fantasi.ui.wireframe;

public class DelegateWireframe<T extends Structurable> implements Wireframe<T> {
	private Wireframe<T> content;

	@Override
	public Structure<T> build() {
		return new DelegateStructure<T>(content.build());
	}

	@Override
	public <Z extends Structurable> Wireframe<Z> transformContent(MyRenderer.WireframeTransformer<T, Z> transformer) {
		return content.transformContent(transformer);
	}

	public DelegateWireframe<T> withContent(Wireframe<T> content) {
		this.content = content;
		return this;
	}
}
