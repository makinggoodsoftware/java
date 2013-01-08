package com.mgs.fantasi.driver.swing;

import com.mgs.fantasi.driver.RenderingContext;
import com.mgs.fantasi.driver.RenderingContextFactory;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelCreationStrategyFactory;
import com.mgs.fantasi.driver.swing.layoutProvider.LayoutProviderFactory;
import com.mgs.fantasi.styles.StyleManager;
import com.mgs.fantasi.styles.UIProfile;

import javax.swing.*;

public class JPanelRenderingContextFactory implements RenderingContextFactory<JPanel> {
	private final LayoutProviderFactory layoutProviderFactory;
	private final StyleManager styleManager;
	private final JPanelCreationStrategyFactory jPanelCreationStrategyFactory;

	public JPanelRenderingContextFactory(LayoutProviderFactory layoutProviderFactory, StyleManager styleManager, JPanelCreationStrategyFactory jPanelCreationStrategyFactory) {
		this.layoutProviderFactory = layoutProviderFactory;
		this.styleManager = styleManager;
		this.jPanelCreationStrategyFactory = jPanelCreationStrategyFactory;
	}

	@Override
	public RenderingContext<JPanel> getContextFor(UIProfile uiProfile) {
		return new JPanelRenderingContext(uiProfile, layoutProviderFactory, styleManager, jPanelCreationStrategyFactory);
	}
}
