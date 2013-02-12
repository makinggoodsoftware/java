package com.mgs.invasion.mvc.views;

import com.mgs.fantasi.properties.UIProperty;
import com.mgs.fantasi.properties.data.measurements.Fractions;
import com.mgs.fantasi.properties.data.measurements.Measurement;
import com.mgs.fantasi.properties.data.polygon.HexagonShape;
import com.mgs.fantasi.wireframe.WireframeContainer;
import com.mgs.fantasi.wireframe.builder.BaseWireframeTreeBuilder;
import com.mgs.fantasi.wireframe.builder.PijamaRowsWireframeTreeBuilder;
import com.mgs.fantasi.wireframe.builder.PolygonWireframeTreeBuilder;
import com.mgs.fantasi.wireframe.builder.WireframeTreeBuilder;

import static com.mgs.fantasi.wireframe.Wireframes.*;

public class HexagonRowsWireframeTreeBuilder extends BaseWireframeTreeBuilder<HexagonRowsWireframeTreeBuilder> {
	private PijamaRowsWireframeTreeBuilder pijamaRows;
	private final PolygonWireframeTreeBuilder hexagon;

	public HexagonRowsWireframeTreeBuilder(int numberOfGenerations, int numberOVerticalDivisions) {
		hexagon = polygon(new HexagonShape());
		this.pijamaRows = pijamaRows(
				verticalSlices(hexagon).
						withVerticalDivisions(numberOVerticalDivisions)
				,
				rectangle()
		).
				withFirstRowSize(Fractions.thwoThirds()).
				withNumberOfGenerations(numberOfGenerations);
	}

	public static HexagonRowsWireframeTreeBuilder hexagonRows(int numberOVerticalDivisions, int numberOfGenerations) {
		return new HexagonRowsWireframeTreeBuilder(numberOfGenerations, numberOVerticalDivisions);
	}

	@Override
	public WireframeContainer build() {
		return pijamaRows.build();
	}

	public WireframeTreeBuilder withHexagonMeasurement(UIProperty<Measurement> hexagonMeasurement) {
		hexagon.withMeasurement(hexagonMeasurement);
		return this;
	}
}
