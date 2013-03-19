package com.mgs.fantasi.structure;

import com.mgs.fantasi.structure.bluePrint.BluePrint;
import com.mgs.fantasi.structure.bluePrintPatterns.BluePrintBuilderPattern;
import com.mgs.fantasi.wireframe.Wireframe;

public abstract class BluePrintBuilderFactory {

	public static BluePrintBuilder newBluePrintBuilder(String name) {
		return new BluePrintBuilder(name);
	}

	public static class BluePrintBuilder {
		private final String name;
		private Wireframe wireframe;
		private BluePrintBuilderPattern builderPattern;

		public BluePrintBuilder(String name) {
			this.name = name;
		}

		public BluePrintBuilder withContent(BluePrintBuilderPattern builderPattern) {
			this.builderPattern = builderPattern;
			builderPattern.initialise(name, wireframe);
			return this;
		}

		public BluePrintBuilder withFrame(Wireframe wireframe) {
			this.wireframe = wireframe;
			return this;
		}

		public BluePrint build() {
			return builderPattern.buildBlueprint();
		}
	}
}
