package com.mgs.invasion.mvc.views;

import com.mgs.fantasi.properties.UIPropertiesBuilder;
import com.mgs.fantasi.properties.UIProperty;
import com.mgs.fantasi.properties.data.measurements.Fractions;
import com.mgs.fantasi.properties.data.measurements.Measurement;
import com.mgs.fantasi.properties.data.polygon.HexagonShape;
import com.mgs.fantasi.properties.data.polygon.NativeRectanguarShape;
import com.mgs.fantasi.wireframe.tree.WireframeTree;
import com.mgs.fantasi.wireframe.tree.builder.SingleChildWireframeTreeBuilder;
import com.mgs.fantasi.wireframe.tree.builder.WireframeTreeBuilder;

import static com.mgs.fantasi.properties.UIPropertiesBuilderFactory.rectangularEmpty;
import static com.mgs.fantasi.wireframe.Wireframes.rectangle;
import static com.mgs.fantasi.wireframe.tree.builder.GridWireframeTreeBuilderFactory.*;

public class HexagonRowsWireframeTreeBuilder implements WireframeTreeBuilder {
	private final SingleChildWireframeTreeBuilder hexagon;
	private final UIPropertiesBuilder uiPropertiesBuilder;
	private final String name;
	private int numberOfGenerations;
	private int numberOVerticalDivisions;

	public static HexagonRowsWireframeTreeBuilder hexagonRows(String name, UIPropertiesBuilder uiPropertiesBuilder) {
		return new HexagonRowsWireframeTreeBuilder(uiPropertiesBuilder, name);
	}

	public HexagonRowsWireframeTreeBuilder(UIPropertiesBuilder uiPropertiesBuilder, String name) {
		this.uiPropertiesBuilder = uiPropertiesBuilder;
		this.name = name;
		hexagon = rectangle(name + "_polygon", new HexagonShape(), rectangularEmpty());
	}

	public HexagonRowsWireframeTreeBuilder withNumberOfGenerations(int numberOfGenerations) {
		this.numberOfGenerations = numberOfGenerations;
		return this;
	}

	public HexagonRowsWireframeTreeBuilder withNumberOfVerticalDivisions(int numberOfVerticalDivisions) {
		this.numberOVerticalDivisions = numberOfVerticalDivisions;
		return this;
	}

	public HexagonRowsWireframeTreeBuilder withHexagonSize(UIProperty<Measurement> hexagonMeasurement) {
		this.hexagon.getUiPropertiesBuilder().withMeasurement(hexagonMeasurement);
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
		return horizontalRepeater(
				getName() + "_pijama_rows",
				twoLines(
						getName() + "_generation",
						Fractions.thwoThirds(),
						verticalRepeater(getName() + "_hexagons", hexagon, numberOVerticalDivisions, rectangularEmpty()),
						rectangle(getName() + "_space", new NativeRectanguarShape(), rectangularEmpty()),
						rectangularEmpty()
				),
				numberOfGenerations,
				uiPropertiesBuilder
		).build();
	}
}
