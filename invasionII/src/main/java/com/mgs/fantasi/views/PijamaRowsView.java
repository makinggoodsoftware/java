package com.mgs.fantasi.views;

import com.mgs.fantasi.properties.measurements.Fraction;
import com.mgs.fantasi.wireframe.GridWireframe;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.grid.CellContent;
import com.mgs.fantasi.wireframe.grid.CellContentGenerator;

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

	public PijamaRowsView withFirstRowSize(Fraction sizeContraints) {
		generationBuilder.withFirstRowSize(sizeContraints);
		return this;
	}

	public PijamaRowsView withNumberOfGerations(int numberOfGerations) {
		numberOfGenerations = numberOfGerations;
		return this;
	}

	@Override
	public Wireframe buildContent() {
		return new GridWireframe().
				withDimension(1, numberOfGenerations).
				withContent(new CellContentGenerator() {
					@Override
					public CellContent generateContentFor(int x, int y) {
						return CellContent.evenlyDivided(generationBuilder);
					}
				});
	}
}
