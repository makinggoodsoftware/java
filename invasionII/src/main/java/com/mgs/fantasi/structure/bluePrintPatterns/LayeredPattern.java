package com.mgs.fantasi.structure.bluePrintPatterns;

import com.mgs.fantasi.structure.BasicBluePrintBuildersFactory;
import com.mgs.fantasi.structure.bluePrint.BluePrint;
import com.mgs.fantasi.structure.bluePrint.LayeredElementsBluePrint;
import com.mgs.fantasi.wireframe.Wireframe;

import java.util.ArrayList;
import java.util.List;

public class LayeredPattern implements BasicBluePrintBuildersFactory.BluePrintBuilder {
	private String name;
	private Wireframe wireframe;
	private final List<BluePrint> layers = new ArrayList<BluePrint>();

	@Override
	public void initialise(String name, Wireframe wireframe) {
		this.name = name;
		this.wireframe = wireframe;
	}

	public LayeredPattern withLayer(BluePrint layer) {
		layers.add(layer);
		return this;
	}

	@Override
	public BluePrint buildBlueprint() {
		LayeredElementsBluePrint layeredElementsBluePrint = new LayeredElementsBluePrint(name, wireframe);
		for (BluePrint layer : layers) {
			layeredElementsBluePrint.withLayer(layer);
		}
		return layeredElementsBluePrint;
	}
}
