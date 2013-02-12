package com.mgs.fantasi.wireframe.builder;

import com.mgs.fantasi.wireframe.CollocationInfo;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.WireframeContainer;

import static com.mgs.fantasi.properties.data.measurements.Fractions.all;
import static com.mgs.fantasi.wireframe.WireframeContainerFactory.empty;
import static com.mgs.fantasi.wireframe.WireframeContainerFactory.rectangle;

public class RectangleWireframeTreeBuilder extends BaseWireframeTreeBuilder<RectangleWireframeTreeBuilder> {
	private WireframeTreeBuilder content;

	public RectangleWireframeTreeBuilder() {
	}

	public RectangleWireframeTreeBuilder withContent(WireframeTreeBuilder content) {
		this.content = content;
		return this;
	}

	@Override
	public WireframeContainer build() {
		Wireframe wireframe = new Wireframe(this.getClass(), getName(), getUiPropertiesBuilder().build());
		if (content == null) {
			return empty(wireframe);
		}
		WireframeContainer wireframeContainer = rectangle(wireframe);
		wireframeContainer.addChild(new CollocationInfo(0, all(), all(), 0, 0), content.build());
		return wireframeContainer;
	}
}