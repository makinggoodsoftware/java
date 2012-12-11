package com.mgs.fantasi.views;

import com.mgs.fantasi.ui.wireframe.Layers;
import com.mgs.fantasi.ui.wireframe.Structure;
import com.mgs.fantasi.ui.wireframe.Wireframe;

import java.util.ArrayList;
import java.util.List;

public class LayeredElementsView extends BaseView {
	private List<StructureBuilder> layers = new ArrayList<StructureBuilder>();

	public static LayeredElementsView layered() {
		return new LayeredElementsView();
	}

	public LayeredElementsView withLayer (StructureBuilder layer){
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

	@Override
	protected BaseView copy() {
		LayeredElementsView copy = new LayeredElementsView();
		for (StructureBuilder layer : layers) {
			copy.withLayer(layer.newCopy());
		}
		return copy;
	}
}
