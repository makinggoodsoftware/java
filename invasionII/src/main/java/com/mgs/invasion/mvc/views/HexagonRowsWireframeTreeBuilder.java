package com.mgs.invasion.mvc.views;

import com.mgs.fantasi.properties.UIProperty;
import com.mgs.fantasi.properties.data.measurements.Fractions;
import com.mgs.fantasi.properties.data.measurements.Measurement;
import com.mgs.fantasi.properties.data.polygon.HexagonShape;
import com.mgs.fantasi.wireframe.CollocationInfo;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.WireframeContentFactory;
import com.mgs.fantasi.wireframeTreeBuilders.BaseWireframeTreeBuilder;
import com.mgs.fantasi.wireframeTreeBuilders.PijamaRowsWireframeTreeBuilder;
import com.mgs.fantasi.wireframeTreeBuilders.PolygonWireframeTreeBuilder;
import com.mgs.fantasi.wireframeTreeBuilders.WireframeTreeBuilder;
import com.mgs.tree.Tree;

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
	public Tree<Wireframe, CollocationInfo> build(WireframeContentFactory wireframeContentFactory) {
		return pijamaRows.build(wireframeContentFactory);
	}

	public WireframeTreeBuilder withHexagonMeasurement(UIProperty<Measurement> hexagonMeasurement) {
		hexagon.withMeasurement(hexagonMeasurement);
		return this;
	}
}
