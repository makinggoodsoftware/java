package com.mgs.fantasi.views;

import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.WireframeContentFactory;
import com.mgs.fantasi.wireframe.WireframeTree;

import java.util.ArrayList;
import java.util.List;

public class LayeredElementsWireframeTreeBuilder extends BaseWireframeTreeBuilder {
	private List<WireframeTreeBuilder> layers = new ArrayList<WireframeTreeBuilder>();

	public LayeredElementsWireframeTreeBuilder withLayer(WireframeTreeBuilder layer) {
		layers.add(layer);
		return this;
	}

	@Override
	public WireframeTree build(WireframeContentFactory wireframeContentFactory) {
		List<WireframeTree> layersBuilt = new ArrayList<WireframeTree>();
		for (WireframeTreeBuilder layer : layers) {
			layersBuilt.add(layer.build(wireframeContentFactory));
		}
		Wireframe wireframe = new Wireframe(this.getClass(), getName(), getUiProperties());
		return new WireframeTree(wireframe, wireframeContentFactory.layered(layersBuilt));
	}
}
