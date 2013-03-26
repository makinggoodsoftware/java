package com.mgs.fantasi.driver;

import com.mgs.fantasi.driver.swing.JPanelRenderingManager;
import com.mgs.fantasi.driver.swing.SwingUIDisplayManager;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelDtoFactory;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelLayoutTranslator;
import com.mgs.fantasi.profile.UIProfile;
import com.mgs.fantasi.properties.UIPropertiesManager;
import com.mgs.fantasi.structure.Structure;

import javax.swing.*;
import java.awt.*;

public class UIDriver<T> {
	private final RenderingManager<T> renderingManager;
	private final UIDisplayManager<T> uiDisplayManager;


	public static UIDriver<JPanel> forSwing() {
		JPanelLayoutTranslator jPanelLayoutTranslator = new JPanelLayoutTranslator();
		JPanelDtoFactory jPanelDtoFactory = new JPanelDtoFactory(jPanelLayoutTranslator);

		return new UIDriver<JPanel>
				(
						new SwingUIDisplayManager(),
						new JPanelRenderingManager(
								jPanelDtoFactory,
								new UIPropertiesManager()
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
