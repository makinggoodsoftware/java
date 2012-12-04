package com.mgs.fantasi.structures;

import com.mgs.fantasi.ui.wireframe.Layers;
import com.mgs.fantasi.ui.wireframe.Structure;
import com.mgs.fantasi.ui.wireframe.Wireframe;

import java.util.ArrayList;
import java.util.List;

public class LayeredElementStructureBuilder extends BaseStructureBuilder {
	private List<StructureBuilder> layers = new ArrayList<StructureBuilder>();

	public static LayeredElementStructureBuilder layered() {
		return new LayeredElementStructureBuilder();
	}

	public LayeredElementStructureBuilder withLayer (StructureBuilder layer){
		layers.add(layer);
		return this;
	}

	@Override
	protected boolean constraintsAreSatisfied() {
		return true;
	}

	@Override
	protected Structure buildLayoutAndChilds() {
		List<Wireframe> layersAsWireframes = new ArrayList<Wireframe>();
		for (StructureBuilder layer : layers) {
			layersAsWireframes.add(layer.build());
		}
		return new Layers<Wireframe>(layersAsWireframes);
	}
}
