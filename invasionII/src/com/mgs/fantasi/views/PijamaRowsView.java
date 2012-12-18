package com.mgs.fantasi.views;

import com.mgs.fantasi.measurements.Fraction;
import com.mgs.fantasi.ui.wireframe.*;

import java.awt.*;

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
	public boolean renderConstraintsAreSatisfied() {
		return generationBuilder.renderConstraintsAreSatisfied() && numberOfGenerations != UNDEFINED;
	}

	@Override
	public Structure<View> getContent() {
		Grid<View> layout = GridFactory.withDimensions(1, numberOfGenerations);
		layout.fillCells(new CellContentGenerator<View>() {
			@Override
			public CellContent<View> generateContentFor(int x, int y) {
				return CellContent.evenlyDivided((View) generationBuilder);
			}
		});
		return layout;
	}

	@Override
	public StructureFactory.StructureType getContentStructureType() {
		return null;
	}

	@Override
	public StructureBuilder<View> getChildStructure() {
		return new GridStructureBuilder<View>().
				withDimension(1, numberOfGenerations).
				withContent(new CellContentGenerator<View>() {
					@Override
					public CellContent<View> generateContentFor(int x, int y) {
						return CellContent.evenlyDivided((View) generationBuilder);
					}
				});
	}

	@Override
	protected BaseView copy() {
		View firstLineBuilder = generationBuilder.getFirstLineBuilder();
		View secondLineBuilder = generationBuilder.getSecondLineBuilder();
		return new PijamaRowsView(firstLineBuilder.newCopy(), secondLineBuilder).withNumberOfGerations(numberOfGenerations);
	}

}
