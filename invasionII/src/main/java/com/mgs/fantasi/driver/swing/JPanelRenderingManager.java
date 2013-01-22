package com.mgs.fantasi.driver.swing;

import com.mgs.fantasi.driver.RenderingManager;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelBuilder;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelCreationStrategyFactory;
import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.styles.StyleManager;
import com.mgs.fantasi.styles.UIProfile;
import com.mgs.fantasi.wireframe.Tree;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.WireframeChildElement;

import javax.swing.*;

public class JPanelRenderingManager implements RenderingManager<JPanel> {
	private final JPanelCreationStrategyFactory jPanelBuilderFactory;
	private final StyleManager styleManager;


	public JPanelRenderingManager(StyleManager styleManager, JPanelCreationStrategyFactory jPanelBuilderFactory) {
		this.jPanelBuilderFactory = jPanelBuilderFactory;
		this.styleManager = styleManager;
	}

	@Override
	public JPanel render(Tree<Wireframe> tree, UIProfile uiProfile) {
		UIProperties uiPropertiesWithStylesApplied = styleManager.applyStyles(tree.getContent().getUiProperties(), uiProfile.findStylesFor(tree));
		JPanelBuilder jPanelBuilder = jPanelBuilderFactory.forUIProperties(uiPropertiesWithStylesApplied, tree.getContentType());

		for (WireframeChildElement<Wireframe> wireframeChildPart : tree.getContentElements()) {
			JPanel child = render(wireframeChildPart.getChild(), uiProfile);
			jPanelBuilder.withChild(child, wireframeChildPart.getCollocationInfo());
		}

		return jPanelBuilder.build();
	}


}
