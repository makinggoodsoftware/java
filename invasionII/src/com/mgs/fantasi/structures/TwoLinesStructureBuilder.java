package com.mgs.fantasi.structures;

import com.mgs.fantasi.ui.wireframe.CellContentGenerator;
import com.mgs.fantasi.ui.wireframe.Grid;
import com.mgs.fantasi.ui.wireframe.GridFactory;
import com.mgs.fantasi.ui.wireframe.Wireframe;

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
			public Wireframe generateContentFor(int x, int y) {
				if (y == 0){
					return firstLineBuilder.build();
				}else{
					return secondLineBuilder.build();
				}
			}
		});
		return layout;
	}

}
