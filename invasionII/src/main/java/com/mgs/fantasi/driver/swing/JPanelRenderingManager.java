package com.mgs.fantasi.driver.swing;

import com.mgs.fantasi.driver.RenderingManager;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelBuilder;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelCreationStrategyFactory;
import com.mgs.fantasi.profile.UIProfile;
import com.mgs.fantasi.properties.UIPropertiesBuilder;
import com.mgs.fantasi.wireframe.CollocationInfo;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.WireframeCollocationInfoConnectionManager;
import com.mgs.tree.Tree;

import javax.swing.*;
import java.util.Map;

import static com.mgs.fantasi.properties.UIPropertiesBuilderFactory.from;

public class JPanelRenderingManager implements RenderingManager<JPanel> {
	private final JPanelCreationStrategyFactory jPanelBuilderFactory;


	public JPanelRenderingManager(JPanelCreationStrategyFactory jPanelBuilderFactory) {
		this.jPanelBuilderFactory = jPanelBuilderFactory;
	}

	@Override
	public JPanel render(Tree<Wireframe, CollocationInfo> tree, UIProfile uiProfile) {
		UIPropertiesBuilder withStylesApplied = from(tree.getRoot().getUiProperties());
		withStylesApplied.applyStyles(uiProfile.findStylesFor(tree));
		WireframeCollocationInfoConnectionManager connectionManager = (WireframeCollocationInfoConnectionManager) tree.getChildrenBranch().getConnectionManager();
		JPanelBuilder jPanelBuilder = jPanelBuilderFactory.forUIProperties(withStylesApplied.build());

		for (Map.Entry<CollocationInfo, Tree<Wireframe, CollocationInfo>> wireframeChildPart : tree.getChildren()) {
			JPanel child = render(wireframeChildPart.getValue(), uiProfile);
			jPanelBuilder.withChild(child, wireframeChildPart.getKey());
		}

		return jPanelBuilder.build(connectionManager.getType());
	}


}
