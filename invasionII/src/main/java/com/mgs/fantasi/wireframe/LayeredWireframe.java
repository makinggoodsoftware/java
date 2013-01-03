package com.mgs.fantasi.wireframe;

import com.mgs.fantasi.views.View;
import com.mgs.fantasi.wireframe.layer.LayerIterator;

import java.util.List;

public class LayeredWireframe implements Wireframe {
	private List<View> layers;

	@Override
	public WireframeType getType() {
		return WireframeType.LAYERS;
	}

	public Wireframe withLayers(List<View> layers) {
		this.layers = layers;
		return this;
	}

	public void iterateInCrescendo(LayerIterator layerIterator) {
		int zIndex = 0;
		for (int i = layers.size() - 1; i >= 0; i--) {
			layerIterator.on(zIndex, layers.get(i));
			zIndex++;
		}
	}
}
