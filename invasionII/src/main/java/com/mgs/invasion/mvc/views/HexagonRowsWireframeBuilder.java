package com.mgs.invasion.mvc.views;

import com.mgs.fantasi.properties.measurements.Fractions;
import com.mgs.fantasi.properties.measurements.Measurement;
import com.mgs.fantasi.properties.polygon.HexagonShape;
import com.mgs.fantasi.views.BaseWireframeBuilder;
import com.mgs.fantasi.views.PijamaRowsWireframeBuilder;
import com.mgs.fantasi.views.PolygonWireframeBuilder;
import com.mgs.fantasi.views.WireframeBuilder;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.WireframeContentFactory;

import static com.mgs.fantasi.views.PijamaRowsWireframeBuilder.pijamaRows;
import static com.mgs.fantasi.views.PolygonWireframeBuilder.polygon;
import static com.mgs.fantasi.views.RectangleWireframeBuilder.rectangle;
import static com.mgs.fantasi.views.VerticalSlicesWireframeBuilder.verticalSlices;

public class HexagonRowsWireframeBuilder extends BaseWireframeBuilder<HexagonRowsWireframeBuilder> {
	private PijamaRowsWireframeBuilder pijamaRows;
	private final PolygonWireframeBuilder hexagon;

	public HexagonRowsWireframeBuilder(int numberOfGenerations, int numberOVerticalDivisions) {
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

	public static HexagonRowsWireframeBuilder hexagonRows(int numberOVerticalDivisions, int numberOfGenerations) {
		return new HexagonRowsWireframeBuilder(numberOfGenerations, numberOVerticalDivisions);
	}

	@Override
	public Wireframe build(WireframeContentFactory wireframeContentFactory) {
		return pijamaRows.build(wireframeContentFactory);
	}

	public WireframeBuilder withHexagonMeasurement(Measurement hexagonMeasurement) {
		hexagon.withMeasurement(hexagonMeasurement);
		return this;
	}
}
