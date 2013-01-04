package com.mgs.fantasi.wireframe;

public class DelegateWireframe implements Wireframe {
	private Wireframe content;

	@Override
	public WireframeType getType() {
		return WireframeType.DELEGATE;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	public DelegateWireframe withContent(Wireframe content) {
		this.content = content;
		return this;
	}

	public Wireframe getContent() {
		return content;
	}
}
