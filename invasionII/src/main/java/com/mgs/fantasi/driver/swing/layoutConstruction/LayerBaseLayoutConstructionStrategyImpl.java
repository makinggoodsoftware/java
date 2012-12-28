package com.mgs.fantasi.driver.swing.layoutConstruction;

import com.mgs.fantasi.rendering.Renderable;
import com.mgs.fantasi.rendering.structure.layer.LayerIterator;
import com.mgs.fantasi.rendering.wireframe.LayeredWireframe;

public class LayerBaseLayoutConstructionStrategyImpl extends BaseLayoutConstructionStrategyStrategy<Integer> {
	public LayerBaseLayoutConstructionStrategyImpl(LayoutProvider layoutProvider) {
		super(layoutProvider);
	}

	public LayerBaseLayoutConstructionStrategyImpl from(LayeredWireframe<Renderable> content) {
		content.iterateInCrescendo(new LayerIterator<Renderable>() {
			@Override
			public void on(int zIndex, Renderable layer) {
				queueForAddition(layer).into(zIndex);
			}
		});
		return this;
	}
}
