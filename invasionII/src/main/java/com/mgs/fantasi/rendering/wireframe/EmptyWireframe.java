package com.mgs.fantasi.rendering.wireframe;

public class EmptyWireframe implements Wireframe {
	@Override
	public WireframeType getType() {
		return WireframeType.EMPTY;
	}
}
