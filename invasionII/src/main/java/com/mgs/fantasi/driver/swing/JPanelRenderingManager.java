package com.mgs.fantasi.driver.swing;

import com.mgs.fantasi.driver.RenderingManager;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelBuilder;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelCreationStrategyFactory;
import com.mgs.fantasi.profile.UIProfile;
import com.mgs.fantasi.properties.UIPropertiesBuilder;
import com.mgs.fantasi.wireframe.CollocationInfo;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.WireframeCollocationInfoConnectionManager;
import com.mgs.fantasi.wireframe.WireframeContainer;

import javax.swing.*;
import java.util.Map;

import static com.mgs.fantasi.properties.UIPropertiesBuilderFactory.from;

public class JPanelRenderingManager implements RenderingManager<JPanel> {
	private final JPanelCreationStrategyFactory jPanelBuilderFactory;


	public JPanelRenderingManager(JPanelCreationStrategyFactory jPanelBuilderFactory) {
		this.jPanelBuilderFactory = jPanelBuilderFactory;
	}

	@Override
	public JPanel render(WireframeContainer wireframeContainer, UIProfile uiProfile) {
		JPanelBuilder jPanelBuilder = renderWireframe(wireframeContainer.getRoot(), uiProfile);
		JPanelBuilder jPanelBuilderWithChildren = renderChildrenIntoJPanelBuilder(jPanelBuilder, wireframeContainer.getChildren(), uiProfile);

		WireframeCollocationInfoConnectionManager connectionManager = wireframeContainer.getLayoutManager();
		return jPanelBuilderWithChildren.build(connectionManager.getType());
	}

	private JPanelBuilder renderChildrenIntoJPanelBuilder(JPanelBuilder jPanelBuilder, Map<CollocationInfo, WireframeContainer> children, UIProfile uiProfile) {
		for (Map.Entry<CollocationInfo, WireframeContainer> wireframeChildPart : children.entrySet()) {
			JPanel child = render(wireframeChildPart.getValue(), uiProfile);
			jPanelBuilder.withChild(child, wireframeChildPart.getKey());
		}

		return jPanelBuilder;
	}

	private JPanelBuilder renderWireframe(Wireframe wireframe, UIProfile uiProfile) {
		UIPropertiesBuilder withStylesApplied = from(wireframe.getUiProperties());
		withStylesApplied.applyStyles(uiProfile.findStylesFor(wireframe));
		return jPanelBuilderFactory.forUIProperties(withStylesApplied.build());
	}


}
