package com.mgs.fantasi.driver.swing;

import com.mgs.fantasi.driver.RenderingManager;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelCreationStrategy;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelCreationStrategyFactory;
import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.styles.StyleManager;
import com.mgs.fantasi.styles.UIProfile;
import com.mgs.fantasi.wireframe.CollocationInfo;
import com.mgs.fantasi.wireframe.WireframeChildElement;
import com.mgs.fantasi.wireframe.WireframeTree;

import javax.swing.*;
import java.awt.*;

public class JPanelRenderingManager implements RenderingManager<JPanel> {
	private final JPanelCreationStrategyFactory jPanelCreationStrategyFactory;
	private final StyleManager styleManager;


	public JPanelRenderingManager(StyleManager styleManager, JPanelCreationStrategyFactory jPanelCreationStrategyFactory) {
		this.jPanelCreationStrategyFactory = jPanelCreationStrategyFactory;
		this.styleManager = styleManager;
	}

	@Override
	public JPanel render(WireframeTree wireframeTree, UIProfile uiProfile) {
		UIProperties uiPropertiesWithStylesApplied = styleManager.applyStyles(wireframeTree.getUiProperties(), uiProfile.findStylesFor(wireframeTree));
		JPanelCreationStrategy baseCreationStrategy = jPanelCreationStrategyFactory.forUIProperties(uiPropertiesWithStylesApplied, wireframeTree.getContentType());
		JPanel container = baseCreationStrategy.create();
		if (wireframeTree.isEmpty()) return container;

		container.setLayout(baseCreationStrategy.translateTypeIntoLayout(container));

		for (WireframeChildElement wireframeChildPart : wireframeTree.getContentElements()) {
			WireframeTree childWireframeTree = wireframeChildPart.getChild();
			CollocationInfo specifics = wireframeChildPart.getCollocationInfo();
			JPanel child = render(childWireframeTree, uiProfile);
			container.add(child, translate(specifics, baseCreationStrategy.translateTypeIntoLayout(container)));
		}
		return container;
	}

	private Object translate(CollocationInfo specifics, LayoutManager type) {
		if (type instanceof GridBagLayout) {
			return SwingUtils.coordinates(specifics.getCoordinateX(), specifics.getCoordinateY(), specifics.getProportionOfParentWeight(), specifics.getProportionOfParentHeight());
		} else if (type instanceof OverlayLayout) {
			return specifics.getzIndex();
		}
		throw new RuntimeException("Not expected to hit this code point");
	}


}
