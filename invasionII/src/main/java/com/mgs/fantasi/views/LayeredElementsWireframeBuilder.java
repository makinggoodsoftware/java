package com.mgs.fantasi.views;

import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.WireframeContentFactory;
import com.mgs.fantasi.wireframe.WireframeTree;

import java.util.ArrayList;
import java.util.List;

public class LayeredElementsWireframeBuilder extends BaseWireframeBuilder {
	private List<WireframeBuilder> layers = new ArrayList<WireframeBuilder>();

	public LayeredElementsWireframeBuilder withLayer(WireframeBuilder layer) {
		layers.add(layer);
		return this;
	}

	@Override
	public WireframeTree build(WireframeContentFactory wireframeContentFactory) {
		List<WireframeTree> layersBuilt = new ArrayList<WireframeTree>();
		for (WireframeBuilder layer : layers) {
			layersBuilt.add(layer.build(wireframeContentFactory));
		}
		Wireframe wireframe = new Wireframe(this.getClass(), getName(), getUiProperties());
		return new WireframeTree(wireframe, wireframeContentFactory.layered(layersBuilt));
	}
}
