package com.mgs.fantasi.wireframe;

import com.mgs.fantasi.wireframe.layer.LayerIterator;

import java.util.List;

public class LayeredWireframe<T> implements Wireframe<T> {
	private List<T> layers;

	@Override
	public WireframeType getType() {
		return WireframeType.LAYERS;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	public Wireframe<T> withLayers(List<T> layers) {
		this.layers = layers;
		return this;
	}

	public void iterateInCrescendo(LayerIterator<T> layerIterator) {
		int zIndex = 0;
		for (int i = layers.size() - 1; i >= 0; i--) {
			layerIterator.on(zIndex, layers.get(i));
			zIndex++;
		}
	}
}
