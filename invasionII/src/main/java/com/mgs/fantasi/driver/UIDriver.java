package com.mgs.fantasi.driver;

import com.mgs.fantasi.driver.swing.SwingUIDisplayManager;
import com.mgs.fantasi.driver.swing.SwingUINativeRenderer;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelCreationStrategyFactory;
import com.mgs.fantasi.driver.swing.layoutConstruction.LayoutConstructionManager;
import com.mgs.fantasi.driver.swing.layoutConstruction.LayoutConstructionStrategyFactory;
import com.mgs.fantasi.styles.StyleManager;
import com.mgs.fantasi.styles.StyleManagerImpl;
import com.mgs.fantasi.styles.UIProfileFactory;
import com.mgs.fantasi.views.View;

import javax.swing.*;
import java.awt.*;

import static com.mgs.fantasi.driver.swing.SwingUINativeRenderer.RenderingProcessFactory;

public class UIDriver<T> {
    private final UINativeRenderer<T> uiNativeRenderer;
	private final UIDisplayManager<T> uiDisplayManager;


    public static UIDriver<JPanel> forSwing(){
		StyleManager styleManager = new StyleManagerImpl();
		JPanelCreationStrategyFactory jPanelCreationStrategyFactory = new JPanelCreationStrategyFactory();
		LayoutConstructionManager layoutConstructionManager = new LayoutConstructionManager(new LayoutConstructionStrategyFactory());
		RenderingProcessFactory renderingProcessFactory = new RenderingProcessFactory(layoutConstructionManager, styleManager, jPanelCreationStrategyFactory);
		SwingUINativeRenderer swingUINativeRenderer = new SwingUINativeRenderer(renderingProcessFactory);
		return new UIDriver<JPanel>(swingUINativeRenderer, new SwingUIDisplayManager());
	}

	private UIDriver(UINativeRenderer<T> uiStrategy, UIDisplayManager<T> uiDisplayManager) {
        this.uiNativeRenderer = uiStrategy;
		this.uiDisplayManager = uiDisplayManager;
    }

	public void show(View view, Dimension dimension, UIProfileFactory uiProfileFactory) {
        T uiNativeComponent = uiNativeRenderer.render(view, uiProfileFactory.getUIProfile());
		uiDisplayManager.showPacked(uiNativeComponent, dimension);
	}
}
