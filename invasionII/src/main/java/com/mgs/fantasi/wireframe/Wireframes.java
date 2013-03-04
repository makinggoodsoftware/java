package com.mgs.fantasi.wireframe;

import com.mgs.fantasi.properties.UIPropertiesBuilder;
import com.mgs.fantasi.properties.data.measurements.Fraction;
import com.mgs.fantasi.properties.data.measurements.Fractions;
import com.mgs.fantasi.properties.data.polygon.NativeRectanguarShape;
import com.mgs.fantasi.properties.data.polygon.PolygonPointsIterator;
import com.mgs.fantasi.wireframe.tree.builder.*;

import java.awt.*;

public class Wireframes {
	public static LayeredElementsWireframeTreeBuilder layered(String name, UIPropertiesBuilder uiPropertiesBuilder) {
		return new LayeredElementsWireframeTreeBuilder(name, new NativeRectanguarShape(), uiPropertiesBuilder);
	}

	public static SingleChildWireframeTreeBuilder rectangle(String name, WireframeTreeBuilder content, PolygonPointsIterator shape, UIPropertiesBuilder uiPropertiesBuilder) {
		return new SingleChildWireframeTreeBuilder(name, content, shape, uiPropertiesBuilder);
	}

	public static FinalWireframeTreeBuilder emptyRectangle(String name, PolygonPointsIterator shape, UIPropertiesBuilder uiPropertiesBuilder) {
		return new FinalWireframeTreeBuilder(name, shape, uiPropertiesBuilder);
	}

	public static GridWireframeTreeBuilder twoLines(String name, Fraction firstLineHeightSizeRatio, WireframeTreeBuilder firstLineTreeBuilder, WireframeTreeBuilder secondLineTreeBuilder, UIPropertiesBuilder uiPropertiesBuilder) {
		GridWireframeTreeBuilder gridWireframeTreeBuilder = new GridWireframeTreeBuilder(name, uiPropertiesBuilder);
		Fraction remainder = Fractions.allWithBase(firstLineHeightSizeRatio.getBase()).minus(firstLineHeightSizeRatio);
		return gridWireframeTreeBuilder
				.withDimension(new Dimension(1, 2))
				.withCell(new Point(0, 0), firstLineHeightSizeRatio, Fractions.all(), firstLineTreeBuilder)
				.withCell(new Point(0, 1), remainder, Fractions.all(), secondLineTreeBuilder)
				.fill();
	}

	public static GridWireframeTreeBuilder horizontalRepeater(String name, WireframeTreeBuilder toRepeat, int numberOfGenerations, UIPropertiesBuilder uiPropertiesBuilder) {
		return new GridWireframeTreeBuilder(name, uiPropertiesBuilder)
				.withDimension(new Dimension(1, numberOfGenerations))
				.allCellsWith(toRepeat);
	}

	public static GridWireframeTreeBuilder verticalRepeater(String name, WireframeTreeBuilder toRepeat, int numberOfGenerations, UIPropertiesBuilder uiPropertiesBuilder) {
		return new GridWireframeTreeBuilder(name, uiPropertiesBuilder)
				.withDimension(new Dimension(numberOfGenerations, 1))
				.allCellsWith(toRepeat);
	}
}
