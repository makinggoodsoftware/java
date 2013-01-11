package com.mgs.fantasi.wireframe;

import java.util.List;

public class BaseWireframe<T> implements Wireframe<T> {
	private final List<Placeholder<T>> placeholders;

	public BaseWireframe(List<Placeholder<T>> placeholders) {
		this.placeholders = placeholders;
	}

	@Override
	public WireframeType getType() {
		return WireframeType.GRID;
	}

	@Override
	public boolean isEmpty() {
		return placeholders.size() == 0;
	}

	@Override
	public List<Placeholder<T>> getContent() {
		return placeholders;
	}
}
