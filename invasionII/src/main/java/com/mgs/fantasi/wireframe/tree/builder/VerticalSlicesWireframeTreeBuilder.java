package com.mgs.fantasi.wireframe.tree.builder;

import com.mgs.fantasi.properties.UIPropertiesBuilder;
import com.mgs.fantasi.wireframe.CollocationInfo;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.tree.WireframeTree;

import java.awt.*;

import static com.mgs.fantasi.properties.data.measurements.Fractions.all;
import static com.mgs.fantasi.wireframe.tree.WireframeTreeFactory.grid;

public class VerticalSlicesWireframeTreeBuilder implements WireframeTreeBuilder {
	private static final int UNDEFINED = -1;
	private final WireframeTreeBuilder contentTreeBuilder;
	private int numberOfDivisions = UNDEFINED;
	private final UIPropertiesBuilder uiPropertiesBuilder;
	private final String name;

	public VerticalSlicesWireframeTreeBuilder(String name, WireframeTreeBuilder contentTreeBuilder, UIPropertiesBuilder uiPropertiesBuilder) {
		this.name = name;
		this.contentTreeBuilder = contentTreeBuilder;
		this.uiPropertiesBuilder = uiPropertiesBuilder;
	}

	@Override
	public UIPropertiesBuilder getUiPropertiesBuilder() {
		return uiPropertiesBuilder;
	}

	@Override
	public String getName() {
		return name;
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
