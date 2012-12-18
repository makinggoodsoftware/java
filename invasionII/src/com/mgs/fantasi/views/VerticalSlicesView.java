package com.mgs.fantasi.views;

import com.mgs.fantasi.ui.wireframe.*;

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
	public boolean renderConstraintsAreSatisfied() {
		return numberOfDivisions != UNDEFINED;
	}

	@Override
	public StructureBuilder<View> getChildStructure() {
		GridStructureBuilder<View> grid = StructureType.grid();
		return grid.withDimension(numberOfDivisions, 1).withContent(new CellContentGenerator<View>() {
			@Override
			public CellContent<View> generateContentFor(int x, int y) {
				return CellContent.evenlyDivided(contentBuilder);
			}
		});
	}

	public VerticalSlicesView withVerticalDivisions (int numberOVerticalDivisions){
		this.numberOfDivisions = numberOVerticalDivisions;
		return this;
	}

	@Override
	public VerticalSlicesView copy() {
		return new VerticalSlicesView(contentBuilder.newCopy()).withVerticalDivisions(numberOfDivisions);
	}
}
