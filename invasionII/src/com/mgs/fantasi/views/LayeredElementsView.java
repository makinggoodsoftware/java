package com.mgs.fantasi.views;

import com.mgs.fantasi.ui.wireframe.*;

import java.util.ArrayList;
import java.util.List;

public class LayeredElementsView extends BaseView {
	private List<View> layers = new ArrayList<View>();

	public static LayeredElementsView layered() {
		return new LayeredElementsView();
	}

	public LayeredElementsView withLayer (View layer){
		layers.add(layer);
		return this;
	}

	@Override
	public boolean renderConstraintsAreSatisfied() {
		return true;
	}

	@Override
	public Structure<View> getContent() {
		List<View> layers = new ArrayList<View>();
		for (View layer : this.layers) {
			layers.add(layer);
		}
		return new Layers<View>(layers);
	}

	@Override
	public StructureFactory.StructureType getContentStructureType() {
		return null;
	}

	@Override
	public ContentStructureStrategy getContentStructureStrategy() {
		return new LayeredContentStructureStrategy() {
			@Override
			public List<View> getLayers() {
				List<View> layers = new ArrayList<View>();
				for (View layer : LayeredElementsView.this.layers) {
					layers.add(layer);
				}
				return layers;
			}
		};
	}

	@Override
	public ReadyForRendering createRenderingStructure() {
		return
			new LayeredStructureBuilder().
			withLayers(layers).
			produce();
	}

	@Override
	protected BaseView copy() {
		LayeredElementsView copy = new LayeredElementsView();
		for (View layer : layers) {
			copy.withLayer(layer.newCopy());
		}
		return copy;
	}
}
