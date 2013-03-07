package com.mgs.fantasi.wireframe.tree.builder;

import com.mgs.fantasi.wireframe.CollocationInfo;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.tree.Structure;

import java.util.ArrayList;
import java.util.List;

import static com.mgs.fantasi.properties.data.measurements.Fractions.all;
import static com.mgs.fantasi.wireframe.tree.WireframeTreeFactory.layered;

public class LayeredElementsBluePrint implements BluePrint {
	private final List<BluePrint> layers = new ArrayList<BluePrint>();
	private final String name;
	private final Wireframe wireframe;

	public LayeredElementsBluePrint(String name, Wireframe wireframe) {
		this.wireframe = wireframe;
		this.name = name;
	}


	public LayeredElementsBluePrint withLayer(BluePrint layer) {
		layers.add(layer);
		return this;
	}

	@Override
	public Structure build() {
		Structure structure = layered(wireframe, name, this.getClass());
		for (int i = layers.size() - 1; i >= 0; i--) {
			BluePrint layerBuilder = layers.get(i);
			Structure layer = layerBuilder.build();
			structure.addChild(new CollocationInfo(i, all(), all(), 0, 0), layer);
		}

		return structure;
	}

	@Override
	public Wireframe getRootWireframe() {
		return wireframe;
	}

	@Override
	public String getName() {
		return name;
	}
}
