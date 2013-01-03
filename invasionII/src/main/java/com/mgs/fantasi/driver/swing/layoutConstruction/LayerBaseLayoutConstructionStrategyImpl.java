package com.mgs.fantasi.driver.swing.layoutConstruction;

import com.mgs.fantasi.views.View;
import com.mgs.fantasi.wireframe.LayeredWireframe;
import com.mgs.fantasi.wireframe.layer.LayerIterator;

public class LayerBaseLayoutConstructionStrategyImpl extends BaseLayoutConstructionStrategyStrategy<Integer, LayeredWireframe> {
	public LayerBaseLayoutConstructionStrategyImpl(LayoutProvider layoutProvider) {
		super(layoutProvider);
	}

	@Override
	public LayoutConstructionStrategy<Integer, LayeredWireframe> fillWith(LayeredWireframe content) {
		content.iterateInCrescendo(new LayerIterator() {
			@Override
			public void on(int zIndex, View layer) {
				queueForAddition(layer, zIndex);
			}
		});
		return this;
	}
}
