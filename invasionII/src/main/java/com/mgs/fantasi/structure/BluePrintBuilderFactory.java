package com.mgs.fantasi.structure;

import com.mgs.fantasi.structure.bluePrint.BluePrint;
import com.mgs.fantasi.structure.bluePrintPatterns.BluePrintBuilder;
import com.mgs.fantasi.wireframe.Wireframe;

public abstract class BluePrintBuilderFactory {

	public static BluePrintBuilderHelper createCanvas(String name) {
		return new BluePrintBuilderHelper(name);
	}

	public static class BluePrintBuilderHelper {
		private final String name;
		private Wireframe wireframe;
		private BluePrintBuilder builder;

		public BluePrintBuilderHelper(String name) {
			this.name = name;
		}

		public BluePrintBuilderHelper withContent(BluePrintBuilder builder) {
			this.builder = builder;
			return this;
		}

		public BluePrintBuilderHelper withFrame(Wireframe wireframe) {
			this.wireframe = wireframe;
			return this;
		}

		public BluePrint build() {
			return builder.buildBlueprint(name, wireframe);
		}
	}
}
