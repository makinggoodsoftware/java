package com.mgs.fantasi.structure;

import com.mgs.fantasi.properties.data.measurements.Fraction;
import com.mgs.fantasi.properties.data.measurements.Fractions;
import com.mgs.fantasi.structure.bluePrint.*;
import com.mgs.fantasi.wireframe.Wireframe;

import java.awt.*;

public abstract class BasicBluePrintBuildersFactory {

	public static <T extends BluePrintBuilder> ZBasicBluePrintBuilderStep1SpecifyWireframe<T> newBluePrintBuilder(String name, T pattern) {
		return new ZBasicBluePrintBuilderStep1SpecifyWireframe(name, pattern);
	}

	public static class ZBasicBluePrintBuilderStep1SpecifyWireframe<T extends BluePrintBuilder> {
		private final String name;
		private final T bluePrintBuilder;

		public ZBasicBluePrintBuilderStep1SpecifyWireframe(String name, T bluePrintBuilder) {
			this.name = name;
			this.bluePrintBuilder = bluePrintBuilder;
		}

		public T withWireframe(Wireframe wireframe) {
			bluePrintBuilder.initialise(name, wireframe);
			return bluePrintBuilder;
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
			Fraction remainder = Fractions.allWithBase(firstLineHeightSizeRatio.getBase()).minus(firstLineHeightSizeRatio);
			return new GridBluePrint(name, wireframe)
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

	public static class HorizontalRepeaterPattern implements BluePrintBuilder {
		private String name;
		private Wireframe wireframe;
		private BluePrint bluePrint;
		private int numberOfGenerations;

		@Override
		public void initialise(String name, Wireframe wireframe) {
			this.name = name;
			this.wireframe = wireframe;
		}

		public HorizontalRepeaterPattern repeating(BluePrint bluePrint) {
			this.bluePrint = bluePrint;
			return this;
		}

		public HorizontalRepeaterPattern repetitions(int numberOfGenerations) {
			this.numberOfGenerations = numberOfGenerations;
			return this;
		}

		@Override
		public BluePrint buildBlueprint() {
			return new GridBluePrint(name, wireframe)
					.withDimension(new Dimension(1, numberOfGenerations))
					.allCellsWith(bluePrint);
		}
	}

	public static class TwoLinesPattern implements BluePrintBuilder {
		private String name;
		private Wireframe wireframe;
		private Fraction firstLineHeightSizeRatio;
		private BluePrint firstLineTreeBuilder;
		private BluePrint secondLineTreeBuilder;

		public TwoLinesPattern withFirstLineHeightSizeRatio(Fraction firstLineHeightSizeRatio) {
			this.firstLineHeightSizeRatio = firstLineHeightSizeRatio;
			return this;
		}

		public TwoLinesPattern withFirstLineTreeBuilder(BluePrint firstLineTreeBuilder) {
			this.firstLineTreeBuilder = firstLineTreeBuilder;
			return this;
		}

		public TwoLinesPattern withSecondLineTreeBuilder(BluePrint secondLineTreeBuilder) {
			this.secondLineTreeBuilder = secondLineTreeBuilder;
			return this;
		}

		@Override
		public void initialise(String name, Wireframe wireframe) {
			this.name = name;
			this.wireframe = wireframe;
		}

		@Override
		public BluePrint buildBlueprint() {
			Fraction remainder = Fractions.allWithBase(firstLineHeightSizeRatio.getBase()).minus(firstLineHeightSizeRatio);
			return new GridBluePrint(name, wireframe)
					.withDimension(new Dimension(1, 2))
					.withCell(new Point(0, 0), firstLineHeightSizeRatio, Fractions.all(), firstLineTreeBuilder)
					.withCell(new Point(0, 1), remainder, Fractions.all(), secondLineTreeBuilder)
					.fill();
		}
	}

	public static interface BluePrintBuilder {

		void initialise(String name, Wireframe wireframe);

		BluePrint buildBlueprint();

	}
}
