package com.mgs.fantasi.structure;

import com.mgs.fantasi.structure.bluePrint.BluePrint;
import com.mgs.fantasi.wireframe.Wireframe;

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

	public static interface BluePrintBuilder {

		void initialise(String name, Wireframe wireframe);

		BluePrint buildBlueprint();

	}
}
