package com.mgs.fantasi.driver.swing.jPanelCreation;

import com.mgs.fantasi.properties.UIProperties;

public class JPanelCreationStrategyFactory {
	private final JPanelLayoutTranslator jPanelLayoutTranslator;

	public JPanelCreationStrategyFactory(JPanelLayoutTranslator jPanelLayoutTranslator) {
		this.jPanelLayoutTranslator = jPanelLayoutTranslator;
	}

	public JPanelBuilder forUIProperties(UIProperties uiProperties) {
		JPanelBuilder baseBuilder = uiProperties.getShape().getValue().isRectangular() ?
				new StandardJPanelBuilder(uiProperties, jPanelLayoutTranslator) :
				new NonRectangularJPanelBuilder(uiProperties, jPanelLayoutTranslator);

		return uiProperties.getPadding().isFullyDefined() ?
				new DecoratedJPanelWithPadding(baseBuilder, uiProperties.getPadding().getValue()) :
				baseBuilder;
	}

}
