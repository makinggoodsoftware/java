package com.mgs.fantasi.driver.swing;

import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelCreationStrategyFactory;
import com.mgs.fantasi.driver.swing.layoutConstruction.LayoutConstructionManager;
import com.mgs.fantasi.styles.StyleManager;
import com.mgs.fantasi.styles.UIProfile;

import javax.swing.*;

public class JPanelRenderingContextFactory implements RenderingContextFactory<JPanel> {
	private final LayoutConstructionManager layoutConstructionManager;
	private final StyleManager styleManager;
	private final JPanelCreationStrategyFactory jPanelCreationStrategyFactory;

	public JPanelRenderingContextFactory(LayoutConstructionManager layoutConstructionManager, StyleManager styleManager, JPanelCreationStrategyFactory jPanelCreationStrategyFactory) {
		this.layoutConstructionManager = layoutConstructionManager;
		this.styleManager = styleManager;
		this.jPanelCreationStrategyFactory = jPanelCreationStrategyFactory;
	}

	@Override
	public RenderingContext<JPanel> getContextFor(UIProfile uiProfile) {
		return new JPanelRenderingContext(uiProfile, layoutConstructionManager, styleManager, jPanelCreationStrategyFactory);
	}
}
