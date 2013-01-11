package com.mgs.fantasi.views;

import com.mgs.fantasi.properties.measurements.Fraction;
import com.mgs.fantasi.properties.measurements.Fractions;
import com.mgs.fantasi.wireframe.Placeholder;
import com.mgs.fantasi.wireframe.TwoDimensionsIterator;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.WireframeFactory;

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
	public Wireframe<View> buildLayout(WireframeFactory<View> wireframeFactory) {
		return wireframeFactory.createGridWireframe(new TwoDimensionsIterator<Placeholder<View>>() {
			@Override
			public Placeholder<View> on(int x, int y) {
				return new Placeholder<View>(generationBuilder, 0, Fractions.all(), Fractions.all(), x, y);
			}
		}, new Dimension(1, numberOfGenerations));
	}
}
