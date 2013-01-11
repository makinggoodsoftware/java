package com.mgs.fantasi.views;

import com.mgs.fantasi.wireframe.GridWireframe;
import com.mgs.fantasi.wireframe.Placeholder;
import com.mgs.fantasi.wireframe.PlaceholderFactory;
import com.mgs.fantasi.wireframe.Wireframe;

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
	public Wireframe<View> buildContent(PlaceholderFactory placeholderFactory) {
		PlaceholderFactory.GridPlaceholderGenerator<View> cellContentGenerator = new PlaceholderFactory.GridPlaceholderGenerator<View>() {
			@Override
			public Placeholder<View> generateContentFor(int x, int y) {
				return new Placeholder<View>(contentBuilder, 0, all(), all(), x, y);
			}
		};
		GridWireframe<View> grid = new GridWireframe<View>(placeholderFactory.gridPlaceholders(cellContentGenerator, new Dimension(numberOfDivisions, 1)));
		return grid.withDimension(numberOfDivisions, 1);
	}

	public VerticalSlicesView withVerticalDivisions(int numberOVerticalDivisions) {
		this.numberOfDivisions = numberOVerticalDivisions;
		return this;
	}
}
