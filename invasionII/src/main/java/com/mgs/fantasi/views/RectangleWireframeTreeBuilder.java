package com.mgs.fantasi.views;

import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.WireframeContentFactory;
import com.mgs.fantasi.wireframe.WireframeTree;

public class RectangleWireframeTreeBuilder extends BaseWireframeTreeBuilder<RectangleWireframeTreeBuilder> {
	private WireframeTreeBuilder content;

	public RectangleWireframeTreeBuilder() {
	}

	public RectangleWireframeTreeBuilder withContent(WireframeTreeBuilder content) {
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