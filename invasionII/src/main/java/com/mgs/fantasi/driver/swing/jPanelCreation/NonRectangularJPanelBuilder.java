package com.mgs.fantasi.driver.swing.jPanelCreation;

import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.wireframe.CollocationInfo;
import com.mgs.fantasi.wireframe.WireframeContentType;

import javax.swing.*;

public class NonRectangularJPanelBuilder implements JPanelBuilder {
	private final UIProperties uiProperties;
	private final WireframeContentType contentType;
	private final JPanelLayoutTranslator jPanelLayoutTranslator;

	public NonRectangularJPanelBuilder(UIProperties uiProperties, WireframeContentType contentType, JPanelLayoutTranslator jPanelLayoutTranslator) {
		this.uiProperties = uiProperties;
		this.contentType = contentType;
		this.jPanelLayoutTranslator = jPanelLayoutTranslator;
	}

	@Override
	public JPanel build() {
		JPanelWithDifferentShape container = new JPanelWithDifferentShape(uiProperties.getShape().getValue(), uiProperties);
		if (getContentType() != WireframeContentType.EMPTY) {
			container.setLayout(jPanelLayoutTranslator.translate(getContentType(), container));
		}
		return container;
	}

	@Override
	public WireframeContentType getContentType() {
		return contentType;
	}

	@Override
	public JPanelBuilder withChild(JPanel child, CollocationInfo collocationInfo) {
		throw new RuntimeException("Can't add content into a non rectangular panel");
	}

}
