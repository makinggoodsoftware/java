package com.mgs.fantasi.structure.bluePrintPatterns;

import com.mgs.fantasi.structure.BluePrintBuilderFactory;
import com.mgs.fantasi.structure.bluePrint.BluePrint;
import com.mgs.fantasi.structure.bluePrint.LayeredElementsBluePrint;
import com.mgs.fantasi.wireframe.Wireframe;

import java.util.ArrayList;
import java.util.List;

public class LayeredBuilderPattern implements BluePrintBuilderPattern {
	private String name;
	private Wireframe wireframe;
	private final List<BluePrintBuilderFactory.BluePrintBuilder> layers = new ArrayList<BluePrintBuilderFactory.BluePrintBuilder>();

	private LayeredBuilderPattern() {
	}

	public static LayeredBuilderPattern layered() {
		return new LayeredBuilderPattern();
	}

	@Override
	public void initialise(String name, Wireframe wireframe) {
		this.name = name;
		this.wireframe = wireframe;
	}

	public LayeredBuilderPattern withLayer(BluePrintBuilderFactory.BluePrintBuilder layer) {
		layers.add(layer);
		return this;
	}

	@Override
	public BluePrint buildBlueprint() {
		LayeredElementsBluePrint layeredElementsBluePrint = new LayeredElementsBluePrint(name, wireframe);
		for (BluePrintBuilderFactory.BluePrintBuilder layer : layers) {
			layeredElementsBluePrint.withLayer(layer.build());
		}
		return layeredElementsBluePrint;
	}
}
