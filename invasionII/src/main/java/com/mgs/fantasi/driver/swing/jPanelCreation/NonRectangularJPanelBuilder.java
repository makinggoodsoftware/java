package com.mgs.fantasi.driver.swing.jPanelCreation;

import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.wireframe.CollocationInfo;
import com.mgs.fantasi.wireframe.tree.WireframeLayoutType;

import javax.swing.*;

public class NonRectangularJPanelBuilder implements JPanelBuilder {
	private final UIProperties uiProperties;
	private final JPanelLayoutTranslator jPanelLayoutTranslator;

	public NonRectangularJPanelBuilder(UIProperties uiProperties, JPanelLayoutTranslator jPanelLayoutTranslator) {
		this.uiProperties = uiProperties;
		this.jPanelLayoutTranslator = jPanelLayoutTranslator;
	}

	@Override
	public JPanel build(WireframeLayoutType layoutType) {
		JPanelWithDifferentShape container = new JPanelWithDifferentShape(uiProperties.getShape().getValue(), uiProperties);
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
