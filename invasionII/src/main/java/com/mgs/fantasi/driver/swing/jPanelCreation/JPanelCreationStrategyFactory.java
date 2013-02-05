package com.mgs.fantasi.driver.swing.jPanelCreation;

import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.properties.UIProperty;
import com.mgs.fantasi.properties.data.polygon.PolygonPointsIterator;
import com.mgs.fantasi.wireframe.WireframeContentType;

public class JPanelCreationStrategyFactory {
	private final JPanelLayoutTranslator jPanelLayoutTranslator;

	public JPanelCreationStrategyFactory(JPanelLayoutTranslator jPanelLayoutTranslator) {
		this.jPanelLayoutTranslator = jPanelLayoutTranslator;
	}

	public JPanelBuilder forUIProperties(UIProperties uiProperties, WireframeContentType contentType) {
		UIProperty<PolygonPointsIterator> shape = uiProperties.getShape();
		PolygonPointsIterator value = shape.getValue();
		boolean rectangular = value.isRectangular();
		JPanelBuilder jPanelBuilder = rectangular ?
				new StandardJPanelBuilder(uiProperties, contentType, jPanelLayoutTranslator) :
				new NonRectangularJPanelBuilder(uiProperties, contentType, jPanelLayoutTranslator);

		return uiProperties.getPadding().isEmpty() ?
				jPanelBuilder :
				new DecoratedJPanelWithPadding(jPanelBuilder, uiProperties.getPadding().getValue());
	}

}
