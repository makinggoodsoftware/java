package com.mgs.fantasi.views;

import com.mgs.fantasi.wireframe.Tree;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.WireframeContentFactory;

import java.util.ArrayList;
import java.util.List;

public class LayeredElementsWireframeTreeBuilder extends BaseWireframeTreeBuilder {
	private List<WireframeTreeBuilder> layers = new ArrayList<WireframeTreeBuilder>();

	public LayeredElementsWireframeTreeBuilder withLayer(WireframeTreeBuilder layer) {
		layers.add(layer);
		return this;
	}

	@Override
	public Tree<Wireframe> build(WireframeContentFactory wireframeContentFactory) {
		List<Tree<Wireframe>> layersBuilt = new ArrayList<Tree<Wireframe>>();
		for (WireframeTreeBuilder layer : layers) {
			layersBuilt.add(layer.build(wireframeContentFactory));
		}
		Wireframe wireframe = new Wireframe(this.getClass(), getName(), getUiProperties());
		return new Tree<Wireframe>(wireframe, wireframeContentFactory.layered(layersBuilt));
	}
}
