package com.mgs.fantasi.driver.swing;

import com.mgs.fantasi.driver.RenderingManager;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelBuilder;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelCreationStrategyFactory;
import com.mgs.fantasi.profile.UIProfile;
import com.mgs.fantasi.properties.UIPropertiesBuilder;
import com.mgs.fantasi.wireframe.CollocationInfo;
import com.mgs.fantasi.wireframe.tree.Structure;
import com.mgs.fantasi.wireframe.tree.WireframeNode;

import javax.swing.*;
import java.util.Map;

import static com.mgs.fantasi.properties.UIPropertiesBuilderFactory.from;

public class JPanelRenderingManager implements RenderingManager<JPanel> {
	private final JPanelCreationStrategyFactory jPanelBuilderFactory;


	public JPanelRenderingManager(JPanelCreationStrategyFactory jPanelBuilderFactory) {
		this.jPanelBuilderFactory = jPanelBuilderFactory;
	}

	@Override
	public JPanel render(Structure structure, UIProfile uiProfile) {
		JPanelBuilder jPanelBuilder = renderWireframe(structure.getRoot(), uiProfile);
		JPanelBuilder jPanelBuilderWithChildren = renderChildrenIntoJPanelBuilder(jPanelBuilder, structure.getChildren(), uiProfile);
		return jPanelBuilderWithChildren.build(structure.getType());
	}

	private JPanelBuilder renderChildrenIntoJPanelBuilder(JPanelBuilder jPanelBuilder, Map<CollocationInfo, Structure> children, UIProfile uiProfile) {
		for (Map.Entry<CollocationInfo, Structure> wireframeChildPart : children.entrySet()) {
			JPanel child = render(wireframeChildPart.getValue(), uiProfile);
			jPanelBuilder.withChild(child, wireframeChildPart.getKey());
		}

		return jPanelBuilder;
	}

	private JPanelBuilder renderWireframe(WireframeNode wireframe, UIProfile uiProfile) {
		UIPropertiesBuilder withStylesApplied = from(wireframe.getValue().getUiProperties());
		withStylesApplied.applyStyles(uiProfile.findStylesFor(wireframe));
		return jPanelBuilderFactory.forUIProperties(withStylesApplied.build(), wireframe.getShape());
	}


}
