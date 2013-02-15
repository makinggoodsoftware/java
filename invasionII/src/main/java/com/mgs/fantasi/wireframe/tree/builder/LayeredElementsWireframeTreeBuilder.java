package com.mgs.fantasi.wireframe.tree.builder;

import com.mgs.fantasi.wireframe.CollocationInfo;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.tree.WireframeTree;

import java.util.ArrayList;
import java.util.List;

import static com.mgs.fantasi.properties.data.measurements.Fractions.all;
import static com.mgs.fantasi.wireframe.tree.WireframeTreeFactory.layered;

public class LayeredElementsWireframeTreeBuilder extends BaseWireframeTreeBuilder {
	private List<WireframeTreeBuilderOld> layers = new ArrayList<WireframeTreeBuilderOld>();

	public LayeredElementsWireframeTreeBuilder withLayer(WireframeTreeBuilderOld layer) {
		layers.add(layer);
		return this;
	}

	@Override
	public WireframeTree build() {
		Wireframe wireframe = new Wireframe(getUiPropertiesBuilder().build());
		WireframeTree wireframeTree = layered(wireframe, getName(), this.getClass());
		for (int i = layers.size() - 1; i >= 0; i--) {
			WireframeTreeBuilderOld layerBuilder = layers.get(i);
			WireframeTree layer = layerBuilder.build();
			wireframeTree.addChild(new CollocationInfo(i, all(), all(), 0, 0), layer);
		}

		return wireframeTree;
	}
}
