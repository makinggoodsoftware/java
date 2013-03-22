package com.mgs.fantasi.driver.swing.jPanelCreation;

import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.properties.data.polygon.NativeRectanguarShape;
import com.mgs.fantasi.properties.data.polygon.PolygonPointsIterator;

public class JPanelDtoFactory {
	private final JPanelLayoutTranslator jPanelLayoutTranslator;

	public JPanelDtoFactory(JPanelLayoutTranslator jPanelLayoutTranslator) {
		this.jPanelLayoutTranslator = jPanelLayoutTranslator;
	}

	public JPanelDto forUIProperties(UIProperties uiProperties, PolygonPointsIterator shape) {
		JPanelDto baseDto = shape.isRectangular() ?
				new StandardJPanelDto(uiProperties, jPanelLayoutTranslator, new NativeRectanguarShape(), new StandardJPanelFactory()) :
				new StandardJPanelDto(uiProperties, jPanelLayoutTranslator, shape, new NonRectangularJPanelFactory());

		return uiProperties.getPadding().isFullyDefined() ?
				new DecoratedJPanelWithPadding(baseDto, uiProperties.getPadding().getValue()) :
				baseDto;
	}

}
