package com.mgs.fantasi.driver.swing;

import com.mgs.fantasi.driver.RenderingManager;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelCreationStrategy;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelCreationStrategyFactory;
import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.styles.StyleManager;
import com.mgs.fantasi.styles.UIProfile;
import com.mgs.fantasi.wireframe.CollocationInfo;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.WireframeChildElement;
import com.mgs.fantasi.wireframe.WireframeContentType;

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
	public JPanel render(Wireframe wireframe, UIProfile uiProfile) {
		UIProperties uiPropertiesWithStylesApplied = styleManager.applyStyles(wireframe.getUiProperties(), uiProfile.findStylesFor(wireframe));
		JPanelCreationStrategy baseCreationStrategy = jPanelCreationStrategyFactory.forUIProperties(uiPropertiesWithStylesApplied, wireframe.getContentType());
		JPanel container = baseCreationStrategy.create();
		if (wireframe.isEmpty()) return container;

		LayoutManager layoutManager = translateTypeIntoLayout(container, baseCreationStrategy.getContentType());
		container.setLayout(layoutManager);

		for (WireframeChildElement wireframeChildPart : wireframe.getContentElements()) {
			Wireframe childWireframe = wireframeChildPart.getChild();
			CollocationInfo specifics = wireframeChildPart.getCollocationInfo();
			JPanel child = render(childWireframe, uiProfile);
			container.add(child, translate(specifics, layoutManager));
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


	private LayoutManager translateTypeIntoLayout(JPanel container, WireframeContentType contentType) {
		switch (contentType) {
			case GRID:
			case RECTANGLE:
				return new GridBagLayout();
			case LAYERS:
				return new OverlayLayout(container);
			case EMPTY:
				throw new RuntimeException("There is no layout for empty wireframes!!!");
			default:
				throw new RuntimeException("Shouldn't have reached this point in the code!!!");
		}
	}


}
