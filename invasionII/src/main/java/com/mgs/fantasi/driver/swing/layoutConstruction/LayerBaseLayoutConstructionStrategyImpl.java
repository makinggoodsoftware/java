package com.mgs.fantasi.driver.swing.layoutConstruction;

import com.mgs.fantasi.rendering.wireframe.LayeredWireframe;
import com.mgs.fantasi.rendering.wireframe.layer.LayerIterator;
import com.mgs.fantasi.views.View;

public class LayerBaseLayoutConstructionStrategyImpl extends BaseLayoutConstructionStrategyStrategy<Integer, LayeredWireframe> {
	public LayerBaseLayoutConstructionStrategyImpl(LayoutProvider layoutProvider) {
		super(layoutProvider);
	}

    @Override
    public LayoutConstructionStrategy<Integer, LayeredWireframe> fillWith(LayeredWireframe content) {
        content.iterateInCrescendo(new LayerIterator() {
			@Override
			public void on(int zIndex, View layer) {
				queueForAddition(layer).into(zIndex);
			}
		});
		return this;
    }
}
