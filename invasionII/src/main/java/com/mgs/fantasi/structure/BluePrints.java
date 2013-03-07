package com.mgs.fantasi.structure;

import com.mgs.fantasi.properties.data.measurements.Fraction;
import com.mgs.fantasi.properties.data.measurements.Fractions;
import com.mgs.fantasi.structure.bluePrint.*;
import com.mgs.fantasi.wireframe.Wireframe;

import java.awt.*;

public abstract class BluePrints {
	public static BluePrintBuilder newBluePrint(String name, Wireframe wireframe) {
		return new BluePrintBuilder(name, wireframe);
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

		public SingleChildBluePrint rectangle(BluePrint content) {
			return new SingleChildBluePrint(name, wireframe, content);
		}

		public NoChildrenBluePrint emptyRectangle() {
			return new NoChildrenBluePrint(name, wireframe);
		}

		public GridBluePrint twoLines(Fraction firstLineHeightSizeRatio, BluePrint firstLineTreeBuilder, BluePrint secondLineTreeBuilder) {
			GridBluePrint gridWireframeTreeBuilder = new GridBluePrint(name, wireframe);
			Fraction remainder = Fractions.allWithBase(firstLineHeightSizeRatio.getBase()).minus(firstLineHeightSizeRatio);
			return gridWireframeTreeBuilder
					.withDimension(new Dimension(1, 2))
					.withCell(new Point(0, 0), firstLineHeightSizeRatio, Fractions.all(), firstLineTreeBuilder)
					.withCell(new Point(0, 1), remainder, Fractions.all(), secondLineTreeBuilder)
					.fill();
		}

		public GridBluePrint horizontalRepeater(BluePrint toRepeat, int numberOfGenerations) {
			return new GridBluePrint(name, wireframe)
					.withDimension(new Dimension(1, numberOfGenerations))
					.allCellsWith(toRepeat);
		}

		public GridBluePrint verticalRepeater(BluePrint toRepeat, int numberOfGenerations) {
			return new GridBluePrint(name, wireframe)
					.withDimension(new Dimension(numberOfGenerations, 1))
					.allCellsWith(toRepeat);
		}
	}
}
