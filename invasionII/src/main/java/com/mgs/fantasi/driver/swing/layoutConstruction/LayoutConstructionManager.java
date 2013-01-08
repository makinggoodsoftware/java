package com.mgs.fantasi.driver.swing.layoutConstruction;

import com.mgs.fantasi.views.View;
import com.mgs.fantasi.wireframe.*;

public class LayoutConstructionManager {
	private final LayoutConstructionStrategyFactory layoutConstructionStrategyFactory;

	public LayoutConstructionManager(LayoutConstructionStrategyFactory layoutConstructionStrategyFactory) {
		this.layoutConstructionStrategyFactory = layoutConstructionStrategyFactory;
	}

	public LayoutConstructionStrategy<?, ? extends Wireframe> createAndFillLayout(Wireframe<View> from) {
		switch (from.getType()) {
			case GRID:
				return layoutConstructionStrategyFactory.grid().fillWith((GridWireframe<View>) from);
			case LAYERS:
				return layoutConstructionStrategyFactory.layers().fillWith((LayeredWireframe<View>) from);
			case SIMPLE:
				return layoutConstructionStrategyFactory.simple().fillWith((RectangleWireframe<View>) from);
			case DELEGATE:
				return createAndFillLayout(((DelegateWireframe<View>) from).getContent());
			case EMPTY:
				return layoutConstructionStrategyFactory.empty();
			default:
				throw new RuntimeException("Can't process the structure: " + from);
		}
	}

	public LayoutProvider translateTypeIntoLayoutProvider(Wireframe<View> from) {
		switch (from.getType()) {
			case GRID:
			case SIMPLE:
				return layoutConstructionStrategyFactory.gridLayoutProvider();
			case LAYERS:
				return layoutConstructionStrategyFactory.layerLayoutProvider();
			case DELEGATE:
				return translateTypeIntoLayoutProvider(((DelegateWireframe<View>) from).getContent());
			case EMPTY:
				return layoutConstructionStrategyFactory.emptyLayoutProvider();
			default:
				throw new RuntimeException("Can't process the structure: " + from);
		}
	}
}
