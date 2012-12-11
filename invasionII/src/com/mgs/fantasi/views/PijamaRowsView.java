package com.mgs.fantasi.views;

import com.mgs.fantasi.measurements.Fraction;
import com.mgs.fantasi.ui.wireframe.*;

public class PijamaRowsView extends BaseView {
	private static final int UNDEFINED = -1;

	private final TwoLinesView generationBuilder;

	private int numberOfGenerations = UNDEFINED;

	private PijamaRowsView(View firstRowBuilder, View secondRowBuilder) {
		generationBuilder = new TwoLinesView(firstRowBuilder, secondRowBuilder);
	}

	public static PijamaRowsView pijamaRows(View firstRowBuilder, View secondRowBuilder) {
		return new PijamaRowsView(firstRowBuilder, secondRowBuilder);
	}

	public PijamaRowsView withFirstRowSize(Fraction sizeContraints){
		generationBuilder.withFirstRowSize(sizeContraints);
		return this;
	}

	public PijamaRowsView withNumberOfGerations (int numberOfGerations){
		numberOfGenerations = numberOfGerations;
		return this;
	}

	@Override
	public boolean constraintsAreSatisfied() {
		if (! generationBuilder.constraintsAreSatisfied()) return false;
		return numberOfGenerations != UNDEFINED;
	}

	@Override
	public Structure buildLayoutAndChilds() {
		Grid<Wireframe> layout = GridFactory.withDimensions(1, numberOfGenerations);
		layout.fillCells(new CellContentGenerator<Wireframe>() {
			@Override
			public CellContent<Wireframe> generateContentFor(int x, int y) {
				return CellContent.evenlyDivided(generationBuilder.render());
			}
		});
		return layout;
	}

	@Override
	protected BaseView copy() {
		View firstLineBuilder = generationBuilder.getFirstLineBuilder();
		View secondLineBuilder = generationBuilder.getSecondLineBuilder();
		return new PijamaRowsView(firstLineBuilder.newCopy(), secondLineBuilder).withNumberOfGerations(numberOfGenerations);
	}

}
