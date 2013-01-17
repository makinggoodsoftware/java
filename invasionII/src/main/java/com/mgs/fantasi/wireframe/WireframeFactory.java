package com.mgs.fantasi.wireframe;

import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.views.WireframeBuilder;

import java.awt.*;
import java.util.List;

public class WireframeFactory {
	private final WireframeContentFactory wireframeContentFactory;

	public WireframeFactory(WireframeContentFactory wireframeContentFactory) {
		this.wireframeContentFactory = wireframeContentFactory;
	}

	public Wireframe createEmptyWireframe(UIProperties uiProperties, String name, Class<? extends WireframeBuilder> builderClass) {
		return new Wireframe(wireframeContentFactory.empty(), uiProperties, name, builderClass);
	}

	public Wireframe createRectangleWireframe(Wireframe content, UIProperties uiProperties, String name, Class<? extends WireframeBuilder> builderClass) {
		if (content == null) return createEmptyWireframe(uiProperties, name, builderClass);
		return new Wireframe(wireframeContentFactory.rectangle(content), uiProperties, name, builderClass);
	}

	public Wireframe createLayeredWireframe(List<Wireframe> layers, UIProperties uiProperties, String name, Class<? extends WireframeBuilder> builderClass) {
		return new Wireframe(wireframeContentFactory.layered(layers), uiProperties, name, builderClass);
	}

	public Wireframe createGridWireframe(TwoDimensionsIterator<WireframeChildElement> twoDimensionsIterator, Dimension dimension, UIProperties uiProperties, String name, Class<? extends WireframeBuilder> builderClass) {
		return new Wireframe(wireframeContentFactory.grid(twoDimensionsIterator, dimension), uiProperties, name, builderClass);
	}
}
