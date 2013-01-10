package com.mgs.fantasi.views;

import com.mgs.fantasi.wireframe.GridWireframe;
import com.mgs.fantasi.wireframe.PlaceholderFactory;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.grid.CellContent;
import com.mgs.fantasi.wireframe.grid.CellContentGenerator;

import java.awt.*;

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
		CellContentGenerator<View> cellContentGenerator = new CellContentGenerator<View>() {
			@Override
			public CellContent<View> generateContentFor(int x, int y) {
				return CellContent.evenlyDivided(contentBuilder, x, y);
			}
		};
		GridWireframe<View> grid = new GridWireframe<View>(placeholderFactory.gridPlaceholders(cellContentGenerator, new Dimension(numberOfDivisions, 1)));
		return grid.withDimension(numberOfDivisions, 1).withContent(cellContentGenerator);
	}

	public VerticalSlicesView withVerticalDivisions(int numberOVerticalDivisions) {
		this.numberOfDivisions = numberOVerticalDivisions;
		return this;
	}
}
