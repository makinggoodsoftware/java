package com.mgs.invasion.mvc.views;

import com.mgs.fantasi.properties.UIPropertiesBuilder;
import com.mgs.fantasi.properties.UIProperty;
import com.mgs.fantasi.properties.data.measurements.Fractions;
import com.mgs.fantasi.properties.data.measurements.Measurement;
import com.mgs.fantasi.properties.data.polygon.HexagonShape;
import com.mgs.fantasi.properties.data.polygon.NativeRectanguarShape;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.tree.Structure;
import com.mgs.fantasi.wireframe.tree.builder.BluePrint;

import static com.mgs.fantasi.properties.UIPropertiesBuilderFactory.allEmptyUIProperties;
import static com.mgs.fantasi.wireframe.BluePrints.newBluePrint;

public class HexagonRowsBluePrint implements BluePrint {
	private final String name;
	private final Wireframe allContainer;
	private final UIPropertiesBuilder twoLinesContainerUIProperties = allEmptyUIProperties();
	private final UIPropertiesBuilder hexagonRowsContainerUIProperties = allEmptyUIProperties();
	private final UIPropertiesBuilder spanBetweenHexagonRowsContainerUIProperties = allEmptyUIProperties();
	private final UIPropertiesBuilder hexagonContainerUIProperties = allEmptyUIProperties();

	private int numberOfGenerations;
	private int numberOVerticalDivisions;

	public static HexagonRowsBluePrint hexagonRows(String name) {
		return new HexagonRowsBluePrint(name, new Wireframe(allEmptyUIProperties().build(), new NativeRectanguarShape()));
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
	public Wireframe getRootWireframe() {
		return allContainer;
	}

	@Override
	public Structure build() {
		Wireframe twoLinesContainer = new Wireframe(twoLinesContainerUIProperties.build(), new NativeRectanguarShape());
		Wireframe hexagonRowsContainer = new Wireframe(hexagonRowsContainerUIProperties.build(), new NativeRectanguarShape());
		Wireframe spanBetweenHexagonRowsContainer = new Wireframe(spanBetweenHexagonRowsContainerUIProperties.build(), new NativeRectanguarShape());
		Wireframe hexagonContainer = new Wireframe(hexagonContainerUIProperties.build(), new HexagonShape());

		return
				newBluePrint(getName() + "_pijama_rows", allContainer).horizontalRepeater(
						newBluePrint(getName() + "_pijama_rows", twoLinesContainer).twoLines(
								Fractions.thwoThirds(),
								newBluePrint(getName() + "_hexagons", hexagonRowsContainer).verticalRepeater(
										newBluePrint(getName() + "_hexagon", hexagonContainer).emptyRectangle(),
										numberOVerticalDivisions
								),
								newBluePrint(getName() + "_space", spanBetweenHexagonRowsContainer).emptyRectangle()
						),
						numberOfGenerations
				).build();
	}
}
