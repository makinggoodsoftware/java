package com.mgs.fantasi.driver;

import com.mgs.fantasi.driver.swing.JPanelRenderingManager;
import com.mgs.fantasi.driver.swing.SwingUIDisplayManager;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelCreationStrategyFactory;
import com.mgs.fantasi.styles.StyleManager;
import com.mgs.fantasi.styles.StyleManagerImpl;
import com.mgs.fantasi.styles.UIProfile;
import com.mgs.fantasi.wireframe.Wireframe;

import javax.swing.*;
import java.awt.*;

public class UIDriver<T> {
	private final RenderingManager<T> renderingManager;
	private final UIDisplayManager<T> uiDisplayManager;


	public static UIDriver<JPanel> forSwing() {
		StyleManager styleManager = new StyleManagerImpl();
		JPanelCreationStrategyFactory jPanelCreationStrategyFactory = new JPanelCreationStrategyFactory();

		return new UIDriver<JPanel>
				(
						new SwingUIDisplayManager(),
						new JPanelRenderingManager(
								styleManager,
								jPanelCreationStrategyFactory
						)
				);
	}

	private UIDriver(UIDisplayManager<T> uiDisplayManager, RenderingManager<T> renderingManager) {
		this.uiDisplayManager = uiDisplayManager;
		this.renderingManager = renderingManager;
	}

	public void show(Wireframe wireframe, Dimension dimension, UIProfile uiProfile) {
//		RenderingProcess<T> renderingProcess = renderingManager.newRenderingProcess(wireframe, uiProfile);
//		T uiNativeComponent = renderingProcess.render();
		T uiNativeComponent = renderingManager.render(wireframe, uiProfile);
		uiDisplayManager.showPacked(uiNativeComponent, dimension);
	}
}
