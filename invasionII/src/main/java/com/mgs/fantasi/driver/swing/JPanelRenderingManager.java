package com.mgs.fantasi.driver.swing;

import com.mgs.fantasi.driver.RenderingManager;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelBuilder;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelCreationStrategyFactory;
import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.styles.StyleManager;
import com.mgs.fantasi.styles.UIProfile;
import com.mgs.fantasi.wireframe.WireframeChildElement;
import com.mgs.fantasi.wireframe.WireframeTree;

import javax.swing.*;

public class JPanelRenderingManager implements RenderingManager<JPanel> {
	private final JPanelCreationStrategyFactory jPanelBuilderFactory;
	private final StyleManager styleManager;


	public JPanelRenderingManager(StyleManager styleManager, JPanelCreationStrategyFactory jPanelBuilderFactory) {
		this.jPanelBuilderFactory = jPanelBuilderFactory;
		this.styleManager = styleManager;
	}

	@Override
	public JPanel render(WireframeTree wireframeTree, UIProfile uiProfile) {
		UIProperties uiPropertiesWithStylesApplied = styleManager.applyStyles(wireframeTree.getUiProperties(), uiProfile.findStylesFor(wireframeTree));
		JPanelBuilder jPanelBuilder = jPanelBuilderFactory.forUIProperties(uiPropertiesWithStylesApplied, wireframeTree.getContentType());

		for (WireframeChildElement wireframeChildPart : wireframeTree.getContentElements()) {
			JPanel child = render(wireframeChildPart.getChild(), uiProfile);
			jPanelBuilder.withChild(child, wireframeChildPart.getCollocationInfo());
		}

		return jPanelBuilder.build();
	}


}
