package com.mgs.fantasi.driver.swing.jPanelCreation;

import com.mgs.fantasi.driver.swing.SwingUtils;
import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.wireframe.WireframeType;

import javax.swing.*;

public class StandardJPanelCreationStrategy implements JPanelCreationStrategy {
	private final UIProperties uiProperties;
	private final WireframeType type;

	public StandardJPanelCreationStrategy(UIProperties uiProperties, WireframeType type) {
		this.uiProperties = uiProperties;
		this.type = type;
	}

	@Override
	public JPanel create() {
		JPanel jPanel = new JPanel();
		jPanel.setOpaque(false);
		SwingUtils.applyUIProperties(jPanel, uiProperties);
		return jPanel;
	}

	@Override
	public WireframeType getType() {
		return type;
	}
}
