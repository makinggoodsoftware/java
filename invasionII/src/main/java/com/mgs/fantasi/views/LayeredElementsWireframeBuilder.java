package com.mgs.fantasi.views;

import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.WireframeContentFactory;

import java.util.ArrayList;
import java.util.List;

public class LayeredElementsWireframeBuilder extends BaseWireframeBuilder {
	private List<WireframeBuilder> layers = new ArrayList<WireframeBuilder>();

	public static LayeredElementsWireframeBuilder layered() {
		return new LayeredElementsWireframeBuilder();
	}

	public LayeredElementsWireframeBuilder withLayer(WireframeBuilder layer) {
		layers.add(layer);
		return this;
	}

	@Override
	public Wireframe build(WireframeContentFactory wireframeContentFactory) {
		List<Wireframe> layersBuilt = new ArrayList<Wireframe>();
		for (WireframeBuilder layer : layers) {
			layersBuilt.add(layer.build(wireframeContentFactory));
		}
		return new Wireframe(wireframeContentFactory.layered(layersBuilt), getUiProperties(), getName(), this.getClass());
	}
}
