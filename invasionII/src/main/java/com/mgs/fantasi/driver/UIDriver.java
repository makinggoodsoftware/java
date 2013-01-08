package com.mgs.fantasi.driver;

import com.mgs.fantasi.driver.swing.JPanelRenderingProcessFactory;
import com.mgs.fantasi.driver.swing.RenderingProcessFactory;
import com.mgs.fantasi.driver.swing.SwingUIDisplayManager;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelCreationStrategyFactory;
import com.mgs.fantasi.driver.swing.layoutProvider.LayoutProviderFactory;
import com.mgs.fantasi.styles.StyleManager;
import com.mgs.fantasi.styles.StyleManagerImpl;
import com.mgs.fantasi.styles.UIProfile;
import com.mgs.fantasi.styles.UIProfileFactory;
import com.mgs.fantasi.views.View;

import javax.swing.*;
import java.awt.*;

public class UIDriver<T> {
	private final RenderingProcessFactory<T> renderingProcessFactory;
	private final UIDisplayManager<T> uiDisplayManager;


	public static UIDriver<JPanel> forSwing() {
		StyleManager styleManager = new StyleManagerImpl();
		JPanelCreationStrategyFactory jPanelCreationStrategyFactory = new JPanelCreationStrategyFactory();
		LayoutProviderFactory layoutProviderFactory = new LayoutProviderFactory();

		return new UIDriver<JPanel>(new SwingUIDisplayManager(), new JPanelRenderingProcessFactory(layoutProviderFactory, styleManager, jPanelCreationStrategyFactory));
	}

	private UIDriver(UIDisplayManager<T> uiDisplayManager, RenderingProcessFactory<T> renderingProcessFactory) {
		this.uiDisplayManager = uiDisplayManager;
		this.renderingProcessFactory = renderingProcessFactory;
	}

	public void show(View view, Dimension dimension, UIProfileFactory uiProfileFactory) {
		UIProfile uiProfile = uiProfileFactory.getUIProfile();
		T uiNativeComponent = renderingProcessFactory.newRenderingProcess(view, uiProfile).render();
		uiDisplayManager.showPacked(uiNativeComponent, dimension);
	}
}
