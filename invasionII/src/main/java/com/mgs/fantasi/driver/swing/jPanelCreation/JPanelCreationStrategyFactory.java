package com.mgs.fantasi.driver.swing.jPanelCreation;

import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.properties.data.polygon.PolygonPointsIterator;

public class JPanelCreationStrategyFactory {
	private final JPanelLayoutTranslator jPanelLayoutTranslator;

	public JPanelCreationStrategyFactory(JPanelLayoutTranslator jPanelLayoutTranslator) {
		this.jPanelLayoutTranslator = jPanelLayoutTranslator;
	}

	public JPanelBuilder forUIProperties(UIProperties uiProperties, PolygonPointsIterator shape) {
		JPanelBuilder baseBuilder = shape.isRectangular() ?
				new StandardJPanelBuilder(uiProperties, jPanelLayoutTranslator) :
				new NonRectangularJPanelBuilder(uiProperties, jPanelLayoutTranslator, shape);

		return uiProperties.getPadding().isFullyDefined() ?
				new DecoratedJPanelWithPadding(baseBuilder, uiProperties.getPadding().getValue()) :
				baseBuilder;
	}

}
