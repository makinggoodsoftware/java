package com.mgs.invasion.mvc.views;

import com.mgs.fantasi.properties.measurements.Fractions;
import com.mgs.fantasi.properties.measurements.Measurement;
import com.mgs.fantasi.properties.polygon.HexagonShape;
import com.mgs.fantasi.views.BaseView;
import com.mgs.fantasi.views.PijamaRowsView;
import com.mgs.fantasi.views.PolygonView;
import com.mgs.fantasi.views.View;
import com.mgs.fantasi.wireframe.Wireframe;

import static com.mgs.fantasi.views.PijamaRowsView.pijamaRows;
import static com.mgs.fantasi.views.PolygonView.polygon;
import static com.mgs.fantasi.views.RectangleView.rectangle;
import static com.mgs.fantasi.views.VerticalSlicesView.verticalSlices;

public class HexagonRowsView extends BaseView<HexagonRowsView> {
	private PijamaRowsView pijamaRows;
	private final PolygonView hexagon;

	public HexagonRowsView(int numberOfGerations, int numberOVerticalDivisions) {
		hexagon = polygon(new HexagonShape());
		this.pijamaRows = pijamaRows(
				verticalSlices(hexagon).
						withVerticalDivisions(numberOVerticalDivisions)
				,
				rectangle()
		).
				withFirstRowSize(Fractions.thwoThirds()).
				withNumberOfGenerations(numberOfGerations);
	}

	public static HexagonRowsView hexagonRows(int numberOVerticalDivisions, int numberOfGerations) {
		return new HexagonRowsView(numberOfGerations, numberOVerticalDivisions);
	}

	@Override
	public Wireframe<View> buildContent() {
		return pijamaRows.buildContent();
	}

	public View withHexagonMeasurement(Measurement hexagonMeasurement) {
		hexagon.withMeasurement(hexagonMeasurement);
		return this;
	}
}
