package com.mgs.fantasi.ui.wireframe;

import java.util.List;

public class Layers<T extends Structurable> implements Structure<T> {
	private final List<T> layers;

	public Layers(List<T> layers) {
		this.layers = layers;
	}

	public void iterateInCrescendo(LayerIterator layerIterator) {
		int zIndex = 0;
		for (int i = layers.size() - 1; i >= 0; i--){
			layerIterator.on (zIndex, layers.get(i));
			zIndex++;
		}
	}

	@Override
	public String toString() {
		return "Layers{" +
				"layers=" + layers +
				'}';
	}

	@Override
	public StructureFactory.StructureType getType() {
		return StructureFactory.StructureType.LAYERS;
	}
}
