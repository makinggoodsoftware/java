package com.mgs.fantasi.rendering.wireframe;

import com.mgs.fantasi.Structurable;
import com.mgs.fantasi.rendering.structure.layer.LayeredStructure;
import com.mgs.fantasi.rendering.MyViewPreprocessor;
import com.mgs.fantasi.rendering.structure.Structure;

import java.util.ArrayList;
import java.util.List;

public class LayeredWireframe<T extends Structurable> implements Wireframe<T> {
	private List<T> layers;

	@Override
	public Structure<T> build() {
		return new LayeredStructure<T>(layers);
	}

	@Override
	public <Z extends Structurable> Wireframe<Z> transform(MyViewPreprocessor.WireframeTransformer<T, Z> transformer) {
		List<Z> transformedLayers = new ArrayList<Z>();
		for (T layer : layers) {
			transformedLayers.add(transformer.transform(layer));
		}
		return new LayeredWireframe<Z>().withLayers(transformedLayers);
	}

	public Wireframe<T> withLayers(List<T> layers) {
		this.layers = layers;
		return this;
	}
}
