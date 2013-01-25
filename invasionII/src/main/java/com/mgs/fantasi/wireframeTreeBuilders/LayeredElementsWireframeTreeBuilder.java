package com.mgs.fantasi.wireframeTreeBuilders;

import com.mgs.fantasi.wireframe.CollocationInfo;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.WireframeContentFactory;
import com.mgs.tree.Branch;
import com.mgs.tree.Tree;

import java.util.ArrayList;
import java.util.List;

import static com.mgs.fantasi.properties.measurements.Fractions.all;

public class LayeredElementsWireframeTreeBuilder extends BaseWireframeTreeBuilder {
	private List<WireframeTreeBuilder> layers = new ArrayList<WireframeTreeBuilder>();

	public LayeredElementsWireframeTreeBuilder withLayer(WireframeTreeBuilder layer) {
		layers.add(layer);
		return this;
	}

	@Override
	public Tree<Wireframe, CollocationInfo> build(WireframeContentFactory wireframeContentFactory) {
		Branch<Wireframe, CollocationInfo> wireframeBranch = new Branch<Wireframe, CollocationInfo>(wireframeContentFactory.getLayeredConnectionManager());
		for (int i = layers.size() - 1; i >= 0; i--) {
			WireframeTreeBuilder layerBuilder = layers.get(i);
			Tree<Wireframe, CollocationInfo> layer = layerBuilder.build(wireframeContentFactory);
			wireframeBranch.addChild(new CollocationInfo(i, all(), all(), 0, 0), layer);
		}

		Wireframe wireframe = new Wireframe(this.getClass(), getName(), getUiProperties());
		return new Tree<Wireframe, CollocationInfo>(wireframe, wireframeBranch);
	}
}
