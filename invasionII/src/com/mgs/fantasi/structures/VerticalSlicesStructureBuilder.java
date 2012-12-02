package com.mgs.fantasi.structures;

import com.mgs.fantasi.ui.wireframe.*;

public class VerticalSlicesStructureBuilder extends BaseStructureBuilder {

	private static final int UNDEFINED = -1;
	private final StructureBuilder contentBuilder;
	private int numberOfDivisions = UNDEFINED;

	public VerticalSlicesStructureBuilder(StructureBuilder contentBuilder) {
		this.contentBuilder = contentBuilder;
	}

	@Override
	protected boolean constraintsAreSatisfied() {
		if (numberOfDivisions == UNDEFINED) return false;
		return true;
	}

	@Override
	protected Grid<Wireframe> buildLayoutAndChilds() {
		Grid<Wireframe> layout = GridFactory.withDimensions(numberOfDivisions, 1);
		layout.fillCells(new CellContentGenerator<Wireframe>() {
			@Override
			public CellContent<Wireframe> generateContentFor(int x, int y) {
				return CellContent.evenlyDivided(contentBuilder.build());
			}
		});
		return layout;
	}

	public VerticalSlicesStructureBuilder withVerticalDivisions (int numberOVerticalDivisions){
		this.numberOfDivisions = numberOVerticalDivisions;
		return this;
	}
}
