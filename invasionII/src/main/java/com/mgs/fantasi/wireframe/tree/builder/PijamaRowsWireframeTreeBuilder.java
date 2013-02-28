package com.mgs.fantasi.wireframe.tree.builder;

import com.mgs.fantasi.properties.UIPropertiesBuilder;
import com.mgs.fantasi.properties.data.measurements.Fraction;
import com.mgs.fantasi.properties.data.measurements.Fractions;
import com.mgs.fantasi.properties.data.polygon.PolygonPointsIterator;
import com.mgs.fantasi.wireframe.CollocationInfo;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.tree.WireframeTree;

import java.awt.*;

import static com.mgs.fantasi.properties.UIPropertiesBuilderFactory.rectangularEmpty;
import static com.mgs.fantasi.wireframe.Wireframes.twoLines;
import static com.mgs.fantasi.wireframe.tree.WireframeTreeFactory.grid;

public class PijamaRowsWireframeTreeBuilder implements WireframeTreeBuilder {
	private static final int UNDEFINED = -1;
	private final TwoLinesWireframeTreeBuilder generationBuilder;
	private int numberOfGenerations = UNDEFINED;
	private final String name;
	private final PolygonPointsIterator shape;
	private final UIPropertiesBuilder uiPropertiesBuilder;

	public PijamaRowsWireframeTreeBuilder(String name, WireframeTreeBuilder firstRowTreeBuilder, WireframeTreeBuilder secondRowTreeBuilder, PolygonPointsIterator shape, UIPropertiesBuilder uiPropertiesBuilder) {
		this.name = name;
		this.shape = shape;
		this.uiPropertiesBuilder = uiPropertiesBuilder;
		generationBuilder = twoLines(getName() + "_two_lines", firstRowTreeBuilder, secondRowTreeBuilder, rectangularEmpty());
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
	public UIPropertiesBuilder getUiPropertiesBuilder() {
		return uiPropertiesBuilder;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public WireframeTree build() {
		final Wireframe wireframe = new Wireframe(getUiPropertiesBuilder().build(), shape);
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
