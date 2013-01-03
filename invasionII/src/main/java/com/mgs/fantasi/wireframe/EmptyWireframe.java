package com.mgs.fantasi.wireframe;

public class EmptyWireframe implements Wireframe {
	@Override
	public WireframeType getType() {
		return WireframeType.EMPTY;
	}
}
