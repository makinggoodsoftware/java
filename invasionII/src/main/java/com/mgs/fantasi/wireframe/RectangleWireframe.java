package com.mgs.fantasi.wireframe;

public class RectangleWireframe<T> implements Wireframe<T> {
	private T content;

	public RectangleWireframe<T> withContent(T content) {
		if (content == null) throw new IllegalArgumentException("Content can't be null");
		this.content = content;
		return this;
	}

	@Override
	public WireframeType getType() {
		return WireframeType.SIMPLE;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	public T getContent() {
		return content;
	}
}
