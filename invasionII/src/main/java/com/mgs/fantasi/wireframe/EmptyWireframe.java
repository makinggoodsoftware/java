package com.mgs.fantasi.wireframe;

public class EmptyWireframe<T> implements Wireframe<T> {
	@Override
	public WireframeType getType() {
		return WireframeType.EMPTY;
	}

	@Override
	public boolean isEmpty() {
		return true;
	}
}
