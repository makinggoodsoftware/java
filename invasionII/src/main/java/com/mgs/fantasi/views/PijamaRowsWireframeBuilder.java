package com.mgs.fantasi.views;

import com.mgs.fantasi.properties.measurements.Fraction;
import com.mgs.fantasi.properties.measurements.Fractions;
import com.mgs.fantasi.wireframe.TwoDimensionsIterator;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.WireframeChildElement;
import com.mgs.fantasi.wireframe.WireframeFactory;

import java.awt.*;

public class PijamaRowsWireframeBuilder extends BaseWireframeBuilder<PijamaRowsWireframeBuilder> {
	private static final int UNDEFINED = -1;
	private final TwoLinesWireframeBuilder generationBuilder;
	private int numberOfGenerations = UNDEFINED;

	private PijamaRowsWireframeBuilder(WireframeBuilder firstRowBuilder, WireframeBuilder secondRowBuilder) {
		generationBuilder = new TwoLinesWireframeBuilder(firstRowBuilder, secondRowBuilder);
	}

	public static PijamaRowsWireframeBuilder pijamaRows(WireframeBuilder firstRowBuilder, WireframeBuilder secondRowBuilder) {
		return new PijamaRowsWireframeBuilder(firstRowBuilder, secondRowBuilder);
	}

	public PijamaRowsWireframeBuilder withFirstRowSize(Fraction sizeConstraints) {
		generationBuilder.withFirstRowSize(sizeConstraints);
		return this;
	}

	public PijamaRowsWireframeBuilder withNumberOfGenerations(int numberOfGenerations) {
		this.numberOfGenerations = numberOfGenerations;
		return this;
	}

	@Override
	public Wireframe build(final WireframeFactory wireframeFactory) {
		return wireframeFactory.createGridWireframe(new TwoDimensionsIterator<WireframeChildElement>() {
			@Override
			public WireframeChildElement on(int x, int y) {
				return new WireframeChildElement(generationBuilder.build(wireframeFactory), 0, Fractions.all(), Fractions.all(), x, y);
			}
		}, new Dimension(1, numberOfGenerations), getUiProperties(), getName(), this.getClass());
	}
}
