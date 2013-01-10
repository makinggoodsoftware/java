package com.mgs.fantasi.wireframe;

import java.util.List;

public class RectangleWireframe<T> implements Wireframe<T> {
	private T content;
	private final List<Placeholder<T>> rectanglePlaceholder;

	public RectangleWireframe(List<Placeholder<T>> rectanglePlaceholder) {
		this.rectanglePlaceholder = rectanglePlaceholder;
	}

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

	@Override
	public List<Placeholder<T>> getContent() {
		return rectanglePlaceholder;
	}

}
