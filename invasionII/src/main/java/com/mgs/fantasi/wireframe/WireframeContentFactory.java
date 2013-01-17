package com.mgs.fantasi.wireframe;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static com.mgs.fantasi.properties.measurements.Fractions.all;

public class WireframeContentFactory {
	public WireframeContent empty() {
		return new WireframeContent(new ArrayList<WireframeChildElement>(), WireframeContentType.EMPTY);
	}

	public WireframeContent rectangle(Wireframe content) {
		List<WireframeChildElement> wireframeChildElements = new ArrayList<WireframeChildElement>();
		wireframeChildElements.add(new WireframeChildElement(content, 0, all(), all(), 0, 0));
		return new WireframeContent(wireframeChildElements, WireframeContentType.RECTANGLE);
	}

	public WireframeContent layered(List<Wireframe> layers) {
		List<WireframeChildElement> wireframeChildElements = new ArrayList<WireframeChildElement>();
		for (int i = layers.size() - 1; i >= 0; i--) {
			Wireframe layer = layers.get(i);
			wireframeChildElements.add(new WireframeChildElement(layer, i, all(), all(), 0, 0));
		}
		return new WireframeContent(wireframeChildElements, WireframeContentType.LAYERS);
	}

	public WireframeContent grid(TwoDimensionsIterator<WireframeChildElement> twoDimensionsIterator, Dimension dimension) {
		List<WireframeChildElement> wireframeChildElements = new ArrayList<WireframeChildElement>();
		for (int x = 0; x < dimension.width; x++) {
			for (int y = 0; y < dimension.height; y++) {
				wireframeChildElements.add(twoDimensionsIterator.on(x, y));
			}
		}
		return new WireframeContent(wireframeChildElements, WireframeContentType.GRID);
	}
}
