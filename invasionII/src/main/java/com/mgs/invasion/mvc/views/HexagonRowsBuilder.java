package com.mgs.invasion.mvc.views;

import com.mgs.fantasi.properties.UIPropertiesBuilder;
import com.mgs.fantasi.properties.UIProperty;
import com.mgs.fantasi.properties.data.measurements.Fractions;
import com.mgs.fantasi.properties.data.measurements.Measurement;
import com.mgs.fantasi.properties.data.polygon.HexagonShape;
import com.mgs.fantasi.structure.Structure;
import com.mgs.fantasi.structure.structureBuilder.Layout.*;
import com.mgs.fantasi.wireframe.Wireframe;

import static com.mgs.fantasi.properties.UIPropertiesBuilderFactory.allEmptyUIProperties;
import static com.mgs.fantasi.structure.structureBuilder.StructureBuilderFactory.createStructureBuilder;
import static com.mgs.fantasi.wireframe.Wireframes.newRectangularWireframe;
import static com.mgs.fantasi.wireframe.Wireframes.newWireframe;

public class HexagonRowsBuilder implements StructureLayout {
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
						withLayout(new HorizontalRepeaterLayout().
								repeating(
										createStructureBuilder("linesOfHexagons").
												withFrame(newRectangularWireframe(twoLinesContainerUIProperties)).
												withLayout(
														new TwoHorizontalLinesLayout().
																withFirstLineHeightSizeRatio(Fractions.thwoThirds()).
																withFirstLineTreeBuilder(
																		createStructureBuilder("hexagons").
																				withFrame(newRectangularWireframe(hexagonRowsContainerUIProperties)).
																				withLayout(
																						new VerticalRepeaterLayout().
																								repeating(
																										createStructureBuilder("hexagon").
																												withFrame(newWireframe(hexagonContainerUIProperties, HEXAGON_SHAPE)).
																												withLayout(new EmptyLayout())
																								).
																								repetitions(numberOVerticalDivisions)
																				)
																).
																withSecondLineTreeBuilder(
																		createStructureBuilder("space").
																				withFrame(newRectangularWireframe(spanBetweenHexagonRowsContainerUIProperties)).
																				withLayout(new EmptyLayout())
																)
												)
								).
								repetitions(numberOfGenerations)).
						build();
	}
}
