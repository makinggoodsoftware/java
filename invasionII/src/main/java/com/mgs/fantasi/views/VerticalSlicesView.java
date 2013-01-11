package com.mgs.fantasi.views;

import com.mgs.fantasi.wireframe.Placeholder;
import com.mgs.fantasi.wireframe.TwoDimensionsIterator;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.WireframeFactory;

import java.awt.*;

import static com.mgs.fantasi.properties.measurements.Fractions.all;

public class VerticalSlicesView extends BaseView {

	private static final int UNDEFINED = -1;
	private final View contentBuilder;
	private int numberOfDivisions = UNDEFINED;

	private VerticalSlicesView(View contentBuilder) {
		this.contentBuilder = contentBuilder;
	}

	public static VerticalSlicesView verticalSlices(View contentBuilder) {
		return new VerticalSlicesView(contentBuilder);
	}

	@Override
	public Wireframe<View> buildLayout(WireframeFactory<View> wireframeFactory) {
		TwoDimensionsIterator<Placeholder<View>> cellContentGenerator = new TwoDimensionsIterator<Placeholder<View>>() {
			@Override
			public Placeholder<View> on(int x, int y) {
				return new Placeholder<View>(contentBuilder, 0, all(), all(), x, y);
			}
		};
		return wireframeFactory.createGridWireframe(cellContentGenerator, new Dimension(numberOfDivisions, 1));
	}

	public VerticalSlicesView withVerticalDivisions(int numberOVerticalDivisions) {
		this.numberOfDivisions = numberOVerticalDivisions;
		return this;
	}
}
