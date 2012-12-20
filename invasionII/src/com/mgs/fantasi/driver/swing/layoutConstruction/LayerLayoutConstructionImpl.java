package com.mgs.fantasi.driver.swing.layoutConstruction;

import com.mgs.fantasi.rendering.Renderable;
import com.mgs.fantasi.rendering.structure.layer.LayerIterator;
import com.mgs.fantasi.rendering.structure.layer.LayeredStructure;

public class LayerLayoutConstructionImpl extends OnGoingLayoutConstructionBaseImpl<Integer>{
	public LayerLayoutConstructionImpl(LayoutProvider layoutProvider) {
		super(layoutProvider);
	}

	public LayerLayoutConstructionImpl from(LayeredStructure<Renderable> content) {
		content.iterateInCrescendo(new LayerIterator<Renderable>() {
			@Override
			public void on(int zIndex, Renderable layer) {
				add(layer).into(zIndex);
			}
		});
		return this;
	}
}
