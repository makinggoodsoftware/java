package com.mgs.fantasi.views;

import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.WireframeContentFactory;
import com.mgs.fantasi.wireframe.WireframeTree;

public class RectangleWireframeBuilder extends BaseWireframeBuilder<RectangleWireframeBuilder> {
	private WireframeBuilder content;

	public RectangleWireframeBuilder() {
	}

	public RectangleWireframeBuilder withContent(WireframeBuilder content) {
		this.content = content;
		return this;
	}

	@Override
	public WireframeTree build(WireframeContentFactory wireframeContentFactory) {
		Wireframe wireframe = new Wireframe(this.getClass(), getName(), getUiProperties());
		if (content == null)
			return new WireframeTree(wireframe, wireframeContentFactory.empty());
		return new WireframeTree(wireframe, wireframeContentFactory.rectangle(content.build(wireframeContentFactory)));
	}
}