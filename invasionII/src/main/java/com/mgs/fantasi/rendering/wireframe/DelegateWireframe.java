package com.mgs.fantasi.rendering.wireframe;

import com.mgs.fantasi.Structurable;

public class DelegateWireframe<T extends Structurable> implements Wireframe {
	private Wireframe content;

	@Override
	public WireframeType getType() {
		return WireframeType.DELEGATE;
	}

	public DelegateWireframe<T> withContent(Wireframe content) {
		this.content = content;
		return this;
	}

    public Wireframe getContent() {
        return content;
    }
}
