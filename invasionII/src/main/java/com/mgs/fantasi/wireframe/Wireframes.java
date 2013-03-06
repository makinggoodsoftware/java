package com.mgs.fantasi.wireframe;

import com.mgs.fantasi.properties.UIPropertiesBuilder;
import com.mgs.fantasi.properties.data.measurements.Fraction;
import com.mgs.fantasi.properties.data.measurements.Fractions;
import com.mgs.fantasi.wireframe.tree.builder.*;

import java.awt.*;

public class Wireframes {
	public static LayeredElementsWireframeTreeBuilder layered(String name, Wireframe wireframe) {
		return new LayeredElementsWireframeTreeBuilder(name, wireframe);
	}

	public static SingleChildWireframeTreeBuilder rectangle(String name, Wireframe wireframe, WireframeTreeBuilder content) {
		return new SingleChildWireframeTreeBuilder(name, wireframe, content);
	}

	public static FinalWireframeTreeBuilder emptyRectangle(String name, Wireframe wireframe) {
		return new FinalWireframeTreeBuilder(name, wireframe);
	}

	public static GridWireframeTreeBuilder twoLines(String name, Wireframe wireframe, Fraction firstLineHeightSizeRatio, WireframeTreeBuilder firstLineTreeBuilder, WireframeTreeBuilder secondLineTreeBuilder, UIPropertiesBuilder uiPropertiesBuilder) {
		GridWireframeTreeBuilder gridWireframeTreeBuilder = new GridWireframeTreeBuilder(name, wireframe);
		Fraction remainder = Fractions.allWithBase(firstLineHeightSizeRatio.getBase()).minus(firstLineHeightSizeRatio);
		return gridWireframeTreeBuilder
				.withDimension(new Dimension(1, 2))
				.withCell(new Point(0, 0), firstLineHeightSizeRatio, Fractions.all(), firstLineTreeBuilder)
				.withCell(new Point(0, 1), remainder, Fractions.all(), secondLineTreeBuilder)
				.fill();
	}

	public static GridWireframeTreeBuilder horizontalRepeater(String name, Wireframe wireframe, WireframeTreeBuilder toRepeat, int numberOfGenerations) {
		return new GridWireframeTreeBuilder(name, wireframe)
				.withDimension(new Dimension(1, numberOfGenerations))
				.allCellsWith(toRepeat);
	}

	public static GridWireframeTreeBuilder verticalRepeater(String name, Wireframe wireframe, WireframeTreeBuilder toRepeat, int numberOfGenerations) {
		return new GridWireframeTreeBuilder(name, wireframe)
				.withDimension(new Dimension(numberOfGenerations, 1))
				.allCellsWith(toRepeat);
	}
}
