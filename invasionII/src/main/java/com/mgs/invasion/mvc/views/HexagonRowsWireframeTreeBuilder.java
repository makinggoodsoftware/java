package com.mgs.invasion.mvc.views;

import com.mgs.fantasi.properties.UIPropertiesBuilder;
import com.mgs.fantasi.properties.UIProperty;
import com.mgs.fantasi.properties.data.measurements.Fractions;
import com.mgs.fantasi.properties.data.measurements.Measurement;
import com.mgs.fantasi.properties.data.polygon.HexagonShape;
import com.mgs.fantasi.properties.data.polygon.NativeRectanguarShape;
import com.mgs.fantasi.wireframe.tree.WireframeTree;
import com.mgs.fantasi.wireframe.tree.builder.PijamaRowsWireframeTreeBuilder;
import com.mgs.fantasi.wireframe.tree.builder.RectangleWireframeTreeBuilder;
import com.mgs.fantasi.wireframe.tree.builder.WireframeTreeBuilder;

import static com.mgs.fantasi.properties.UIPropertiesBuilderFactory.rectangularEmpty;
import static com.mgs.fantasi.wireframe.Wireframes.*;

public class HexagonRowsWireframeTreeBuilder implements WireframeTreeBuilder {
	private final RectangleWireframeTreeBuilder hexagon;
	private final UIPropertiesBuilder uiPropertiesBuilder;
	private PijamaRowsWireframeTreeBuilder pijamaRows;
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
		this.pijamaRows = pijamaRows(
				getName() + "_pijama_rows",
				verticalSlices(getName() + "_odd", hexagon, rectangularEmpty()).withVerticalDivisions(numberOVerticalDivisions),
				rectangle(getName() + "_even", new NativeRectanguarShape(), rectangularEmpty()),
				rectangularEmpty()
		).
				withFirstRowSize(Fractions.thwoThirds()).
				withNumberOfGenerations(numberOfGenerations);
		return pijamaRows.build();
	}
}
