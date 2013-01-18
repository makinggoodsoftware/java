package com.mgs.fantasi.driver.swing.jPanelCreation;

import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.wireframe.WireframeContentType;

public class JPanelCreationStrategyFactory {
	private final JPanelLayoutTranslator jPanelLayoutTranslator;

	public JPanelCreationStrategyFactory(JPanelLayoutTranslator jPanelLayoutTranslator) {
		this.jPanelLayoutTranslator = jPanelLayoutTranslator;
	}

	public JPanelCreationStrategy forUIProperties(UIProperties uiProperties, WireframeContentType contentType) {
		JPanelCreationStrategy jPanelCreationStrategy = uiProperties.getShape().isRectangular() ?
				new StandardJPanelCreationStrategy(uiProperties, contentType, jPanelLayoutTranslator) :
				new NonRectangularJPanelCreationStrategy(uiProperties, contentType, jPanelLayoutTranslator);

		return uiProperties.getPadding().isEmpty() ?
				jPanelCreationStrategy :
				new DecoratedJPanelWithPadding(jPanelCreationStrategy, uiProperties.getPadding());
	}

}
