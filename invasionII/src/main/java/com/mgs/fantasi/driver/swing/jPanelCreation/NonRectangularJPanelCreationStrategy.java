package com.mgs.fantasi.driver.swing.jPanelCreation;

import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.wireframe.WireframeContentType;

import javax.swing.*;
import java.awt.*;

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
		return new JPanelWithDifferentShape(uiProperties.getShape(), uiProperties);
	}

	@Override
	public WireframeContentType getContentType() {
		return contentType;
	}

	public LayoutManager translateTypeIntoLayout(JPanel container) {
		return jPanelLayoutTranslator.translate(getContentType(), container);
	}
}
