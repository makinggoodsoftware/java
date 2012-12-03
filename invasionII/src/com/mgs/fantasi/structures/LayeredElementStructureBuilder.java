package com.mgs.fantasi.structures;

import com.mgs.fantasi.ui.wireframe.Layers;
import com.mgs.fantasi.ui.wireframe.Structure;
import com.mgs.fantasi.ui.wireframe.Wireframe;

import java.util.ArrayList;
import java.util.List;

public class LayeredElementStructureBuilder extends BaseStructureBuilder {
	private final StructureBuilder[] layers;

	public LayeredElementStructureBuilder(StructureBuilder... layers) {
		this.layers = layers;
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
