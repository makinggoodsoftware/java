package com.mgs.fantasi.wireframe.builder;

import com.mgs.fantasi.wireframe.CollocationInfo;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.WireframeContainer;
import com.mgs.fantasi.wireframe.WireframeContentFactory;

import static com.mgs.fantasi.properties.data.measurements.Fractions.all;

public class RectangleWireframeTreeBuilder extends BaseWireframeTreeBuilder<RectangleWireframeTreeBuilder> {
	private WireframeTreeBuilder content;

	public RectangleWireframeTreeBuilder() {
	}

	public RectangleWireframeTreeBuilder withContent(WireframeTreeBuilder content) {
		this.content = content;
		return this;
	}

	@Override
	public WireframeContainer build(WireframeContentFactory wireframeContentFactory) {
		Wireframe wireframe = new Wireframe(this.getClass(), getName(), getUiPropertiesBuilder().build());
		if (content == null) {
			return new WireframeContainer(wireframe, wireframeContentFactory.getEmptyConnectionManager());
		}
		WireframeContainer wireframeContainer = new WireframeContainer(wireframe, wireframeContentFactory.getRectangleConnectionManager());
		wireframeContainer.addChild(new CollocationInfo(0, all(), all(), 0, 0), content.build(wireframeContentFactory));
		return wireframeContainer;
	}
}