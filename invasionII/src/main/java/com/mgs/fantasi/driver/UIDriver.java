package com.mgs.fantasi.driver;

import com.mgs.fantasi.driver.swing.JPanelRenderingManager;
import com.mgs.fantasi.driver.swing.SwingUIDisplayManager;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelCreationStrategyFactory;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelLayoutTranslator;
import com.mgs.fantasi.profile.UIProfile;
import com.mgs.fantasi.structure.Structure;

import javax.swing.*;
import java.awt.*;

public class UIDriver<T> {
	private final RenderingManager<T> renderingManager;
	private final UIDisplayManager<T> uiDisplayManager;


	public static UIDriver<JPanel> forSwing() {
		JPanelLayoutTranslator jPanelLayoutTranslator = new JPanelLayoutTranslator();
		JPanelCreationStrategyFactory jPanelCreationStrategyFactory = new JPanelCreationStrategyFactory(jPanelLayoutTranslator);

		return new UIDriver<JPanel>
				(
						new SwingUIDisplayManager(),
						new JPanelRenderingManager(
								jPanelCreationStrategyFactory
						)
				);
	}

	private UIDriver(UIDisplayManager<T> uiDisplayManager, RenderingManager<T> renderingManager) {
		this.uiDisplayManager = uiDisplayManager;
		this.renderingManager = renderingManager;
	}

	public void show(Structure tree, Dimension dimension, UIProfile uiProfile) {
		T uiNativeComponent = renderingManager.render(tree, uiProfile);
		uiDisplayManager.showPacked(uiNativeComponent, dimension);
	}
}
