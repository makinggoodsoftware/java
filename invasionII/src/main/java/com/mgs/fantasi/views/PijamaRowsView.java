package com.mgs.fantasi.views;

import com.mgs.fantasi.properties.measurements.Fraction;
import com.mgs.fantasi.rendering.structure.grid.CellContent;
import com.mgs.fantasi.rendering.structure.grid.CellContentGenerator;
import com.mgs.fantasi.rendering.wireframe.GridWireframe;
import com.mgs.fantasi.rendering.wireframe.Wireframe;

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
	public Wireframe<View> buildChildViews() {
		return new GridWireframe<View>().
			withDimension(1, numberOfGenerations).
			withContent(new CellContentGenerator<View>() {
				@Override
				public CellContent<View> generateContentFor(int x, int y) {
					return CellContent.evenlyDivided((View) generationBuilder);
				}
			});
	}

	@Override
	protected BaseView copySpecifics() {
		View firstLineBuilder = generationBuilder.getFirstLineBuilder();
		View secondLineBuilder = generationBuilder.getSecondLineBuilder();
		return new PijamaRowsView(firstLineBuilder.newCopy(), secondLineBuilder).withNumberOfGerations(numberOfGenerations);
	}

}
