package com.mgs.fantasi.wireframe.tree.builder;

import com.mgs.fantasi.properties.UIPropertiesBuilder;
import com.mgs.fantasi.wireframe.tree.WireframeTree;

import java.awt.*;

public class PijamaRowsWireframeTreeBuilder implements WireframeTreeBuilder {
	private static final int UNDEFINED = -1;
	private final WireframeTreeBuilder generationBuilder;
	private int numberOfGenerations = UNDEFINED;
	private final String name;
	private final UIPropertiesBuilder uiPropertiesBuilder;

	public PijamaRowsWireframeTreeBuilder(String name, WireframeTreeBuilder twoLines, UIPropertiesBuilder uiPropertiesBuilder) {
		this.name = name;
		this.uiPropertiesBuilder = uiPropertiesBuilder;
		this.generationBuilder = twoLines;
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
		GridWireframeTreeBuilder gridWireframeTreeBuilder = new GridWireframeTreeBuilder(getName(), uiPropertiesBuilder);
		return gridWireframeTreeBuilder
				.withDimension(new Dimension(1, numberOfGenerations))
				.allCellsWith(generationBuilder)
				.build();
	}
}
