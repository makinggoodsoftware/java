package com.mgs.fantasi.driver.swing.layoutConstruction;

import com.mgs.fantasi.rendering.wireframe.*;

public class LayoutConstructionManager {
    private final LayoutConstructionStrategyFactory layoutConstructionStrategyFactory;

    public LayoutConstructionManager(LayoutConstructionStrategyFactory layoutConstructionStrategyFactory) {
        this.layoutConstructionStrategyFactory = layoutConstructionStrategyFactory;
    }

    public LayoutConstructionStrategy<?, ? extends Wireframe> createAndFillLayout(Wireframe from) {
		switch (from.getType()){
			case GRID:
				return layoutConstructionStrategyFactory.grid().fillWith((GridWireframe) from);
			case LAYERS:
				return layoutConstructionStrategyFactory.layers().fillWith((LayeredWireframe) from);
			case SIMPLE:
				return layoutConstructionStrategyFactory.simple().fillWith((RectangleWireframe) from);
			case DELEGATE:
                return createAndFillLayout(((DelegateWireframe) from).getContent());
			case EMPTY:
				return layoutConstructionStrategyFactory.empty();
			default:
				throw new RuntimeException("Can't process the structure: " + from);
		}
	}
}
