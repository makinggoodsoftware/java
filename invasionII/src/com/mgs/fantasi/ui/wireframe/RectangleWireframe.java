package com.mgs.fantasi.ui.wireframe;

public class RectangleWireframe<T extends Structurable> implements Wireframe<T> {
	private T content;

	public RectangleWireframe<T> withContent(T content) {
		if (content==null) throw new IllegalArgumentException("Content can't be null");
		this.content = content;
		return this;
	}

	@Override
	public Structure<T> build() {
		return new SimpleStructure<T>(content);
	}

	@Override
	public <Z extends Structurable> Wireframe<Z> transformContent(MyViewPreprocessor.WireframeTransformer<T, Z> transformer) {
		RectangleWireframe<Z> emptyRectangle = new RectangleWireframe<Z>();
		if (content == null) return emptyRectangle;
		return emptyRectangle.withContent(transformer.transform(content));
	}
}
