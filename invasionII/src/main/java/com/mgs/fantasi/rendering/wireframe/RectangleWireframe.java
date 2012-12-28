package com.mgs.fantasi.rendering.wireframe;

import com.mgs.fantasi.Structurable;
import com.mgs.fantasi.rendering.ViewPreprocessorImpl;

public class RectangleWireframe<T extends Structurable> implements Wireframe<T> {
	private T content;

	public RectangleWireframe<T> withContent(T content) {
		if (content==null) throw new IllegalArgumentException("Content can't be null");
		this.content = content;
		return this;
	}

	@Override
	public <Z extends Structurable> Wireframe<Z> transform(ViewPreprocessorImpl.WireframeTransformer<T, Z> transformer) {
		RectangleWireframe<Z> emptyRectangle = new RectangleWireframe<Z>();
		if (content == null) return emptyRectangle;
		return emptyRectangle.withContent(transformer.transform(content));
	}

	@Override
	public WireframeType getType() {
		return WireframeType.SIMPLE;
	}

	public T getContent() {
		return content;
	}
}
