package com.mgs.fantasi.driver.swing.jPanelCreation;

import com.mgs.fantasi.driver.swing.SwingUtils;
import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.wireframe.WireframeContentType;

import javax.swing.*;

public class StandardJPanelCreationStrategy implements JPanelCreationStrategy {
	private final UIProperties uiProperties;
	private final WireframeContentType contentType;
	private final JPanelLayoutTranslator jPanelLayoutTranslator;

	public StandardJPanelCreationStrategy(UIProperties uiProperties, WireframeContentType contentType, JPanelLayoutTranslator jPanelLayoutTranslator) {
		this.uiProperties = uiProperties;
		this.contentType = contentType;
		this.jPanelLayoutTranslator = jPanelLayoutTranslator;
	}

	@Override
	public JPanel create() {
		JPanel jPanel = new JPanel();
		jPanel.setOpaque(false);
		SwingUtils.applyUIProperties(jPanel, uiProperties);
		if (getContentType() != WireframeContentType.EMPTY) {
			jPanel.setLayout(jPanelLayoutTranslator.translate(getContentType(), jPanel));
		}
		return jPanel;
	}

	@Override
	public WireframeContentType getContentType() {
		return contentType;
	}

}
