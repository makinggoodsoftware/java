package com.mgs.fantasi.wireframe;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static com.mgs.fantasi.properties.measurements.Fractions.all;

public class WireframeContentFactory {
	public Branch<Wireframe> empty() {
		return new Branch<Wireframe>(emptyConnectionManager(), new ArrayList<WireframeChildElement<Wireframe>>(), WireframeContentType.EMPTY);
	}

	private ConnectionManager emptyConnectionManager() {
		return new ConnectionManager() {
		};
	}

	public Branch<Wireframe> rectangle(Tree<Wireframe> content) {
		List<WireframeChildElement<Wireframe>> wireframeChildElements = new ArrayList<WireframeChildElement<Wireframe>>();
		wireframeChildElements.add(new WireframeChildElement<Wireframe>(content, 0, all(), all(), 0, 0));
		return new Branch<Wireframe>(rectangleConnectionManager(), wireframeChildElements, WireframeContentType.RECTANGLE);
	}

	private ConnectionManager rectangleConnectionManager() {
		return new ConnectionManager() {
		};
	}

	public Branch<Wireframe> layered(List<Tree<Wireframe>> layers) {
		List<WireframeChildElement<Wireframe>> wireframeChildElements = new ArrayList<WireframeChildElement<Wireframe>>();
		for (int i = layers.size() - 1; i >= 0; i--) {
			Tree<Wireframe> layer = layers.get(i);
			wireframeChildElements.add(new WireframeChildElement<Wireframe>(layer, i, all(), all(), 0, 0));
		}
		return new Branch<Wireframe>(layeredConnectionManager(), wireframeChildElements, WireframeContentType.LAYERS);
	}

	private ConnectionManager layeredConnectionManager() {
		return new ConnectionManager() {
		};
	}

	public Branch<Wireframe> grid(TwoDimensionsIterator<WireframeChildElement<Wireframe>> twoDimensionsIterator, Dimension dimension) {
		List<WireframeChildElement<Wireframe>> wireframeChildElements = new ArrayList<WireframeChildElement<Wireframe>>();
		for (int x = 0; x < dimension.width; x++) {
			for (int y = 0; y < dimension.height; y++) {
				wireframeChildElements.add(twoDimensionsIterator.on(x, y));
			}
		}
		return new Branch<Wireframe>(gridConnectionManager(), wireframeChildElements, WireframeContentType.GRID);
	}

	private ConnectionManager gridConnectionManager() {
		return new ConnectionManager() {
		};
	}
}
