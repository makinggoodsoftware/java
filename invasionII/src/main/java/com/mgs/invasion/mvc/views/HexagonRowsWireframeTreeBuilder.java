package com.mgs.invasion.mvc.views;

import com.mgs.fantasi.properties.measurements.Fractions;
import com.mgs.fantasi.properties.measurements.Measurement;
import com.mgs.fantasi.properties.polygon.HexagonShape;
import com.mgs.fantasi.views.BaseWireframeTreeBuilder;
import com.mgs.fantasi.views.PijamaRowsWireframeTreeBuilder;
import com.mgs.fantasi.views.PolygonWireframeTreeBuilder;
import com.mgs.fantasi.views.WireframeTreeBuilder;
import com.mgs.fantasi.wireframe.WireframeContentFactory;
import com.mgs.fantasi.wireframe.WireframeTree;

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
	public WireframeTree build(WireframeContentFactory wireframeContentFactory) {
		return pijamaRows.build(wireframeContentFactory);
	}

	public WireframeTreeBuilder withHexagonMeasurement(Measurement hexagonMeasurement) {
		hexagon.withMeasurement(hexagonMeasurement);
		return this;
	}
}
