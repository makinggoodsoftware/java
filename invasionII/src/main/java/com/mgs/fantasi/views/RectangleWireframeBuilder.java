package com.mgs.fantasi.views;

import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.WireframeFactory;

public class RectangleWireframeBuilder extends BaseWireframeBuilder<RectangleWireframeBuilder> {
	private WireframeBuilder content;

	private RectangleWireframeBuilder() {
	}

	public static RectangleWireframeBuilder rectangle() {
		return new RectangleWireframeBuilder();
	}

	public RectangleWireframeBuilder withContent(WireframeBuilder content) {
		this.content = content;
		return this;
	}

	@Override
	public Wireframe build(WireframeFactory wireframeFactory) {
		if (content == null) return wireframeFactory.createEmptyWireframe(getUiProperties(), getName(), getClass());
		return wireframeFactory.createRectangleWireframe(content.build(wireframeFactory), getUiProperties(), getName(), this.getClass());
	}
}