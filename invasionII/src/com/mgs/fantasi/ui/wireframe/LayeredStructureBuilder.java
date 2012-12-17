package com.mgs.fantasi.ui.wireframe;

import java.util.ArrayList;
import java.util.List;

public class LayeredStructureBuilder<T extends Structurable> implements StructureBuilder<T>{
	private List<T> layers;

	@Override
	public Structure<T> build() {
		return new Layers<T>(layers);
	}

	@Override
	public <Z extends Structurable> StructureBuilder<Z> transform(MyRenderer.StructureBuilderTransformer<T, Z> transformer) {
		List<Z> transformedLayers = new ArrayList<Z>();
		for (T layer : layers) {
			transformedLayers.add(transformer.transform(layer));
		}
		return new LayeredStructureBuilder<Z>().withLayers(transformedLayers);
	}

	public StructureBuilder<T> withLayers(List<T> layers) {
		this.layers = layers;
		return this;
	}
}
