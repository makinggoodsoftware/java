package com.mgs.fantasi.wireframe;

import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.views.WireframeBuilder;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static com.mgs.fantasi.properties.measurements.Fractions.all;

public class WireframeFactory {
	public Wireframe createEmptyWireframe(UIProperties uiProperties, String name, Class<? extends WireframeBuilder> builderClass) {
		return new Wireframe(new ArrayList<WireframeChildElement>(), WireframeType.EMPTY, uiProperties, name, builderClass);
	}

	public Wireframe createRectangleWireframe(Wireframe content, UIProperties uiProperties, String name, Class<? extends WireframeBuilder> builderClass) {
		if (content == null) return createEmptyWireframe(uiProperties, name, builderClass);

		List<WireframeChildElement> wireframeChildElements = new ArrayList<WireframeChildElement>();
		wireframeChildElements.add(new WireframeChildElement(content, 0, all(), all(), 0, 0));
		return new Wireframe(wireframeChildElements, WireframeType.SIMPLE, uiProperties, name, builderClass);
	}

	public Wireframe createLayeredWireframe(List<Wireframe> layers, UIProperties uiProperties, String name, Class<? extends WireframeBuilder> builderClass) {
		List<WireframeChildElement> wireframeChildElements = new ArrayList<WireframeChildElement>();
		for (int i = layers.size() - 1; i >= 0; i--) {
			Wireframe layer = layers.get(i);
			wireframeChildElements.add(new WireframeChildElement(layer, i, all(), all(), 0, 0));
		}
		return new Wireframe(wireframeChildElements, WireframeType.LAYERS, uiProperties, name, builderClass);
	}

	public Wireframe createGridWireframe(TwoDimensionsIterator<WireframeChildElement> twoDimensionsIterator, Dimension dimension, UIProperties uiProperties, String name, Class<? extends WireframeBuilder> builderClass) {
		return new WireframeFactory().grid(twoDimensionsIterator, dimension, uiProperties, name, builderClass);
	}

	public Wireframe grid(TwoDimensionsIterator<WireframeChildElement> twoDimensionsIterator, Dimension dimension, UIProperties uiProperties, String name, Class<? extends WireframeBuilder> builderClass) {
		List<WireframeChildElement> wireframeChildElements = new ArrayList<WireframeChildElement>();
		for (int x = 0; x < dimension.width; x++) {
			for (int y = 0; y < dimension.height; y++) {
				wireframeChildElements.add(twoDimensionsIterator.on(x, y));
			}
		}
		return new Wireframe(wireframeChildElements, WireframeType.GRID, uiProperties, name, builderClass);
	}
}
