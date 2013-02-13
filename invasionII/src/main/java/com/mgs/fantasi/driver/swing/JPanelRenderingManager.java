package com.mgs.fantasi.driver.swing;

import com.mgs.fantasi.driver.RenderingManager;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelBuilder;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelCreationStrategyFactory;
import com.mgs.fantasi.profile.UIProfile;
import com.mgs.fantasi.properties.UIPropertiesBuilder;
import com.mgs.fantasi.wireframe.CollocationInfo;
import com.mgs.fantasi.wireframe.tree.WireframeNode;
import com.mgs.fantasi.wireframe.tree.WireframeTree;

import javax.swing.*;
import java.util.Map;

import static com.mgs.fantasi.properties.UIPropertiesBuilderFactory.from;

public class JPanelRenderingManager implements RenderingManager<JPanel> {
	private final JPanelCreationStrategyFactory jPanelBuilderFactory;


	public JPanelRenderingManager(JPanelCreationStrategyFactory jPanelBuilderFactory) {
		this.jPanelBuilderFactory = jPanelBuilderFactory;
	}

	@Override
	public JPanel render(WireframeTree wireframeTree, UIProfile uiProfile) {
		JPanelBuilder jPanelBuilder = renderWireframe(wireframeTree.getRoot(), uiProfile);
		JPanelBuilder jPanelBuilderWithChildren = renderChildrenIntoJPanelBuilder(jPanelBuilder, wireframeTree.getChildren(), uiProfile);
		return jPanelBuilderWithChildren.build(wireframeTree.getType());
	}

	private JPanelBuilder renderChildrenIntoJPanelBuilder(JPanelBuilder jPanelBuilder, Map<CollocationInfo, WireframeTree> children, UIProfile uiProfile) {
		for (Map.Entry<CollocationInfo, WireframeTree> wireframeChildPart : children.entrySet()) {
			JPanel child = render(wireframeChildPart.getValue(), uiProfile);
			jPanelBuilder.withChild(child, wireframeChildPart.getKey());
		}

		return jPanelBuilder;
	}

	private JPanelBuilder renderWireframe(WireframeNode wireframe, UIProfile uiProfile) {
		UIPropertiesBuilder withStylesApplied = from(wireframe.getValue().getUiProperties());
		withStylesApplied.applyStyles(uiProfile.findStylesFor(wireframe));
		return jPanelBuilderFactory.forUIProperties(withStylesApplied.build());
	}


}
