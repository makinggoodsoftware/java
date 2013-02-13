package com.mgs.fantasi.wireframe.tree.builder;

import com.mgs.fantasi.wireframe.CollocationInfo;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.tree.WireframeTree;

import java.awt.*;

import static com.mgs.fantasi.properties.data.measurements.Fractions.all;
import static com.mgs.fantasi.wireframe.tree.WireframeTreeFactory.grid;

public class VerticalSlicesWireframeTreeBuilder extends BaseWireframeTreeBuilder {

	private static final int UNDEFINED = -1;
	private final WireframeTreeBuilder contentTreeBuilder;
	private int numberOfDivisions = UNDEFINED;

	public VerticalSlicesWireframeTreeBuilder(WireframeTreeBuilder contentTreeBuilder) {
		this.contentTreeBuilder = contentTreeBuilder;
	}

	@Override
	public WireframeTree build() {
		final Wireframe wireframe = new Wireframe(getUiPropertiesBuilder().build());
		WireframeTree wireframeTree = grid(wireframe, getName(), this.getClass());
		Dimension dimension = new Dimension(numberOfDivisions, 1);
		for (int x = 0; x < dimension.width; x++) {
			for (int y = 0; y < dimension.height; y++) {
				CollocationInfo collocationInfo = new CollocationInfo(0, all(), all(), x, y);
				WireframeTree child = contentTreeBuilder.build();
				wireframeTree.addChild(collocationInfo, child);
			}
		}
		return wireframeTree;
	}

	public VerticalSlicesWireframeTreeBuilder withVerticalDivisions(int numberOVerticalDivisions) {
		this.numberOfDivisions = numberOVerticalDivisions;
		return this;
	}
}
