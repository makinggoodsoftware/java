package com.mgs.fantasi.driver.swing.layoutConstruction;

import com.mgs.fantasi.views.View;
import com.mgs.fantasi.wireframe.DelegateWireframe;
import com.mgs.fantasi.wireframe.Wireframe;

public class LayoutConstructionManager {
	private final LayoutConstructionStrategyFactory layoutConstructionStrategyFactory;

	public LayoutConstructionManager(LayoutConstructionStrategyFactory layoutConstructionStrategyFactory) {
		this.layoutConstructionStrategyFactory = layoutConstructionStrategyFactory;
	}

	public LayoutProvider translateTypeIntoLayoutProvider(Wireframe<View> from) {
		switch (from.getType()) {
			case GRID:
			case SIMPLE:
				return layoutConstructionStrategyFactory.gridLayoutProvider();
			case LAYERS:
				return layoutConstructionStrategyFactory.layerLayoutProvider();
			case DELEGATE:
				return translateTypeIntoLayoutProvider(((DelegateWireframe<View>) from).getContent().buildContent());
			case EMPTY:
				return layoutConstructionStrategyFactory.emptyLayoutProvider();
			default:
				throw new RuntimeException("Can't process the structure: " + from);
		}
	}
}
