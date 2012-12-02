package com.mgs.fantasi.structures;

import com.mgs.fantasi.ui.wireframe.*;

public class TwoLinesStructureBuilder extends BaseStructureBuilder {

	private final StructureBuilder firstLineBuilder;
	private final StructureBuilder secondLineBuilder;

	private Fraction sizeConstraints = null;

	public TwoLinesStructureBuilder(StructureBuilder firstLineBuilder, StructureBuilder secondLineBuilder) {
		this.firstLineBuilder = firstLineBuilder;
		this.secondLineBuilder = secondLineBuilder;
	}

	public TwoLinesStructureBuilder withFirstRowSize(Fraction sizeContraints){
		this.sizeConstraints = sizeContraints;
		return this;
	}


	@Override
	protected boolean constraintsAreSatisfied() {
		return sizeConstraints != null;
	}

	@Override
	protected Grid<Wireframe> buildLayoutAndChilds() {
		Grid<Wireframe> layout = GridFactory.withDimensions(1, 2);
		layout.fillCells(new CellContentGenerator<Wireframe>() {
			@Override
			public CellContent<Wireframe> generateContentFor(int x, int y) {
				if (y == 0){
					return CellContent.evenlyDivided(firstLineBuilder.build());
				}else{
					return CellContent.evenlyDivided(secondLineBuilder.build());
				}
			}
		});
		return layout;
	}

}
