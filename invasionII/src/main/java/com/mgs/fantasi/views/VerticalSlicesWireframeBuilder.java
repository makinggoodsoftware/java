package com.mgs.fantasi.views;

import com.mgs.fantasi.wireframe.Placeholder;
import com.mgs.fantasi.wireframe.TwoDimensionsIterator;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.WireframeFactory;

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
	public Wireframe<Wireframe> build(final WireframeFactory<Wireframe> wireframeFactory) {
		TwoDimensionsIterator<Placeholder<Wireframe>> cellContentGenerator = new TwoDimensionsIterator<Placeholder<Wireframe>>() {
			@Override
			public Placeholder<Wireframe> on(int x, int y) {
				return new Placeholder<Wireframe>(contentBuilder.build(wireframeFactory), 0, all(), all(), x, y);
			}
		};
		return wireframeFactory.createGridWireframe(cellContentGenerator, new Dimension(numberOfDivisions, 1), getUiProperties(), getName(), this.getClass());
	}

	public VerticalSlicesWireframeBuilder withVerticalDivisions(int numberOVerticalDivisions) {
		this.numberOfDivisions = numberOVerticalDivisions;
		return this;
	}
}
