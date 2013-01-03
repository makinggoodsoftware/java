package com.mgs.fantasi.driver.swing;

import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelCreationStrategy;
import com.mgs.fantasi.driver.swing.layoutConstruction.LayoutConstructionStrategy;
import com.mgs.fantasi.wireframe.Wireframe;

public class JPanelCreationInstructions {
	private final JPanelCreationStrategy outsideCreationStrategy;
	private final LayoutConstructionStrategy<?, ? extends Wireframe> insideConstructionStrategy;

	public JPanelCreationInstructions(JPanelCreationStrategy outsideCreationStrategy, LayoutConstructionStrategy<?, ? extends Wireframe> insideConstructionStrategy) {
		this.outsideCreationStrategy = outsideCreationStrategy;
		this.insideConstructionStrategy = insideConstructionStrategy;
	}

	public JPanelCreationStrategy getOutsideCreationStrategy() {
		return outsideCreationStrategy;
	}

	public LayoutConstructionStrategy<?, ? extends Wireframe> getInsideConstructionStrategy() {
		return insideConstructionStrategy;
	}
}
