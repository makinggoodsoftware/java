package com.mgs.fantasi.views;

import com.mgs.fantasi.properties.measurements.Fraction;
import com.mgs.fantasi.wireframe.GridWireframe;
import com.mgs.fantasi.wireframe.PlaceholderFactory;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.grid.CellContent;
import com.mgs.fantasi.wireframe.grid.CellContentGenerator;

import java.awt.*;

public class PijamaRowsView extends BaseView<PijamaRowsView> {
	private static final int UNDEFINED = -1;
	private final TwoLinesView generationBuilder;
	private int numberOfGenerations = UNDEFINED;

	private PijamaRowsView(View firstRowBuilder, View secondRowBuilder) {
		generationBuilder = new TwoLinesView(firstRowBuilder, secondRowBuilder);
	}

	public static PijamaRowsView pijamaRows(View firstRowBuilder, View secondRowBuilder) {
		return new PijamaRowsView(firstRowBuilder, secondRowBuilder);
	}

	public PijamaRowsView withFirstRowSize(Fraction sizeConstraints) {
		generationBuilder.withFirstRowSize(sizeConstraints);
		return this;
	}

	public PijamaRowsView withNumberOfGenerations(int numberOfGenerations) {
		this.numberOfGenerations = numberOfGenerations;
		return this;
	}

	@Override
	public Wireframe<View> buildContent(PlaceholderFactory placeholderFactory) {
		CellContentGenerator<View> cellContentGenerator = new CellContentGenerator<View>() {
			@Override
			public CellContent<View> generateContentFor(int x, int y) {
				return CellContent.evenlyDivided((View) generationBuilder, x, y);
			}
		};
		return new GridWireframe<View>(placeholderFactory.gridPlaceholders(cellContentGenerator, new Dimension(1, numberOfGenerations))).
				withDimension(1, numberOfGenerations).
				withContent(cellContentGenerator);
	}
}
