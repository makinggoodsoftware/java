package com.mgs.fantasi.driver;

import com.mgs.fantasi.driver.swing.JPanelRenderingProcessFactory;
import com.mgs.fantasi.driver.swing.SwingUIDisplayManager;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelCreationStrategyFactory;
import com.mgs.fantasi.styles.StyleManager;
import com.mgs.fantasi.styles.StyleManagerImpl;
import com.mgs.fantasi.styles.UIProfile;
import com.mgs.fantasi.wireframe.Wireframe;

import javax.swing.*;
import java.awt.*;

public class UIDriver<T> {
	private final RenderingProcessFactory<T> renderingProcessFactory;
	private final UIDisplayManager<T> uiDisplayManager;


	public static UIDriver<JPanel> forSwing() {
		StyleManager styleManager = new StyleManagerImpl();
		JPanelCreationStrategyFactory jPanelCreationStrategyFactory = new JPanelCreationStrategyFactory();

		return new UIDriver<JPanel>
				(
						new SwingUIDisplayManager(),
						new JPanelRenderingProcessFactory(
								styleManager,
								jPanelCreationStrategyFactory
						)
				);
	}

	private UIDriver(UIDisplayManager<T> uiDisplayManager, RenderingProcessFactory<T> renderingProcessFactory) {
		this.uiDisplayManager = uiDisplayManager;
		this.renderingProcessFactory = renderingProcessFactory;
	}

	public void show(Wireframe wireframe, Dimension dimension, UIProfile uiProfile) {
		RenderingProcess<T> renderingProcess = renderingProcessFactory.newRenderingProcess(wireframe, uiProfile);
		T uiNativeComponent = renderingProcess.render();
		uiDisplayManager.showPacked(uiNativeComponent, dimension);
	}
}
