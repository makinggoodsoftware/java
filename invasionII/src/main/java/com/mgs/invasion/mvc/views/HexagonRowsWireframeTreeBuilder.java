package com.mgs.invasion.mvc.views;

import com.mgs.fantasi.properties.UIPropertiesBuilder;
import com.mgs.fantasi.properties.UIProperty;
import com.mgs.fantasi.properties.data.measurements.Fractions;
import com.mgs.fantasi.properties.data.measurements.Measurement;
import com.mgs.fantasi.properties.data.polygon.HexagonShape;
import com.mgs.fantasi.properties.data.polygon.NativeRectanguarShape;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.Wireframes;
import com.mgs.fantasi.wireframe.tree.WireframeTree;
import com.mgs.fantasi.wireframe.tree.builder.WireframeTreeBuilder;

import static com.mgs.fantasi.properties.UIPropertiesBuilderFactory.allEmptyUIProperties;
import static com.mgs.fantasi.wireframe.Wireframes.*;

public class HexagonRowsWireframeTreeBuilder implements WireframeTreeBuilder {
	private final String name;
	private final Wireframe allContainer;
	private final UIPropertiesBuilder twoLinesContainerUIProperties = allEmptyUIProperties();
	private final UIPropertiesBuilder hexagonRowsContainerUIProperties = allEmptyUIProperties();
	private final UIPropertiesBuilder spanBetweenHexagonRowsContainerUIProperties = allEmptyUIProperties();
	private final UIPropertiesBuilder hexagonContainerUIProperties = allEmptyUIProperties();

	private int numberOfGenerations;
	private int numberOVerticalDivisions;

	public static HexagonRowsWireframeTreeBuilder hexagonRows(String name) {
		return new HexagonRowsWireframeTreeBuilder(name, new Wireframe(allEmptyUIProperties().build(), new NativeRectanguarShape()));
	}

	public HexagonRowsWireframeTreeBuilder(String name, Wireframe wireframe) {
		this.name = name;
		this.allContainer = wireframe;
	}

	public HexagonRowsWireframeTreeBuilder withNumberOfGenerations(int numberOfGenerations) {
		this.numberOfGenerations = numberOfGenerations;
		return this;
	}

	public HexagonRowsWireframeTreeBuilder withNumberOfVerticalDivisions(int numberOfVerticalDivisions) {
		this.numberOVerticalDivisions = numberOfVerticalDivisions;
		return this;
	}

	public HexagonRowsWireframeTreeBuilder withHexagonSize(UIProperty<Measurement> hexagonMeasurement) {
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
	public WireframeTree build() {
		Wireframe twoLinesContainer = new Wireframe(twoLinesContainerUIProperties.build(), new NativeRectanguarShape());
		Wireframe hexagonRowsContainer = new Wireframe(hexagonRowsContainerUIProperties.build(), new NativeRectanguarShape());
		Wireframe spanBetweenHexagonRowsContainer = new Wireframe(spanBetweenHexagonRowsContainerUIProperties.build(), new NativeRectanguarShape());
		Wireframe hexagonContainer = new Wireframe(hexagonContainerUIProperties.build(), new HexagonShape());

		return Wireframes.horizontalRepeater(
				getName() + "_pijama_rows",
				allContainer,
				twoLines(
						getName() + "_generation",
						twoLinesContainer, Fractions.thwoThirds(),
						verticalRepeater(getName() + "_hexagons", hexagonRowsContainer, emptyRectangle(getName() + "_hexagon", hexagonContainer), numberOVerticalDivisions),
						emptyRectangle(getName() + "_space", spanBetweenHexagonRowsContainer), allEmptyUIProperties()
				),
				numberOfGenerations
		).build();
	}
}
