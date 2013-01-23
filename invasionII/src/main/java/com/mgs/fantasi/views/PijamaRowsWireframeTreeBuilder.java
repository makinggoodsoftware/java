package com.mgs.fantasi.views;

import com.mgs.fantasi.properties.measurements.Fraction;
import com.mgs.fantasi.properties.measurements.Fractions;
import com.mgs.fantasi.wireframe.*;

import java.awt.*;

public class PijamaRowsWireframeTreeBuilder extends BaseWireframeTreeBuilder<PijamaRowsWireframeTreeBuilder> {
	private static final int UNDEFINED = -1;
	private final TwoLinesWireframeTreeBuilder generationBuilder;
	private int numberOfGenerations = UNDEFINED;

	public PijamaRowsWireframeTreeBuilder(WireframeTreeBuilder firstRowTreeBuilder, WireframeTreeBuilder secondRowTreeBuilder) {
		generationBuilder = new TwoLinesWireframeTreeBuilder(firstRowTreeBuilder, secondRowTreeBuilder);
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
	public Tree<Wireframe, CollocationInfo> build(final WireframeContentFactory wireframeContentFactory) {
		return new Tree<Wireframe, CollocationInfo>
				(
						new Wireframe(this.getClass(), getName(), getUiProperties()),
						wireframeContentFactory.grid(new TwoDimensionsIterator<WireframeChildElement<Wireframe, CollocationInfo>>() {
							@Override
							public WireframeChildElement<Wireframe, CollocationInfo> on(int x, int y) {
								return new WireframeChildElement<Wireframe, CollocationInfo>(generationBuilder.build(wireframeContentFactory), 0, Fractions.all(), Fractions.all(), x, y);
							}
						}, new Dimension(1, numberOfGenerations)));
	}
}
