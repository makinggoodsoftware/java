package com.mgs.fantasi.wireframe;

import java.util.List;

public class Wireframe<T> {
	private final List<Placeholder<T>> placeholders;
	private final WireframeType type;

	public Wireframe(List<Placeholder<T>> placeholders, WireframeType type) {
		this.placeholders = placeholders;
		this.type = type;
	}

	public WireframeType getType() {
		return type;
	}

	public boolean isEmpty() {
		return placeholders.size() == 0;
	}

	public List<Placeholder<T>> getContent() {
		return placeholders;
	}
}
