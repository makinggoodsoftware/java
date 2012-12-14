package com.mgs.fantasi.ui.wireframe;

import com.mgs.fantasi.views.View;

import java.util.List;

public class LayeredStructureBuilder implements StructureBuilder{
	private List<View> layers;

	@Override
	public ReadyForRendering produce() {
		return new ReadyForRendering(this);
	}

	public StructureBuilder withLayers(List<View> layers) {
		this.layers = layers;
		return this;
	}
}
