package com.mgs.invasion.mvc.views;

import com.mgs.fantasi.properties.UIPropertiesBuilder;
import com.mgs.fantasi.properties.UIProperty;
import com.mgs.fantasi.properties.data.measurements.Fractions;
import com.mgs.fantasi.properties.data.measurements.Measurement;
import com.mgs.fantasi.properties.data.polygon.HexagonShape;
import com.mgs.fantasi.structure.BasicBluePrintBuildersFactory;
import com.mgs.fantasi.structure.Structure;
import com.mgs.fantasi.structure.bluePrint.BluePrint;
import com.mgs.fantasi.structure.bluePrint.GridBluePrint;
import com.mgs.fantasi.structure.bluePrint.NoChildrenBluePrint;
import com.mgs.fantasi.wireframe.Wireframe;

import static com.mgs.fantasi.properties.UIPropertiesBuilderFactory.allEmptyUIProperties;
import static com.mgs.fantasi.structure.BasicBluePrintBuilders.newBasicBluePrintBuilder;
import static com.mgs.fantasi.structure.BasicBluePrintBuildersFactory.newBluePrintBuilder;
import static com.mgs.fantasi.wireframe.Wireframes.*;

public class HexagonRowsBluePrint implements BluePrint {
	private static final HexagonShape HEXAGON_SHAPE = new HexagonShape();
	private final String name;
	private final Wireframe allContainer;
	private final UIPropertiesBuilder twoLinesContainerUIProperties = allEmptyUIProperties();
	private final UIPropertiesBuilder hexagonRowsContainerUIProperties = allEmptyUIProperties();
	private final UIPropertiesBuilder spanBetweenHexagonRowsContainerUIProperties = allEmptyUIProperties();
	private final UIPropertiesBuilder hexagonContainerUIProperties = allEmptyUIProperties();

	private int numberOfGenerations;
	private int numberOVerticalDivisions;

	public static HexagonRowsBluePrint hexagonRows(String name) {
		return new HexagonRowsBluePrint(name, newRectangularAllUIPropertiesEmptyWireframe());
	}

	public HexagonRowsBluePrint(String name, Wireframe wireframe) {
		this.name = name;
		this.allContainer = wireframe;
	}

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
	public String getName() {
		return name;
	}

	@Override
	public Structure buildStructure() {
		GridBluePrint firstLineTreeBuilder = newBasicBluePrintBuilder("hexagonsRow").
				withWireframe(newRectangularWireframe(hexagonRowsContainerUIProperties)).
				repeatingVertically(
						newBasicBluePrintBuilder("hexagon").
								withWireframe(newWireframe(hexagonContainerUIProperties, HEXAGON_SHAPE)).
								emptyRectangle(),
						numberOVerticalDivisions);
		NoChildrenBluePrint space = newBasicBluePrintBuilder("space").
				withWireframe(newRectangularWireframe(spanBetweenHexagonRowsContainerUIProperties)).
				emptyRectangle();
		return
				newBluePrintBuilder("hexagonRows", new BasicBluePrintBuildersFactory.HorizontalRepeaterPattern()).
						withWireframe(allContainer).
						repeating(
								newBluePrintBuilder("linesOfHexagons", new BasicBluePrintBuildersFactory.TwoLinesPattern()).
										withWireframe(newRectangularWireframe(twoLinesContainerUIProperties)).
										withFirstLineHeightSizeRatio(Fractions.thwoThirds()).
										withFirstLineTreeBuilder(firstLineTreeBuilder).
										withSecondLineTreeBuilder(space).
										buildBlueprint()
						).
						repetitions(numberOfGenerations).
						buildBlueprint().
						buildStructure();
	}


}
