package com.mgs.fantasi.structure;

import com.mgs.fantasi.structure.bluePrintPatterns.BluePrintPattern;
import com.mgs.fantasi.wireframe.Wireframe;

public abstract class BluePrintPatternFactory {

	public static <T extends BluePrintPattern> BluePrintPatternBuilder<T> newBluePrintBuilder(String name, T pattern) {
		return new BluePrintPatternBuilder(name, pattern);
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

}
