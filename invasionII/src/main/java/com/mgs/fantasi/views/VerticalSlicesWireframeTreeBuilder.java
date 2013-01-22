package com.mgs.fantasi.views;

import com.mgs.fantasi.wireframe.*;

import java.awt.*;

import static com.mgs.fantasi.properties.measurements.Fractions.all;

public class VerticalSlicesWireframeTreeBuilder extends BaseWireframeTreeBuilder {

	private static final int UNDEFINED = -1;
	private final WireframeTreeBuilder contentTreeBuilder;
	private int numberOfDivisions = UNDEFINED;

	public VerticalSlicesWireframeTreeBuilder(WireframeTreeBuilder contentTreeBuilder) {
		this.contentTreeBuilder = contentTreeBuilder;
	}

	@Override
	public Tree<Wireframe> build(final WireframeContentFactory wireframeContentFactory) {
		TwoDimensionsIterator<WireframeChildElement<Wireframe>> cellContentGenerator = new TwoDimensionsIterator<WireframeChildElement<Wireframe>>() {
			@Override
			public WireframeChildElement<Wireframe> on(int x, int y) {
				return new WireframeChildElement<Wireframe>(contentTreeBuilder.build(wireframeContentFactory), 0, all(), all(), x, y);
			}
		};
		return new Tree<Wireframe>
				(
						new Wireframe(this.getClass(), getName(), getUiProperties()),
						wireframeContentFactory.grid(cellContentGenerator, new Dimension(numberOfDivisions, 1))
				);
	}

	public VerticalSlicesWireframeTreeBuilder withVerticalDivisions(int numberOVerticalDivisions) {
		this.numberOfDivisions = numberOVerticalDivisions;
		return this;
	}
}
