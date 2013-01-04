package com.mgs.fantasi.driver.swing.layoutConstruction;

import com.mgs.fantasi.views.View;
import com.mgs.fantasi.wireframe.LayeredWireframe;
import com.mgs.fantasi.wireframe.layer.LayerIterator;

public class LayerBaseLayoutConstructionStrategyImpl extends BaseLayoutConstructionStrategy<Integer, LayeredWireframe<View>> {
	public LayerBaseLayoutConstructionStrategyImpl(LayoutProvider layoutProvider) {
		super(layoutProvider);
	}

	@Override
	public LayoutConstructionStrategy<Integer, LayeredWireframe<View>> fillWith(LayeredWireframe<View> content) {
		content.iterateInCrescendo(new LayerIterator<View>() {
			@Override
			public void on(int zIndex, View layer) {
				queueForAddition(layer, zIndex);
			}
		});
		return this;
	}
}
