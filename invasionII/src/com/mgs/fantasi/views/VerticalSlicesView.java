package com.mgs.fantasi.views;

import com.mgs.fantasi.ui.wireframe.*;

public class VerticalSlicesView extends BaseView {

	private static final int UNDEFINED = -1;
	private final StructureBuilder contentBuilder;
	private int numberOfDivisions = UNDEFINED;

	private VerticalSlicesView(StructureBuilder contentBuilder) {
		this.contentBuilder = contentBuilder;
	}

	public static VerticalSlicesView verticalSlices(StructureBuilder contentBuilder) {
		return new VerticalSlicesView(contentBuilder);
	}

	@Override
	protected boolean constraintsAreSatisfied() {
		if (numberOfDivisions == UNDEFINED) return false;
		return true;
	}

	@Override
	protected Structure buildLayoutAndChilds() {
		Grid<Wireframe> layout = GridFactory.withDimensions(numberOfDivisions, 1);
		layout.fillCells(new CellContentGenerator<Wireframe>() {
			@Override
			public CellContent<Wireframe> generateContentFor(int x, int y) {
				return CellContent.evenlyDivided(contentBuilder.build());
			}
		});
		return layout;
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
