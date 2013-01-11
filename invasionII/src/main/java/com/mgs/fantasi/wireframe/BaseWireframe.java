package com.mgs.fantasi.wireframe;

import com.mgs.fantasi.views.View;

import java.util.List;

public class BaseWireframe<T> implements Wireframe<T> {
	public BaseWireframe(List<Placeholder<View>> placeholders) {
	}

	@Override
	public WireframeType getType() {
		return null;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public List<Placeholder<T>> getContent() {
		return null;
	}
}
