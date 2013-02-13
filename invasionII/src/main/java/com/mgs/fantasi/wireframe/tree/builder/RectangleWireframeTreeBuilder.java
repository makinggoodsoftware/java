package com.mgs.fantasi.wireframe.tree.builder;

import com.mgs.fantasi.wireframe.CollocationInfo;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.tree.WireframeTree;

import static com.mgs.fantasi.properties.data.measurements.Fractions.all;
import static com.mgs.fantasi.wireframe.tree.WireframeTreeFactory.empty;
import static com.mgs.fantasi.wireframe.tree.WireframeTreeFactory.rectangle;

public class RectangleWireframeTreeBuilder extends BaseWireframeTreeBuilder<RectangleWireframeTreeBuilder> {
	private WireframeTreeBuilder content;

	public RectangleWireframeTreeBuilder() {
	}

	public RectangleWireframeTreeBuilder withContent(WireframeTreeBuilder content) {
		this.content = content;
		return this;
	}

	@Override
	public WireframeTree build() {
		Wireframe wireframe = new Wireframe(getUiPropertiesBuilder().build());
		if (content == null) {
			return empty(wireframe, getName(), this.getClass());
		}
		WireframeTree wireframeTree = rectangle(wireframe, getName(), this.getClass());
		wireframeTree.addChild(new CollocationInfo(0, all(), all(), 0, 0), content.build());
		return wireframeTree;
	}
}