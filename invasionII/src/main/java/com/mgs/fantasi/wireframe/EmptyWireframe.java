package com.mgs.fantasi.wireframe;

import java.util.ArrayList;
import java.util.List;

public class EmptyWireframe<T> implements Wireframe<T> {
	@Override
	public WireframeType getType() {
		return WireframeType.EMPTY;
	}

	@Override
	public boolean isEmpty() {
		return true;
	}

	@Override
	public List<Placeholder<T>> getContent() {
		return new ArrayList<Placeholder<T>>();
	}
}
