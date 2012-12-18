package com.mgs.fantasi.views;

import com.mgs.fantasi.ui.wireframe.*;

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
	public boolean renderConstraintsAreSatisfied() {
		return true;
	}

	@Override
	public StructureBuilder<View> getChildStructure() {
		return new LayeredStructureBuilder<View>().
			withLayers(layers);
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
