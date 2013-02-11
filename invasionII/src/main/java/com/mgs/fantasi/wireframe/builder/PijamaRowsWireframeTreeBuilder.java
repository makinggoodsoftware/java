package com.mgs.fantasi.wireframe.builder;

import com.mgs.fantasi.properties.data.measurements.Fraction;
import com.mgs.fantasi.properties.data.measurements.Fractions;
import com.mgs.fantasi.wireframe.*;

import java.awt.*;

public class PijamaRowsWireframeTreeBuilder extends BaseWireframeTreeBuilder<PijamaRowsWireframeTreeBuilder> {
	private static final int UNDEFINED = -1;
	private final TwoLinesWireframeTreeBuilder generationBuilder;
	private int numberOfGenerations = UNDEFINED;

	public PijamaRowsWireframeTreeBuilder(WireframeTreeBuilder firstRowTreeBuilder, WireframeTreeBuilder secondRowTreeBuilder) {
		generationBuilder = Wireframes.twoLines(firstRowTreeBuilder, secondRowTreeBuilder);
	}

	public PijamaRowsWireframeTreeBuilder withFirstRowSize(Fraction sizeConstraints) {
		generationBuilder.withFirstRowSize(sizeConstraints);
		return this;
	}

	public PijamaRowsWireframeTreeBuilder withNumberOfGenerations(int numberOfGenerations) {
		this.numberOfGenerations = numberOfGenerations;
		return this;
	}

	@Override
	public WireframeContainer build(final WireframeContentFactory wireframeContentFactory) {
		Wireframe wireframe = new Wireframe(this.getClass(), getName(), getUiPropertiesBuilder().build());
		WireframeContainer wireframeContainer = new WireframeContainer(wireframe, wireframeContentFactory.getGridConnectionManager());
		Dimension dimension = new Dimension(1, numberOfGenerations);
		for (int x = 0; x < dimension.width; x++) {
			for (int y = 0; y < dimension.height; y++) {
				WireframeContainer content = generationBuilder.build(wireframeContentFactory);
				CollocationInfo collocationInfo = new CollocationInfo(0, Fractions.all(), Fractions.all(), x, y);
				wireframeContainer.addChild(collocationInfo, content);
			}
		}
		return wireframeContainer;
	}
}
