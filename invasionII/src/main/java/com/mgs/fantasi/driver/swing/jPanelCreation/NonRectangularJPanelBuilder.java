package com.mgs.fantasi.driver.swing.jPanelCreation;

import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.properties.data.polygon.PolygonPointsIterator;
import com.mgs.fantasi.structure.CollocationInfo;
import com.mgs.fantasi.structure.treeAux.WireframeLayoutType;

import javax.swing.*;

public class NonRectangularJPanelBuilder implements JPanelBuilder {
	private final PolygonPointsIterator shape;
	private final UIProperties uiProperties;
	private final JPanelLayoutTranslator jPanelLayoutTranslator;

	public NonRectangularJPanelBuilder(UIProperties uiProperties, JPanelLayoutTranslator jPanelLayoutTranslator, PolygonPointsIterator shape) {
		this.uiProperties = uiProperties;
		this.jPanelLayoutTranslator = jPanelLayoutTranslator;
		this.shape = shape;
	}

	@Override
	public JPanel build(WireframeLayoutType layoutType) {
		JPanelWithDifferentShape container = new JPanelWithDifferentShape(shape, uiProperties);
		if (layoutType != WireframeLayoutType.EMPTY) {
			container.setLayout(jPanelLayoutTranslator.translate(layoutType, container));
		}
		return container;
	}

	@Override
	public JPanelBuilder withChild(JPanel child, CollocationInfo collocationInfo) {
		throw new RuntimeException("Can't add content into a non rectangular panel");
	}

}
