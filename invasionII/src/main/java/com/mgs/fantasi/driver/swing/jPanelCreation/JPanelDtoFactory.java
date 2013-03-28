package com.mgs.fantasi.driver.swing.jPanelCreation;

import com.mgs.fantasi.properties.JPanelDecorator;
import com.mgs.fantasi.properties.UIPropertiesProvider;
import com.mgs.fantasi.properties.data.Padding;
import com.mgs.fantasi.properties.data.polygon.PolygonPointsIterator;

public class JPanelDtoFactory {
	private final JPanelLayoutTranslator jPanelLayoutTranslator;
	private final JPanelDecorator<Padding> paddingDecorator;

	public JPanelDtoFactory(JPanelLayoutTranslator jPanelLayoutTranslator, JPanelDecorator<Padding> paddingDecorator) {
		this.jPanelLayoutTranslator = jPanelLayoutTranslator;
		this.paddingDecorator = paddingDecorator;
	}

	public JPanelDto forUIProperties(UIPropertiesProvider uiProperties, PolygonPointsIterator shape) {
		return shape.isRectangular() ?
				new JPanelDto(uiProperties, jPanelLayoutTranslator, shape, new StandardJPanelFactory(), paddingDecorator) :
				new JPanelDto(uiProperties, jPanelLayoutTranslator, shape, new NonRectangularJPanelFactory(), paddingDecorator);

	}

}
