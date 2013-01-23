package com.mgs.fantasi.wireframe;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static com.mgs.fantasi.properties.measurements.Fractions.all;

public class WireframeContentFactory {
	public Branch<Wireframe> empty() {
		return new Branch<Wireframe>(rejectNewChildren(), WireframeContentType.EMPTY);
	}

	private ConnectionManager<Wireframe> rejectNewChildren() {
		return new ConnectionManager<Wireframe>() {
			@Override
			public boolean accepts(WireframeChildElement<Wireframe> toBeAdded) {
				throw new RuntimeException("Can't accept content for an empty connector!");
			}
		};
	}

	public Branch<Wireframe> rectangle(Tree<Wireframe> content) {
		List<WireframeChildElement<Wireframe>> wireframeChildElements = new ArrayList<WireframeChildElement<Wireframe>>();
		wireframeChildElements.add(new WireframeChildElement<Wireframe>(content, 0, all(), all(), 0, 0));
		Branch<Wireframe> wireframeBranch = new Branch<Wireframe>(acceptNewChildren(), WireframeContentType.RECTANGLE);
		wireframeBranch.addChildren(wireframeChildElements);
		return wireframeBranch;
	}

	private ConnectionManager<Wireframe> acceptNewChildren() {
		return new ConnectionManager<Wireframe>() {
			@Override
			public boolean accepts(WireframeChildElement<Wireframe> toBeAdded) {
				return true;
			}
		};
	}

	public Branch<Wireframe> layered(List<Tree<Wireframe>> layers) {
		List<WireframeChildElement<Wireframe>> wireframeChildElements = new ArrayList<WireframeChildElement<Wireframe>>();
		for (int i = layers.size() - 1; i >= 0; i--) {
			Tree<Wireframe> layer = layers.get(i);
			wireframeChildElements.add(new WireframeChildElement<Wireframe>(layer, i, all(), all(), 0, 0));
		}
		Branch<Wireframe> wireframeBranch = new Branch<Wireframe>(acceptNewChildren(), WireframeContentType.LAYERS);
		wireframeBranch.addChildren(wireframeChildElements);
		return wireframeBranch;
	}

	public Branch<Wireframe> grid(TwoDimensionsIterator<WireframeChildElement<Wireframe>> twoDimensionsIterator, Dimension dimension) {
		List<WireframeChildElement<Wireframe>> wireframeChildElements = new ArrayList<WireframeChildElement<Wireframe>>();
		for (int x = 0; x < dimension.width; x++) {
			for (int y = 0; y < dimension.height; y++) {
				wireframeChildElements.add(twoDimensionsIterator.on(x, y));
			}
		}
		Branch<Wireframe> wireframeBranch = new Branch<Wireframe>(acceptNewChildren(), WireframeContentType.GRID);
		wireframeBranch.addChildren(wireframeChildElements);
		return wireframeBranch;
	}
}
