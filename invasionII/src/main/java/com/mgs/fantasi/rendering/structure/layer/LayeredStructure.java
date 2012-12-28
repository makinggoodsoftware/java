package com.mgs.fantasi.rendering.structure.layer;

import com.mgs.fantasi.Structurable;
import com.mgs.fantasi.rendering.structure.Structure;
import com.mgs.fantasi.rendering.structure.StructureType;

import java.util.List;

public class LayeredStructure<T extends Structurable> implements Structure<T> {
	private final List<T> layers;

	public LayeredStructure(List<T> layers) {
		this.layers = layers;
	}

	public void iterateInCrescendo(LayerIterator<T> layerIterator) {
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
	public StructureType getType() {
		return StructureType.LAYERS;
	}
}
