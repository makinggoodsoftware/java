package com.mgs.fantasi.driver;

import com.mgs.fantasi.driver.swing.SwingUIDisplayManager;
import com.mgs.fantasi.driver.swing.SwingUINativeRenderer;
import com.mgs.fantasi.profile.UIProfileFactory;
import com.mgs.fantasi.views.View;

import javax.swing.*;
import java.awt.*;

public class UIDriver<T> {
    private final UINativeRenderer<T> uiNativeRenderer;
	private final UIDisplayManager<T> uiDisplayManager;


    public static UIDriver<JPanel> forSwing(){
		return new UIDriver<JPanel>(new SwingUINativeRenderer(), new SwingUIDisplayManager());
	}

	private UIDriver(UINativeRenderer<T> uiStrategy, UIDisplayManager<T> uiDisplayManager) {
        this.uiNativeRenderer = uiStrategy;
		this.uiDisplayManager = uiDisplayManager;
    }

	public void show(View view, Dimension dimension, UIProfileFactory uiProfileFactory) {
        T uiNativeComponent = uiNativeRenderer.render(view, uiProfileFactory);
		uiDisplayManager.showPacked(uiNativeComponent, dimension);
	}
}
