package com.mgs.fantasi.driver.swing;

import com.mgs.fantasi.driver.RenderingManager;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelBuilder;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelCreationStrategyFactory;
import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.styles.StyleManager;
import com.mgs.fantasi.styles.UIProfile;
import com.mgs.fantasi.wireframe.CollocationInfo;
import com.mgs.fantasi.wireframe.Tree;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.WireframeCollocationInfoConnectionManager;

import javax.swing.*;
import java.util.Map;

public class JPanelRenderingManager implements RenderingManager<JPanel> {
	private final JPanelCreationStrategyFactory jPanelBuilderFactory;
	private final StyleManager styleManager;


	public JPanelRenderingManager(StyleManager styleManager, JPanelCreationStrategyFactory jPanelBuilderFactory) {
		this.jPanelBuilderFactory = jPanelBuilderFactory;
		this.styleManager = styleManager;
	}

	@Override
	public JPanel render(Tree<Wireframe, CollocationInfo> tree, UIProfile uiProfile) {
		UIProperties uiPropertiesWithStylesApplied = styleManager.applyStyles(tree.getContent().getUiProperties(), uiProfile.findStylesFor(tree));
		WireframeCollocationInfoConnectionManager connectionManager = (WireframeCollocationInfoConnectionManager) tree.getBranch().getConnectionManager();
		JPanelBuilder jPanelBuilder = jPanelBuilderFactory.forUIProperties(uiPropertiesWithStylesApplied, connectionManager.getType());

		for (Map.Entry<CollocationInfo, Tree<Wireframe, CollocationInfo>> wireframeChildPart : tree.getContentElements()) {
			JPanel child = render(wireframeChildPart.getValue(), uiProfile);
			jPanelBuilder.withChild(child, wireframeChildPart.getKey());
		}

		return jPanelBuilder.build();
	}


}
