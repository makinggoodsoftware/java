package com.mgs.fantasi.views;

import com.mgs.fantasi.wireframe.CollocationInfo;
import com.mgs.fantasi.wireframe.Tree;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.WireframeContentFactory;

public class RectangleWireframeTreeBuilder extends BaseWireframeTreeBuilder<RectangleWireframeTreeBuilder> {
	private WireframeTreeBuilder content;

	public RectangleWireframeTreeBuilder() {
	}

	public RectangleWireframeTreeBuilder withContent(WireframeTreeBuilder content) {
		this.content = content;
		return this;
	}

	@Override
	public Tree<Wireframe, CollocationInfo> build(WireframeContentFactory wireframeContentFactory) {
		Wireframe wireframe = new Wireframe(this.getClass(), getName(), getUiProperties());
		if (content == null)
			return new Tree<Wireframe, CollocationInfo>(wireframe, wireframeContentFactory.empty());
		return new Tree<Wireframe, CollocationInfo>(wireframe, wireframeContentFactory.rectangle(content.build(wireframeContentFactory)));
	}
}