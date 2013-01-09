package com.mgs.fantasi.driver.swing.jPanelCreation;

import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.wireframe.WireframeType;

import javax.swing.*;

public class NonRectangularJPanelCreationStrategy implements JPanelCreationStrategy {
	private final UIProperties uiProperties;
	private final WireframeType type;

	public NonRectangularJPanelCreationStrategy(UIProperties uiProperties, WireframeType type) {
		this.uiProperties = uiProperties;
		this.type = type;
	}

	@Override
	public JPanel create() {
		return new JPanelWithDifferentShape(uiProperties.getShape(), uiProperties);
	}

	@Override
	public WireframeType getType() {
		return type;
	}
}
