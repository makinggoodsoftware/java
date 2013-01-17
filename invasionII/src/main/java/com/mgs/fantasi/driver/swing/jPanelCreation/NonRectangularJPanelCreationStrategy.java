package com.mgs.fantasi.driver.swing.jPanelCreation;

import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.wireframe.WireframeContentType;

import javax.swing.*;

public class NonRectangularJPanelCreationStrategy implements JPanelCreationStrategy {
	private final UIProperties uiProperties;
	private final WireframeContentType contentType;

	public NonRectangularJPanelCreationStrategy(UIProperties uiProperties, WireframeContentType contentType) {
		this.uiProperties = uiProperties;
		this.contentType = contentType;
	}

	@Override
	public JPanel create() {
		return new JPanelWithDifferentShape(uiProperties.getShape(), uiProperties);
	}

	@Override
	public WireframeContentType getContentType() {
		return contentType;
	}
}
