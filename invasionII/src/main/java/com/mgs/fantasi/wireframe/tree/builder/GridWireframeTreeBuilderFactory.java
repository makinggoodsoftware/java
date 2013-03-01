package com.mgs.fantasi.wireframe.tree.builder;

import com.mgs.fantasi.properties.UIPropertiesBuilder;
import com.mgs.fantasi.properties.data.measurements.Fraction;
import com.mgs.fantasi.properties.data.measurements.Fractions;

import java.awt.*;

public class GridWireframeTreeBuilderFactory {
	public static GridWireframeTreeBuilder twoLines(String name, Fraction firstLineHeightSizeRatio, WireframeTreeBuilder firstLineTreeBuilder, WireframeTreeBuilder secondLineTreeBuilder, UIPropertiesBuilder uiPropertiesBuilder) {
		GridWireframeTreeBuilder gridWireframeTreeBuilder = new GridWireframeTreeBuilder(name, uiPropertiesBuilder);
		Fraction remainder = Fractions.allWithBase(firstLineHeightSizeRatio.getBase()).minus(firstLineHeightSizeRatio);
		return gridWireframeTreeBuilder
				.withDimension(new Dimension(1, 2))
				.withCell(new Point(0, 0), firstLineHeightSizeRatio, Fractions.all(), firstLineTreeBuilder)
				.withCell(new Point(0, 1), remainder, Fractions.all(), secondLineTreeBuilder)
				.fill();
	}
}
