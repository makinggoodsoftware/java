package com.mgs.fantasi.driver.swing.layoutConstruction;

import com.mgs.fantasi.rendering.Renderable;
import com.mgs.fantasi.rendering.structure.layer.LayerIterator;
import com.mgs.fantasi.rendering.structure.layer.LayeredStructure;

public class LayerBaseLayoutConstructionStrategyImpl extends BaseLayoutConstructionStrategyStrategy<Integer> {
	public LayerBaseLayoutConstructionStrategyImpl(LayoutProvider layoutProvider) {
		super(layoutProvider);
	}

	public LayerBaseLayoutConstructionStrategyImpl from(LayeredStructure<Renderable> content) {
		content.iterateInCrescendo(new LayerIterator<Renderable>() {
			@Override
			public void on(int zIndex, Renderable layer) {
				queueForAddition(layer).into(zIndex);
			}
		});
		return this;
	}
}
