package com.mgs.fantasi.structure.structureBuilder.Layout;

import com.mgs.fantasi.structure.CollocationInfo;
import com.mgs.fantasi.structure.Structure;
import com.mgs.fantasi.structure.structureBuilder.StructureBuilder;
import com.mgs.fantasi.wireframe.Wireframe;

import java.util.ArrayList;
import java.util.List;

import static com.mgs.fantasi.properties.data.measurements.Fractions.all;
import static com.mgs.fantasi.structure.Structures.layeredStructure;

public class LayeredLayout implements StructureLayout {
	private final List<StructureBuilder> layers = new ArrayList<StructureBuilder>();

	private LayeredLayout() {
	}

	public static LayeredLayout layered() {
		return new LayeredLayout();
	}

	public LayeredLayout withLayer(StructureBuilder layer) {
		layers.add(layer);
		return this;
	}

	@Override
	public Structure buildStructure(String name, Wireframe wireframe) {
		Structure structure = layeredStructure(wireframe, name, this.getClass());
		for (int i = layers.size() - 1; i >= 0; i--) {
			StructureBuilder layerBuilder = layers.get(i);
			structure.addChild(new CollocationInfo(i, all(), all(), 0, 0), layerBuilder.build());
		}

		return structure;
	}
}
