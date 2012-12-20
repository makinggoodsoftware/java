package com.mgs.invasion.mvc.views;

import com.mgs.fantasi.properties.measurements.Fractions;
import com.mgs.fantasi.properties.measurements.Measurement;
import com.mgs.fantasi.properties.polygon.HexagonShape;
import com.mgs.fantasi.rendering.wireframe.DelegateWireframe;
import com.mgs.fantasi.rendering.wireframe.Wireframe;
import com.mgs.fantasi.views.BaseView;
import com.mgs.fantasi.views.PijamaRowsView;
import com.mgs.fantasi.views.PolygonView;
import com.mgs.fantasi.views.View;

import static com.mgs.fantasi.views.PijamaRowsView.pijamaRows;
import static com.mgs.fantasi.views.PolygonView.polygon;
import static com.mgs.fantasi.views.RectangleView.rectangle;
import static com.mgs.fantasi.views.VerticalSlicesView.verticalSlices;

public class HexagonRowsView extends BaseView<HexagonRowsView> {
	private int numberOVerticalDivisions;
	private int numberOfGerations;
	private PijamaRowsView pijamaRows;
	private final PolygonView hexagon;

	public HexagonRowsView(int numberOfGerations, int numberOVerticalDivisions) {
		this.numberOfGerations = numberOfGerations;
		this.numberOVerticalDivisions = numberOVerticalDivisions;
		hexagon = polygon(new HexagonShape());
		this.pijamaRows = pijamaRows(
			verticalSlices(hexagon).
					withVerticalDivisions(numberOVerticalDivisions)
			,
			rectangle()
		).
		withFirstRowSize(Fractions.thwoThirds()).
		withNumberOfGerations(numberOfGerations);
	}

	public static HexagonRowsView hexagonRows(int numberOVerticalDivisions, int numberOfGerations) {
		return new HexagonRowsView(numberOfGerations, numberOVerticalDivisions);
	}

	@Override
	public Wireframe<View> buildChildViews() {
		return new DelegateWireframe<View>().
				withContent(pijamaRows.buildChildViews());
	}

	@Override
	protected HexagonRowsView copySpecifics() {
		return new HexagonRowsView(numberOfGerations, numberOVerticalDivisions);
	}

	public HexagonRowsView withOneLessColumn() {
		this.numberOVerticalDivisions --;
		return this;
	}

	public View withHexagonMeasurement(Measurement hexagonMeasurement) {
		hexagon.withMeasurement(hexagonMeasurement);
		return this;
	}
}
