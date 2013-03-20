package com.mgs.invasion.mvc.views;

import com.mgs.fantasi.properties.UIPropertiesBuilder;
import com.mgs.fantasi.properties.UIProperty;
import com.mgs.fantasi.properties.data.measurements.Fractions;
import com.mgs.fantasi.properties.data.measurements.Measurement;
import com.mgs.fantasi.properties.data.polygon.HexagonShape;
import com.mgs.fantasi.structure.Structure;
import com.mgs.fantasi.structure.bluePrintPatterns.*;
import com.mgs.fantasi.wireframe.Wireframe;

import static com.mgs.fantasi.properties.UIPropertiesBuilderFactory.allEmptyUIProperties;
import static com.mgs.fantasi.structure.StructureBuilderFactory.createStructureBuilder;
import static com.mgs.fantasi.wireframe.Wireframes.newRectangularWireframe;
import static com.mgs.fantasi.wireframe.Wireframes.newWireframe;

public class HexagonRowsBuilder implements StructureContentBuilder {
	private static final HexagonShape HEXAGON_SHAPE = new HexagonShape();
	private final UIPropertiesBuilder twoLinesContainerUIProperties = allEmptyUIProperties();
	private final UIPropertiesBuilder hexagonRowsContainerUIProperties = allEmptyUIProperties();
	private final UIPropertiesBuilder spanBetweenHexagonRowsContainerUIProperties = allEmptyUIProperties();
	private final UIPropertiesBuilder hexagonContainerUIProperties = allEmptyUIProperties();

	private int numberOfGenerations;
	private int numberOVerticalDivisions;

	public HexagonRowsBuilder withNumberOfGenerations(int numberOfGenerations) {
		this.numberOfGenerations = numberOfGenerations;
		return this;
	}

	public HexagonRowsBuilder withNumberOfVerticalDivisions(int numberOfVerticalDivisions) {
		this.numberOVerticalDivisions = numberOfVerticalDivisions;
		return this;
	}

	public HexagonRowsBuilder withHexagonSize(UIProperty<Measurement> hexagonMeasurement) {
		hexagonContainerUIProperties.withMeasurement(hexagonMeasurement);
		return this;
	}

	@Override
	public Structure buildStructure(String name, Wireframe wireframe) {
		return
				createStructureBuilder("hexagonRows").
						withFrame(wireframe).
						withContent(new HorizontalRepeaterBuilder().
								repeating(
										createStructureBuilder("linesOfHexagons").
												withFrame(newRectangularWireframe(twoLinesContainerUIProperties)).
												withContent(
														new TwoLinesBuilder().
																withFirstLineHeightSizeRatio(Fractions.thwoThirds()).
																withFirstLineTreeBuilder(
																		createStructureBuilder("hexagons").
																				withFrame(newRectangularWireframe(hexagonRowsContainerUIProperties)).
																				withContent(
																						new VerticalRepeaterBuilder().
																								repeating(
																										createStructureBuilder("hexagon").
																												withFrame(newWireframe(hexagonContainerUIProperties, HEXAGON_SHAPE)).
																												withContent(new EmptyRectangleBuilder())
																								).
																								repetitions(numberOVerticalDivisions)
																				)
																).
																withSecondLineTreeBuilder(
																		createStructureBuilder("space").
																				withFrame(newRectangularWireframe(spanBetweenHexagonRowsContainerUIProperties)).
																				withContent(new EmptyRectangleBuilder())
																)
												)
								).
								repetitions(numberOfGenerations)).
						build();
	}
}
