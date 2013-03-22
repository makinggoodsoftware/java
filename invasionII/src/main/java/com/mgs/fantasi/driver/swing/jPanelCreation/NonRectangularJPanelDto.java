package com.mgs.fantasi.driver.swing.jPanelCreation;

import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.properties.data.polygon.PolygonPointsIterator;
import com.mgs.fantasi.structure.CollocationInfo;
import com.mgs.fantasi.structure.treeAux.WireframeLayoutType;

import javax.swing.*;

public class NonRectangularJPanelDto implements JPanelDto {
	private final PolygonPointsIterator shape;
	private final UIProperties uiProperties;
	private final JPanelLayoutTranslator jPanelLayoutTranslator;

	public NonRectangularJPanelDto(UIProperties uiProperties, JPanelLayoutTranslator jPanelLayoutTranslator, PolygonPointsIterator shape) {
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
	public JPanelDto addChild(JPanelDto child, CollocationInfo collocationInfo, WireframeLayoutType wireframeLayoutType) {
		throw new RuntimeException("Can't add content into a non rectangular panel");
	}

}
