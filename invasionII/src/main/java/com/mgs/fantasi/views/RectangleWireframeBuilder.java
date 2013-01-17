package com.mgs.fantasi.views;

import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.WireframeContentFactory;

public class RectangleWireframeBuilder extends BaseWireframeBuilder<RectangleWireframeBuilder> {
	private WireframeBuilder content;

	public RectangleWireframeBuilder() {
	}

	public RectangleWireframeBuilder withContent(WireframeBuilder content) {
		this.content = content;
		return this;
	}

	@Override
	public Wireframe build(WireframeContentFactory wireframeContentFactory) {
		if (content == null)
			return new Wireframe(wireframeContentFactory.empty(), getUiProperties(), getName(), getClass());
		return new Wireframe(wireframeContentFactory.rectangle(content.build(wireframeContentFactory)), getUiProperties(), getName(), this.getClass());
	}
}