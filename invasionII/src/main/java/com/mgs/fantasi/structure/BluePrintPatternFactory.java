package com.mgs.fantasi.structure;

import com.mgs.fantasi.structure.bluePrintPatterns.BluePrintPattern;
import com.mgs.fantasi.wireframe.Wireframe;

public abstract class BluePrintPatternFactory {

	public static <T extends BluePrintPattern> BluePrintPatternBuilder<T> newBluePrintBuilder(String name, T pattern) {
		return new BluePrintPatternBuilder(name, pattern);
	}

	public static Blah newBluePrintBuilder(String name) {
		return new Blah(name);
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

	public static class Blah {
		private final String name;

		public Blah(String name) {
			this.name = name;
		}

		public Bleh withWireframe(Wireframe wireframe) {
			return new Bleh(name, wireframe);
		}
	}

	public static class Bleh {
		private final String name;
		private final Wireframe wireframe;

		public Bleh(String name, Wireframe wireframe) {
			this.name = name;
			this.wireframe = wireframe;
		}

		public <T extends BluePrintPattern> T withContent(T pattern) {
			pattern.initialise(name, wireframe);
			return pattern;
		}
	}
}
