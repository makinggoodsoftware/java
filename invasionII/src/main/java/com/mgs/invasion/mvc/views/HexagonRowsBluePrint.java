package com.mgs.invasion.mvc.views;

import com.mgs.fantasi.properties.UIPropertiesBuilder;
import com.mgs.fantasi.properties.UIProperty;
import com.mgs.fantasi.properties.data.measurements.Fractions;
import com.mgs.fantasi.properties.data.measurements.Measurement;
import com.mgs.fantasi.properties.data.polygon.HexagonShape;
import com.mgs.fantasi.structure.bluePrint.BluePrint;
import com.mgs.fantasi.structure.bluePrintPatterns.*;
import com.mgs.fantasi.wireframe.Wireframe;

import static com.mgs.fantasi.properties.UIPropertiesBuilderFactory.allEmptyUIProperties;
import static com.mgs.fantasi.structure.BluePrintPatternFactory.newBluePrintBuilder;
import static com.mgs.fantasi.wireframe.Wireframes.newRectangularWireframe;
import static com.mgs.fantasi.wireframe.Wireframes.newWireframe;

public class HexagonRowsBluePrint implements BluePrintPattern {
	private static final HexagonShape HEXAGON_SHAPE = new HexagonShape();
	private String name;
	private Wireframe allContainer;
	private final UIPropertiesBuilder twoLinesContainerUIProperties = allEmptyUIProperties();
	private final UIPropertiesBuilder hexagonRowsContainerUIProperties = allEmptyUIProperties();
	private final UIPropertiesBuilder spanBetweenHexagonRowsContainerUIProperties = allEmptyUIProperties();
	private final UIPropertiesBuilder hexagonContainerUIProperties = allEmptyUIProperties();

	private int numberOfGenerations;
	private int numberOVerticalDivisions;

	public HexagonRowsBluePrint withNumberOfGenerations(int numberOfGenerations) {
		this.numberOfGenerations = numberOfGenerations;
		return this;
	}

	public HexagonRowsBluePrint withNumberOfVerticalDivisions(int numberOfVerticalDivisions) {
		this.numberOVerticalDivisions = numberOfVerticalDivisions;
		return this;
	}

	public HexagonRowsBluePrint withHexagonSize(UIProperty<Measurement> hexagonMeasurement) {
		hexagonContainerUIProperties.withMeasurement(hexagonMeasurement);
		return this;
	}

	@Override
	public void initialise(String name, Wireframe wireframe) {
		this.name = name;
		this.allContainer = wireframe;
	}

	@Override
	public BluePrint buildBlueprint() {
		return
				newBluePrintBuilder("hexagonRows", new HorizontalRepeaterPattern()).
						withWireframe(allContainer).
						repeating(
								newBluePrintBuilder("linesOfHexagons", new TwoLinesPattern()).
										withWireframe(newRectangularWireframe(twoLinesContainerUIProperties)).
										withFirstLineHeightSizeRatio(Fractions.thwoThirds()).
										withFirstLineTreeBuilder(
												newBluePrintBuilder("hexagons", new VerticalRepeaterPattern()).
														withWireframe(newRectangularWireframe(hexagonRowsContainerUIProperties)).
														repeating(
																newBluePrintBuilder("hexagon", new EmptyRectanglePattern()).
																		withWireframe(newWireframe(hexagonContainerUIProperties, HEXAGON_SHAPE)).
																		buildBlueprint()
														).
														repetitions(numberOVerticalDivisions).
														buildBlueprint()
										).
										withSecondLineTreeBuilder(
												newBluePrintBuilder("space", new EmptyRectanglePattern()).
														withWireframe(newRectangularWireframe(spanBetweenHexagonRowsContainerUIProperties)).
														buildBlueprint()
										).
										buildBlueprint()
						).
						repetitions(numberOfGenerations).
						buildBlueprint();
	}
}
