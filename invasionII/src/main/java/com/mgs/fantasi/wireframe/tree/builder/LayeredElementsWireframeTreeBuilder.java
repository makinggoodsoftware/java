package com.mgs.fantasi.wireframe.tree.builder;

import com.mgs.fantasi.wireframe.CollocationInfo;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.tree.WireframeTree;

import java.util.ArrayList;
import java.util.List;

import static com.mgs.fantasi.properties.data.measurements.Fractions.all;
import static com.mgs.fantasi.wireframe.tree.WireframeTreeFactory.layered;

public class LayeredElementsWireframeTreeBuilder implements WireframeTreeBuilder {
	private final List<WireframeTreeBuilder> layers = new ArrayList<WireframeTreeBuilder>();
	private final String name;
	private final Wireframe wireframe;

	public LayeredElementsWireframeTreeBuilder(String name, Wireframe wireframe) {
		this.wireframe = wireframe;
		this.name = name;
	}


	public LayeredElementsWireframeTreeBuilder withLayer(WireframeTreeBuilder layer) {
		layers.add(layer);
		return this;
	}

	@Override
	public WireframeTree build() {
		WireframeTree wireframeTree = layered(wireframe, name, this.getClass());
		for (int i = layers.size() - 1; i >= 0; i--) {
			WireframeTreeBuilder layerBuilder = layers.get(i);
			WireframeTree layer = layerBuilder.build();
			wireframeTree.addChild(new CollocationInfo(i, all(), all(), 0, 0), layer);
		}

		return wireframeTree;
	}

	@Override
	public Wireframe getRootWireframe() {
		return wireframe;
	}

	@Override
	public String getName() {
		return name;
	}
}
