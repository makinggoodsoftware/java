package com.mgs.fantasi.driver.swing.jPanelCreation;

import com.mgs.fantasi.properties.UIPropertiesProvider;
import com.mgs.fantasi.properties.data.polygon.PolygonPointsIterator;
import com.mgs.fantasi.structure.treeAux.WireframeLayoutType;

import javax.swing.*;
import java.util.List;

public class NonRectangularJPanelFactory implements JPanelFactory {
	@Override
	public JPanel create(WireframeLayoutType layoutType, UIPropertiesProvider uiProperties, JPanelLayoutTranslator jPanelLayoutTranslator, List<JPanelDto.JPanelChild> children, PolygonPointsIterator shape) {
		JPanelWithDifferentShape container = new JPanelWithDifferentShape(shape, uiProperties);
		if (layoutType != WireframeLayoutType.EMPTY) {
			container.setLayout(jPanelLayoutTranslator.translate(layoutType, container));
		}
		return container;
	}
}
