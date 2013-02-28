package com.mgs.fantasi.wireframe.tree.builder;

import com.mgs.fantasi.properties.UIPropertiesBuilder;
import com.mgs.fantasi.properties.data.polygon.PolygonPointsIterator;
import com.mgs.fantasi.wireframe.CollocationInfo;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.tree.WireframeTree;

import java.util.ArrayList;
import java.util.List;

import static com.mgs.fantasi.properties.data.measurements.Fractions.all;
import static com.mgs.fantasi.wireframe.tree.WireframeTreeFactory.layered;

public class LayeredElementsWireframeTreeBuilder implements WireframeTreeBuilder {
	private final List<WireframeTreeBuilder> layers = new ArrayList<WireframeTreeBuilder>();
	private final UIPropertiesBuilder uiPropertiesBuilder;
	private final String name;
	private final PolygonPointsIterator shape;

	public LayeredElementsWireframeTreeBuilder(String name, PolygonPointsIterator shape, UIPropertiesBuilder uiPropertiesBuilder) {
		this.shape = shape;
		this.uiPropertiesBuilder = uiPropertiesBuilder;
		this.name = name;
	}


	public LayeredElementsWireframeTreeBuilder withLayer(WireframeTreeBuilder layer) {
		layers.add(layer);
		return this;
	}

	@Override
	public WireframeTree build() {
		Wireframe wireframe = new Wireframe(uiPropertiesBuilder.build(), shape);
		WireframeTree wireframeTree = layered(wireframe, name, this.getClass());
		for (int i = layers.size() - 1; i >= 0; i--) {
			WireframeTreeBuilder layerBuilder = layers.get(i);
			WireframeTree layer = layerBuilder.build();
			wireframeTree.addChild(new CollocationInfo(i, all(), all(), 0, 0), layer);
		}

		return wireframeTree;
	}

	@Override
	public UIPropertiesBuilder getUiPropertiesBuilder() {
		return uiPropertiesBuilder;
	}

	@Override
	public String getName() {
		return name;
	}
}
