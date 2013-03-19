package com.mgs.fantasi.structure;

import com.mgs.fantasi.structure.bluePrint.BluePrint;
import com.mgs.fantasi.structure.bluePrintPatterns.BluePrintPattern;
import com.mgs.fantasi.wireframe.Wireframe;

public abstract class BluePrintPatternFactory {

	public static <T extends BluePrintPattern> BluePrintPatternBuilder<T> newBluePrintBuilder(String name, T pattern) {
		return new BluePrintPatternBuilder(name, pattern);
	}

	public static BluePrintBuilder newBluePrintBuilder(String name) {
		return new BluePrintBuilder(name);
	}

	public static class BluePrintPatternBuilder<T extends BluePrintPattern> {
		private final String name;
		private final T bluePrintBuilder;

		public BluePrintPatternBuilder(String name, T bluePrintBuilder) {
			this.name = name;
			this.bluePrintBuilder = bluePrintBuilder;
		}

		public T withWireframe(Wireframe wireframe) {
			bluePrintBuilder.initialise(name, wireframe);
			return bluePrintBuilder;
		}
	}

	public static class BluePrintBuilder {
		private final String name;
		private Wireframe wireframe;
		private BluePrintPattern pattern;

		public BluePrintBuilder(String name) {
			this.name = name;
		}

		public BluePrintBuilder withContent(BluePrintPattern pattern) {
			this.pattern = pattern;
			pattern.initialise(name, wireframe);
			return this;
		}

		public BluePrintBuilder withWireframe(Wireframe wireframe) {
			this.wireframe = wireframe;
			return this;
		}

		public BluePrint build() {
			return pattern.buildBlueprint();
		}
	}
}
