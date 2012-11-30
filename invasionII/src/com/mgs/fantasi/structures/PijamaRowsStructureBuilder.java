package com.mgs.fantasi.structures;

import com.mgs.fantasi.ui.wireframe.CellContentGenerator;
import com.mgs.fantasi.ui.wireframe.Grid;
import com.mgs.fantasi.ui.wireframe.GridFactory;
import com.mgs.fantasi.ui.wireframe.Wireframe;

public class PijamaRowsStructureBuilder extends BaseStructureBuilder {
	private static final int UNDEFINED = -1;

	private final StructureBuilder mainRowBuilder;
	private final StructureBuilder emptyRowBuilder;

	private Fraction sizeConstraints = null;
	private int numberOfGenerations = UNDEFINED;

	public PijamaRowsStructureBuilder(StructureBuilder mainRowBuilder, StructureBuilder emptyRowBuilder) {
		this.mainRowBuilder = mainRowBuilder;
		this.emptyRowBuilder = emptyRowBuilder;
	}

	public PijamaRowsStructureBuilder withMainRowSize(Fraction sizeContraints){
		this.sizeConstraints = sizeContraints;
		return this;
	}

	public PijamaRowsStructureBuilder withNumberOfGerations (int numberOfGerations){
		this.numberOfGenerations = numberOfGerations;
		return this;
	}

	@Override
	protected boolean constraintsAreSatisfied() {
		if (sizeConstraints == null | numberOfGenerations == UNDEFINED) {
			return false;
		}
		return true;
	}

	@Override
	protected Grid<Wireframe> buildLayoutAndChilds() {
		int numOfRowsTotal = numberOfGenerations * 2;
		Grid<Wireframe> layout = GridFactory.withDimensions(1, numOfRowsTotal);
		layout.fillCells(new CellContentGenerator<Wireframe>() {
			@Override
			public Wireframe generateContentFor(int x, int y) {
				if (isRowOdd(y)){
					return mainRowBuilder.build();
				}else{
					return emptyRowBuilder.build();
				}
			}
		});
		return layout;
	}

	private boolean isRowOdd(int y) {
		return y % 2 == 0;
	}
}
