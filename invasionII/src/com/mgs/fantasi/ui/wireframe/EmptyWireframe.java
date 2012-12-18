package com.mgs.fantasi.ui.wireframe;

public class EmptyWireframe<T extends Structurable> implements Wireframe<T>
{
	@Override
	public Structure<T> build() {
		return new EmptyStructure<T>();
	}

	@Override
	public <Z extends Structurable> Wireframe<Z> transformContent(MyRenderer.WireframeTransformer<T, Z> tzWireframeTransformer) {
		return new EmptyWireframe<Z>();
	}
}
