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
import static com.mgs.fantasi.structure.BluePrintBuilderFactory.newBluePrintBuilder;
import static com.mgs.fantasi.wireframe.Wireframes.newRectangularWireframe;
import static com.mgs.fantasi.wireframe.Wireframes.newWireframe;

public class HexagonRowsBuilderPattern implements BluePrintBuilderPattern {
	private static final HexagonShape HEXAGON_SHAPE = new HexagonShape();
	private String name;
	private Wireframe allContainer;
	private final UIPropertiesBuilder twoLinesContainerUIProperties = allEmptyUIProperties();
	private final UIPropertiesBuilder hexagonRowsContainerUIProperties = allEmptyUIProperties();
	private final UIPropertiesBuilder spanBetweenHexagonRowsContainerUIProperties = allEmptyUIProperties();
	private final UIPropertiesBuilder hexagonContainerUIProperties = allEmptyUIProperties();

	private int numberOfGenerations;
	private int numberOVerticalDivisions;

	public HexagonRowsBuilderPattern withNumberOfGenerations(int numberOfGenerations) {
		this.numberOfGenerations = numberOfGenerations;
		return this;
	}

	public HexagonRowsBuilderPattern withNumberOfVerticalDivisions(int numberOfVerticalDivisions) {
		this.numberOVerticalDivisions = numberOfVerticalDivisions;
		return this;
	}

	public HexagonRowsBuilderPattern withHexagonSize(UIProperty<Measurement> hexagonMeasurement) {
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
				newBluePrintBuilder("hexagonRows").
						withFrame(allContainer).
						withContent(
								new HorizontalRepeaterBuilderPattern().
										repeating(
												newBluePrintBuilder("linesOfHexagons").
														withFrame(newRectangularWireframe(twoLinesContainerUIProperties)).
														withContent(
																new TwoLinesBuilderPattern().
																		withFirstLineHeightSizeRatio(Fractions.thwoThirds()).
																		withFirstLineTreeBuilder(
																				newBluePrintBuilder("hexagons").
																						withFrame(newRectangularWireframe(hexagonRowsContainerUIProperties)).
																						withContent(
																								new VerticalRepeaterBuilderPattern().
																										repeating(
																												newBluePrintBuilder("hexagon").
																														withFrame(newWireframe(hexagonContainerUIProperties, HEXAGON_SHAPE)).
																														withContent(new EmptyRectangleBuilderPattern())
																										).
																										repetitions(numberOVerticalDivisions)
																						)
																		).
																		withSecondLineTreeBuilder(
																				newBluePrintBuilder("space").
																						withFrame(newRectangularWireframe(spanBetweenHexagonRowsContainerUIProperties)).
																						withContent(new EmptyRectangleBuilderPattern())
																		)
														)
										).
										repetitions(numberOfGenerations)).
						build();
	}
}
