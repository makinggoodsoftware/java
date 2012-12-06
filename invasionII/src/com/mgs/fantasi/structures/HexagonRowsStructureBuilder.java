package com.mgs.fantasi.structures;

import com.mgs.fantasi.polygon.HexagonShape;
import com.mgs.fantasi.ui.wireframe.SimpleStructure;
import com.mgs.fantasi.ui.wireframe.Structure;
import com.mgs.fantasi.ui.wireframe.Wireframe;

import static com.mgs.fantasi.structures.PijamaRowsStructureBuilder.pijamaRows;
import static com.mgs.fantasi.structures.PolygonStructureBuilder.polygon;
import static com.mgs.fantasi.structures.RectangleStructureBuilder.emptyRectangle;
import static com.mgs.fantasi.structures.VerticalSlicesStructureBuilder.verticalSlices;

public class HexagonRowsStructureBuilder extends BaseStructureBuilder<HexagonRowsStructureBuilder>{
	private int numberOVerticalDivisions;
	private int numberOfGerations;
	private PijamaRowsStructureBuilder pijamaRows;

	public HexagonRowsStructureBuilder(int numberOfGerations, int numberOVerticalDivisions) {
		this.numberOfGerations = numberOfGerations;
		this.numberOVerticalDivisions = numberOVerticalDivisions;
		PolygonStructureBuilder hexagon = polygon(new HexagonShape());
		this.pijamaRows = pijamaRows(
			verticalSlices(hexagon).
					withVerticalDivisions(numberOVerticalDivisions)
			,
			emptyRectangle()
		).
		withFirstRowSize(Fractions.thwoThirds()).
		withNumberOfGerations(numberOfGerations);
	}

	public static HexagonRowsStructureBuilder newHexagonRows(int numberOVerticalDivisions, int numberOfGerations) {
		return new HexagonRowsStructureBuilder(numberOfGerations, numberOVerticalDivisions);
	}

	@Override
	protected boolean constraintsAreSatisfied() {
		return true;
	}

	@Override
	protected Structure buildLayoutAndChilds() {
		return new SimpleStructure<Wireframe>(pijamaRows.build());
	}

	public HexagonRowsStructureBuilder newOneColumnSmaller() {
		return new HexagonRowsStructureBuilder(numberOfGerations, numberOVerticalDivisions-1);
	}
}
