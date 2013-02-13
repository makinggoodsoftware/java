package com.mgs.fantasi.wireframe.builder;

import com.mgs.fantasi.wireframe.CollocationInfo;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.WireframeContainer;

import java.util.ArrayList;
import java.util.List;

import static com.mgs.fantasi.properties.data.measurements.Fractions.all;
import static com.mgs.fantasi.wireframe.WireframeContainerFactory.layered;

public class LayeredElementsWireframeTreeBuilder extends BaseWireframeTreeBuilder {
	private List<WireframeTreeBuilder> layers = new ArrayList<WireframeTreeBuilder>();

	public LayeredElementsWireframeTreeBuilder withLayer(WireframeTreeBuilder layer) {
		layers.add(layer);
		return this;
	}

	@Override
	public WireframeContainer build() {
		Wireframe wireframe = new Wireframe(getUiPropertiesBuilder().build());
		WireframeContainer wireframeContainer = layered(wireframe, getName(), this.getClass());
		for (int i = layers.size() - 1; i >= 0; i--) {
			WireframeTreeBuilder layerBuilder = layers.get(i);
			WireframeContainer layer = layerBuilder.build();
			wireframeContainer.addChild(new CollocationInfo(i, all(), all(), 0, 0), layer);
		}

		return wireframeContainer;
	}
}
