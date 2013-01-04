package com.mgs.fantasi.wireframe;

import com.mgs.fantasi.views.View;

public class RectangleWireframe implements Wireframe {
	private View content;

	public RectangleWireframe withContent(View content) {
		if (content == null) throw new IllegalArgumentException("Content can't be null");
		this.content = content;
		return this;
	}

	@Override
	public WireframeType getType() {
		return WireframeType.SIMPLE;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	public View getContent() {
		return content;
	}
}
