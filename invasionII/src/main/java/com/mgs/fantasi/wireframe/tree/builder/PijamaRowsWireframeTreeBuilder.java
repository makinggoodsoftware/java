package com.mgs.fantasi.wireframe.tree.builder;

import com.mgs.fantasi.properties.data.measurements.Fraction;
import com.mgs.fantasi.properties.data.measurements.Fractions;
import com.mgs.fantasi.wireframe.CollocationInfo;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.Wireframes;
import com.mgs.fantasi.wireframe.tree.WireframeTree;

import java.awt.*;

import static com.mgs.fantasi.wireframe.tree.WireframeTreeFactory.grid;

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
	public WireframeTree build() {
		final Wireframe wireframe = new Wireframe(getUiPropertiesBuilder().build());
		WireframeTree wireframeTree = grid(wireframe, getName(), this.getClass());
		Dimension dimension = new Dimension(1, numberOfGenerations);
		for (int x = 0; x < dimension.width; x++) {
			for (int y = 0; y < dimension.height; y++) {
				WireframeTree content = generationBuilder.build();
				CollocationInfo collocationInfo = new CollocationInfo(0, Fractions.all(), Fractions.all(), x, y);
				wireframeTree.addChild(collocationInfo, content);
			}
		}
		return wireframeTree;
	}
}
