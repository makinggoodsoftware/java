package com.mgs.fantasi.structure.bluePrintPatterns;

import com.mgs.fantasi.structure.BluePrintBuilderFactory;
import com.mgs.fantasi.structure.bluePrint.BluePrint;
import com.mgs.fantasi.structure.bluePrint.LayeredElementsBluePrint;
import com.mgs.fantasi.wireframe.Wireframe;

import java.util.ArrayList;
import java.util.List;

public class LayeredBuilder implements BluePrintBuilder {
	private final List<BluePrintBuilderFactory.BluePrintBuilderHelper> layers = new ArrayList<BluePrintBuilderFactory.BluePrintBuilderHelper>();

	private LayeredBuilder() {
	}

	public static LayeredBuilder layered() {
		return new LayeredBuilder();
	}

	public LayeredBuilder withLayer(BluePrintBuilderFactory.BluePrintBuilderHelper layer) {
		layers.add(layer);
		return this;
	}

	@Override
	public BluePrint buildBlueprint(String name, Wireframe wireframe) {
		LayeredElementsBluePrint layeredElementsBluePrint = new LayeredElementsBluePrint(name, wireframe);
		for (BluePrintBuilderFactory.BluePrintBuilderHelper layer : layers) {
			layeredElementsBluePrint.withLayer(layer.build());
		}
		return layeredElementsBluePrint;
	}
}
