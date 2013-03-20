package com.mgs.fantasi.structure;

import com.mgs.fantasi.structure.bluePrintPatterns.StructureContentBuilder;
import com.mgs.fantasi.wireframe.Wireframe;

public abstract class StructureBuilderFactory {

	public static StructureBuilder createStructureBuilder(String name) {
		return new StructureBuilder(name);
	}

	public static class StructureBuilder {
		private final String name;
		private Wireframe wireframe;
		private StructureContentBuilder builder;

		public StructureBuilder(String name) {
			this.name = name;
		}

		public StructureBuilder withContent(StructureContentBuilder builder) {
			this.builder = builder;
			return this;
		}

		public StructureBuilder withFrame(Wireframe wireframe) {
			this.wireframe = wireframe;
			return this;
		}

		public Structure build() {
			return builder.buildStructure(name, wireframe);
		}
	}
}
