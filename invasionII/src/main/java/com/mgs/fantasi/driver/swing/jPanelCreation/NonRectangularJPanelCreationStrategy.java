package com.mgs.fantasi.driver.swing.jPanelCreation;

import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.wireframe.WireframeContentType;

import javax.swing.*;

public class NonRectangularJPanelCreationStrategy implements JPanelCreationStrategy {
	private final UIProperties uiProperties;
	private final WireframeContentType contentType;
	private final JPanelLayoutTranslator jPanelLayoutTranslator;

	public NonRectangularJPanelCreationStrategy(UIProperties uiProperties, WireframeContentType contentType, JPanelLayoutTranslator jPanelLayoutTranslator) {
		this.uiProperties = uiProperties;
		this.contentType = contentType;
		this.jPanelLayoutTranslator = jPanelLayoutTranslator;
	}

	@Override
	public JPanel create() {
		JPanelWithDifferentShape container = new JPanelWithDifferentShape(uiProperties.getShape(), uiProperties);
		if (getContentType() != WireframeContentType.EMPTY) {
			container.setLayout(jPanelLayoutTranslator.translate(getContentType(), container));
		}
		return container;
	}

	@Override
	public WireframeContentType getContentType() {
		return contentType;
	}

}
