package com.mgs.fantasi.driver.swing.jPanelCreation;

import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.wireframe.WireframeType;

public class JPanelCreationStrategyFactory {
	public JPanelCreationStrategy forUIProperties(UIProperties uiProperties, WireframeType type) {
		JPanelCreationStrategy jPanelCreationStrategy = uiProperties.getShape().isRectangular() ?
				new StandardJPanelCreationStrategy(uiProperties, type) :
				new NonRectangularJPanelCreationStrategy(uiProperties, type);


		return uiProperties.getPadding().isEmpty() ?
				jPanelCreationStrategy :
				new DecoratedJPanelWithPadding(jPanelCreationStrategy, uiProperties.getPadding());
	}
}
