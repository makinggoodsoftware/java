package com.mgs.fantasi.views;

import com.mgs.fantasi.ui.wireframe.Layers;
import com.mgs.fantasi.ui.wireframe.Structure;
import com.mgs.fantasi.ui.wireframe.Wireframe;

import java.util.ArrayList;
import java.util.List;

public class LayeredElementsView extends BaseView {
	private List<View> layers = new ArrayList<View>();

	public static LayeredElementsView layered() {
		return new LayeredElementsView();
	}

	public LayeredElementsView withLayer (View layer){
		layers.add(layer);
		return this;
	}

	@Override
	public boolean constraintsAreSatisfied() {
		return true;
	}

	@Override
	public Structure buildLayoutAndChilds() {
		List<Wireframe> layersAsWireframes = new ArrayList<Wireframe>();
		for (View layer : layers) {
			layersAsWireframes.add(layer.render());
		}
		return new Layers<Wireframe>(layersAsWireframes);
	}

	@Override
	protected BaseView copy() {
		LayeredElementsView copy = new LayeredElementsView();
		for (View layer : layers) {
			copy.withLayer(layer.newCopy());
		}
		return copy;
	}
}
