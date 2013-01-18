package com.mgs.fantasi.views;

import com.mgs.fantasi.wireframe.*;

import java.awt.*;

import static com.mgs.fantasi.properties.measurements.Fractions.all;

public class VerticalSlicesWireframeBuilder extends BaseWireframeBuilder {

	private static final int UNDEFINED = -1;
	private final WireframeBuilder contentBuilder;
	private int numberOfDivisions = UNDEFINED;

	public VerticalSlicesWireframeBuilder(WireframeBuilder contentBuilder) {
		this.contentBuilder = contentBuilder;
	}

	@Override
	public WireframeTree build(final WireframeContentFactory wireframeContentFactory) {
		TwoDimensionsIterator<WireframeChildElement> cellContentGenerator = new TwoDimensionsIterator<WireframeChildElement>() {
			@Override
			public WireframeChildElement on(int x, int y) {
				return new WireframeChildElement(contentBuilder.build(wireframeContentFactory), 0, all(), all(), x, y);
			}
		};
		return new WireframeTree
				(
						new Wireframe(this.getClass(), getName(), getUiProperties()),
						wireframeContentFactory.grid(cellContentGenerator, new Dimension(numberOfDivisions, 1))
				);
	}

	public VerticalSlicesWireframeBuilder withVerticalDivisions(int numberOVerticalDivisions) {
		this.numberOfDivisions = numberOVerticalDivisions;
		return this;
	}
}
