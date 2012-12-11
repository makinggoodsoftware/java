package com.mgs.fantasi.views;

import com.mgs.fantasi.measurements.Fractions;
import com.mgs.fantasi.measurements.Measurement;
import com.mgs.fantasi.polygon.HexagonShape;
import com.mgs.fantasi.ui.wireframe.SimpleStructure;
import com.mgs.fantasi.ui.wireframe.Structure;
import com.mgs.fantasi.ui.wireframe.Wireframe;

import static com.mgs.fantasi.views.PijamaRowsView.pijamaRows;
import static com.mgs.fantasi.views.PolygonView.polygon;
import static com.mgs.fantasi.views.RectangleView.emptyRectangle;
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
			emptyRectangle()
		).
		withFirstRowSize(Fractions.thwoThirds()).
		withNumberOfGerations(numberOfGerations);
	}

	public static HexagonRowsView newHexagonRows(int numberOVerticalDivisions, int numberOfGerations) {
		return new HexagonRowsView(numberOfGerations, numberOVerticalDivisions);
	}

	@Override
	protected boolean constraintsAreSatisfied() {
		return true;
	}

	@Override
	protected Structure buildLayoutAndChilds() {
		return new SimpleStructure<Wireframe>(pijamaRows.render());
	}

	@Override
	protected HexagonRowsView copy() {
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
