package com.mgs.fantasi.wireframe;

import java.awt.*;
import java.util.List;

import static com.mgs.fantasi.properties.measurements.Fractions.all;

public class WireframeContentFactory {
	public Branch<Wireframe, CollocationInfo> empty() {
		return new Branch<Wireframe, CollocationInfo>(rejectNewChildren(), WireframeContentType.EMPTY);
	}

	public Branch<Wireframe, CollocationInfo> rectangle(Tree<Wireframe, CollocationInfo> content) {
		Branch<Wireframe, CollocationInfo> wireframeBranch = new Branch<Wireframe, CollocationInfo>(acceptNewChildren(), WireframeContentType.RECTANGLE);
		wireframeBranch.addChild(new CollocationInfo(0, all(), all(), 0, 0), content);
		return wireframeBranch;
	}

	public Branch<Wireframe, CollocationInfo> layered(List<Tree<Wireframe, CollocationInfo>> layers) {
		Branch<Wireframe, CollocationInfo> wireframeBranch = new Branch<Wireframe, CollocationInfo>(acceptNewChildren(), WireframeContentType.LAYERS);
		for (int i = layers.size() - 1; i >= 0; i--) {
			Tree<Wireframe, CollocationInfo> layer = layers.get(i);
			wireframeBranch.addChild(new CollocationInfo(i, all(), all(), 0, 0), layer);
		}
		return wireframeBranch;
	}

	public Branch<Wireframe, CollocationInfo> grid(TwoDimensionsIterator<WireframeChildElement<Wireframe, CollocationInfo>> twoDimensionsIterator, Dimension dimension) {
		Branch<Wireframe, CollocationInfo> wireframeBranch = new Branch<Wireframe, CollocationInfo>(acceptNewChildren(), WireframeContentType.GRID);
		for (int x = 0; x < dimension.width; x++) {
			for (int y = 0; y < dimension.height; y++) {
				WireframeChildElement<Wireframe, CollocationInfo> childElementInfo = twoDimensionsIterator.on(x, y);
				wireframeBranch.addChild(childElementInfo.getCollocationInfo(), childElementInfo.getChild());
			}
		}
		return wireframeBranch;
	}

	private ConnectionManager<Wireframe, CollocationInfo> acceptNewChildren() {
		return new ConnectionManager<Wireframe, CollocationInfo>() {
			@Override
			public boolean accepts(CollocationInfo linkInfo, Tree<Wireframe, CollocationInfo> child) {
				return true;
			}
		};
	}

	private ConnectionManager<Wireframe, CollocationInfo> rejectNewChildren() {
		return new ConnectionManager<Wireframe, CollocationInfo>() {
			@Override
			public boolean accepts(CollocationInfo linkInfo, Tree<Wireframe, CollocationInfo> child) {
				throw new RuntimeException("Can't accept content for an empty connector!");
			}
		};
	}
}
