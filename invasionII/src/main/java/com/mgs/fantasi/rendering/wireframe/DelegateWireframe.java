package com.mgs.fantasi.rendering.wireframe;

public class DelegateWireframe implements Wireframe {
	private Wireframe content;

	@Override
	public WireframeType getType() {
		return WireframeType.DELEGATE;
	}

	public DelegateWireframe withContent(Wireframe content) {
		this.content = content;
		return this;
	}

    public Wireframe getContent() {
        return content;
    }
}
