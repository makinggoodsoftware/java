package com.mgs.fantasi.wireframe;

import com.mgs.fantasi.properties.UIPropertiesBuilder;
import com.mgs.fantasi.properties.data.measurements.Fraction;
import com.mgs.fantasi.properties.data.measurements.Fractions;
import com.mgs.fantasi.wireframe.tree.builder.*;

import java.awt.*;

public abstract class BluePrints {
	public static BluePrintBuilder newBluePrint(String name, Wireframe wireframe) {
		return new BluePrintBuilder(name, wireframe);
	}

	public static SingleChildBluePrint rectangle(String name, Wireframe wireframe, BluePrint content) {
		return new SingleChildBluePrint(name, wireframe, content);
	}

	public static NoChildrenBluePrint emptyRectangle(String name, Wireframe wireframe) {
		return new NoChildrenBluePrint(name, wireframe);
	}

	public static GridBluePrint twoLines(String name, Wireframe wireframe, Fraction firstLineHeightSizeRatio, BluePrint firstLineTreeBuilder, BluePrint secondLineTreeBuilder, UIPropertiesBuilder uiPropertiesBuilder) {
		GridBluePrint gridWireframeTreeBuilder = new GridBluePrint(name, wireframe);
		Fraction remainder = Fractions.allWithBase(firstLineHeightSizeRatio.getBase()).minus(firstLineHeightSizeRatio);
		return gridWireframeTreeBuilder
				.withDimension(new Dimension(1, 2))
				.withCell(new Point(0, 0), firstLineHeightSizeRatio, Fractions.all(), firstLineTreeBuilder)
				.withCell(new Point(0, 1), remainder, Fractions.all(), secondLineTreeBuilder)
				.fill();
	}

	public static GridBluePrint horizontalRepeater(String name, Wireframe wireframe, BluePrint toRepeat, int numberOfGenerations) {
		return new GridBluePrint(name, wireframe)
				.withDimension(new Dimension(1, numberOfGenerations))
				.allCellsWith(toRepeat);
	}

	public static GridBluePrint verticalRepeater(String name, Wireframe wireframe, BluePrint toRepeat, int numberOfGenerations) {
		return new GridBluePrint(name, wireframe)
				.withDimension(new Dimension(numberOfGenerations, 1))
				.allCellsWith(toRepeat);
	}

	public static class BluePrintBuilder {
		private final String name;
		private final Wireframe wireframe;

		public BluePrintBuilder(String name, Wireframe wireframe) {
			this.name = name;
			this.wireframe = wireframe;
		}

		public LayeredElementsBluePrint layered() {
			return new LayeredElementsBluePrint(name, wireframe);
		}
	}
}
