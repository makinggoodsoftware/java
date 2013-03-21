package com.mgs.invasion.mvc.views;

import com.mgs.fantasi.properties.UIPropertiesBuilder;
import com.mgs.fantasi.properties.UIProperty;
import com.mgs.fantasi.properties.data.measurements.Fractions;
import com.mgs.fantasi.properties.data.measurements.Measurement;
import com.mgs.fantasi.properties.data.polygon.HexagonShape;
import com.mgs.fantasi.structure.Structure;
import com.mgs.fantasi.structure.structureBuilder.Layout.StructureLayout;
import com.mgs.fantasi.wireframe.Wireframe;

import static com.mgs.fantasi.properties.UIPropertiesBuilderFactory.allEmptyUIProperties;
import static com.mgs.fantasi.structure.structureBuilder.Layout.HorizontalRepeaterLayout.horizontalRepeater;
import static com.mgs.fantasi.structure.structureBuilder.Layout.TwoHorizontalLinesLayout.twoHorizontalLines;
import static com.mgs.fantasi.structure.structureBuilder.Layout.VerticalRepeaterLayout.verticalRepeater;
import static com.mgs.fantasi.structure.structureBuilder.StructureBuilderFactory.createStructureBuilder;
import static com.mgs.fantasi.wireframe.Wireframes.newRectangularWireframe;
import static com.mgs.fantasi.wireframe.Wireframes.newWireframe;

public class HexagonRowsLayout implements StructureLayout {
	private static final HexagonShape HEXAGON_SHAPE = new HexagonShape();
	private final UIPropertiesBuilder twoLinesContainerUIProperties = allEmptyUIProperties();
	private final UIPropertiesBuilder hexagonRowsContainerUIProperties = allEmptyUIProperties();
	private final UIPropertiesBuilder spanBetweenHexagonRowsContainerUIProperties = allEmptyUIProperties();
	private final UIPropertiesBuilder hexagonContainerUIProperties = allEmptyUIProperties();

	private int numberOfGenerations;
	private int numberOVerticalDivisions;

	private HexagonRowsLayout() {
	}

	public static HexagonRowsLayout hexagonRows() {
		return new HexagonRowsLayout();
	}

	public HexagonRowsLayout withNumberOfGenerations(int numberOfGenerations) {
		this.numberOfGenerations = numberOfGenerations;
		return this;
	}

	public HexagonRowsLayout withNumberOfVerticalDivisions(int numberOfVerticalDivisions) {
		this.numberOVerticalDivisions = numberOfVerticalDivisions;
		return this;
	}

	public HexagonRowsLayout withHexagonSize(UIProperty<Measurement> hexagonMeasurement) {
		hexagonContainerUIProperties.withMeasurement(hexagonMeasurement);
		return this;
	}

	@Override
	public Structure buildStructure(String name, Wireframe wireframe) {
		return
				createStructureBuilder("hexagonRows").
						withFrame(wireframe).
						withLayout(
								horizontalRepeater().
										repeating(
												createStructureBuilder("linesOfHexagons").
														withFrame(newRectangularWireframe(twoLinesContainerUIProperties)).
														withLayout(
																twoHorizontalLines().
																		withFirstLineHeightSizeRatio(Fractions.thwoThirds()).
																		withFirstLineTreeBuilder(
																				createStructureBuilder("hexagons").
																						withFrame(newRectangularWireframe(hexagonRowsContainerUIProperties)).
																						withLayout(
																								verticalRepeater().
																										repeating(
																												createStructureBuilder("hexagon").withFrame(newWireframe(hexagonContainerUIProperties, HEXAGON_SHAPE))
																										).
																										repetitions(numberOVerticalDivisions)
																						)
																		).
																		withSecondLineTreeBuilder(
																				createStructureBuilder("space").withFrame(newRectangularWireframe(spanBetweenHexagonRowsContainerUIProperties))
																		)
														)
										).
										repetitions(numberOfGenerations)).
						build();
	}
}
