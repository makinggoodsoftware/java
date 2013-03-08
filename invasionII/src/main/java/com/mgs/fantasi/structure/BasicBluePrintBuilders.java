package com.mgs.fantasi.structure;

import com.mgs.fantasi.properties.data.measurements.Fraction;
import com.mgs.fantasi.properties.data.measurements.Fractions;
import com.mgs.fantasi.structure.bluePrint.*;
import com.mgs.fantasi.wireframe.Wireframe;

import java.awt.*;

public abstract class BasicBluePrintBuilders {

	public static BasicBluePrintBuilderStep1SpecifyWireframe newBasicBluePrintBuilder(String name) {
		return new BasicBluePrintBuilderStep1SpecifyWireframe(name);
	}

	public static class BasicBluePrintBuilderStep1SpecifyWireframe {
		private final String name;

		public BasicBluePrintBuilderStep1SpecifyWireframe(String name) {
			this.name = name;
		}

		public BasicBluePrintBuilderStep2SpecifyTypeOfLayout withWireframe(Wireframe wireframe) {
			return new BasicBluePrintBuilderStep2SpecifyTypeOfLayout(name, wireframe);
		}
	}

	public static class BasicBluePrintBuilderStep2SpecifyTypeOfLayout {
		private final String name;
		private final Wireframe wireframe;

		public BasicBluePrintBuilderStep2SpecifyTypeOfLayout(String name, Wireframe wireframe) {
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

		public GridBluePrint repeatingHorizontally(BluePrint toRepeat, int numberOfGenerations) {
			return new GridBluePrint(name, wireframe)
					.withDimension(new Dimension(1, numberOfGenerations))
					.allCellsWith(toRepeat);
		}

		public GridBluePrint repeatingVertically(BluePrint toRepeat, int numberOfGenerations) {
			return new GridBluePrint(name, wireframe)
					.withDimension(new Dimension(numberOfGenerations, 1))
					.allCellsWith(toRepeat);
		}
	}
}
