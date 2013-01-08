package com.mgs.fantasi.driver;

import com.mgs.fantasi.driver.swing.JPanelRenderingContextFactory;
import com.mgs.fantasi.driver.swing.SwingUIDisplayManager;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelCreationStrategyFactory;
import com.mgs.fantasi.driver.swing.layoutProvider.LayoutProviderFactory;
import com.mgs.fantasi.styles.StyleManager;
import com.mgs.fantasi.styles.StyleManagerImpl;
import com.mgs.fantasi.styles.UIProfileFactory;
import com.mgs.fantasi.views.View;

import javax.swing.*;
import java.awt.*;

public class UIDriver<T> {
	private final UIDisplayManager<T> uiDisplayManager;
	private final RenderingContextFactory<T> renderingContextFactory;


	public static UIDriver<JPanel> forSwing() {
		StyleManager styleManager = new StyleManagerImpl();
		JPanelCreationStrategyFactory jPanelCreationStrategyFactory = new JPanelCreationStrategyFactory();
		LayoutProviderFactory layoutProviderFactory = new LayoutProviderFactory();
		JPanelRenderingContextFactory JPanelRenderingContextFactory = new JPanelRenderingContextFactory(layoutProviderFactory, styleManager, jPanelCreationStrategyFactory);
		return new UIDriver<JPanel>(new SwingUIDisplayManager(), JPanelRenderingContextFactory);
	}

	private UIDriver(UIDisplayManager<T> uiDisplayManager, RenderingContextFactory<T> renderingContextFactory) {
		this.uiDisplayManager = uiDisplayManager;
		this.renderingContextFactory = renderingContextFactory;
	}

	public void show(View view, Dimension dimension, UIProfileFactory uiProfileFactory) {
		RenderingContext<T> renderingContext = renderingContextFactory.getContextFor(uiProfileFactory.getUIProfile());
		T uiNativeComponent = renderingContext.render(view);
		uiDisplayManager.showPacked(uiNativeComponent, dimension);
	}
}
