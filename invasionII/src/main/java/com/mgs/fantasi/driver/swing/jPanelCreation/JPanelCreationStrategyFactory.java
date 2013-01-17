package com.mgs.fantasi.driver.swing.jPanelCreation;

import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.wireframe.WireframeContentType;

public class JPanelCreationStrategyFactory {
	public JPanelCreationStrategy forUIProperties(UIProperties uiProperties, WireframeContentType contentType) {
		JPanelCreationStrategy jPanelCreationStrategy = uiProperties.getShape().isRectangular() ?
				new StandardJPanelCreationStrategy(uiProperties, contentType) :
				new NonRectangularJPanelCreationStrategy(uiProperties, contentType);

		return uiProperties.getPadding().isEmpty() ?
				jPanelCreationStrategy :
				new DecoratedJPanelWithPadding(jPanelCreationStrategy, uiProperties.getPadding());
	}
}
