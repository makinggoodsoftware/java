package com.mgs.fantasi.views;

import com.mgs.fantasi.wireframe.GridWireframe;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.grid.CellContent;
import com.mgs.fantasi.wireframe.grid.CellContentGenerator;

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
	public Wireframe<View> buildContent() {
		GridWireframe<View> grid = new GridWireframe<View>();
		return grid.withDimension(numberOfDivisions, 1).withContent(new CellContentGenerator<View>() {
			@Override
			public CellContent<View> generateContentFor(int x, int y) {
				return CellContent.evenlyDivided(contentBuilder);
			}
		});
	}

	public VerticalSlicesView withVerticalDivisions(int numberOVerticalDivisions) {
		this.numberOfDivisions = numberOVerticalDivisions;
		return this;
	}
}
