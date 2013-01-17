package com.mgs.fantasi.views;

import com.mgs.fantasi.wireframe.TwoDimensionsIterator;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.WireframeChildElement;
import com.mgs.fantasi.wireframe.WireframeContentFactory;

import java.awt.*;

import static com.mgs.fantasi.properties.measurements.Fractions.all;

public class VerticalSlicesWireframeBuilder extends BaseWireframeBuilder {

	private static final int UNDEFINED = -1;
	private final WireframeBuilder contentBuilder;
	private int numberOfDivisions = UNDEFINED;

	private VerticalSlicesWireframeBuilder(WireframeBuilder contentBuilder) {
		this.contentBuilder = contentBuilder;
	}

	public static VerticalSlicesWireframeBuilder verticalSlices(WireframeBuilder contentBuilder) {
		return new VerticalSlicesWireframeBuilder(contentBuilder);
	}

	@Override
	public Wireframe build(final WireframeContentFactory wireframeContentFactory) {
		TwoDimensionsIterator<WireframeChildElement> cellContentGenerator = new TwoDimensionsIterator<WireframeChildElement>() {
			@Override
			public WireframeChildElement on(int x, int y) {
				return new WireframeChildElement(contentBuilder.build(wireframeContentFactory), 0, all(), all(), x, y);
			}
		};
		return new Wireframe(wireframeContentFactory.grid(cellContentGenerator, new Dimension(numberOfDivisions, 1)), getUiProperties(), getName(), this.getClass());
	}

	public VerticalSlicesWireframeBuilder withVerticalDivisions(int numberOVerticalDivisions) {
		this.numberOfDivisions = numberOVerticalDivisions;
		return this;
	}
}
