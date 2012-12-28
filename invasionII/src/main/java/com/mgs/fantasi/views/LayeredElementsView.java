package com.mgs.fantasi.views;

import com.mgs.fantasi.rendering.wireframe.LayeredWireframe;
import com.mgs.fantasi.rendering.wireframe.Wireframe;

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
	public Wireframe<View> buildChildViews() {
		return new LayeredWireframe<View>().
			withLayers(layers);
	}
}
