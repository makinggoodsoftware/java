package com.mgs.fantasi.ui.driver;

import com.mgs.fantasi.ui.driver.swing.SwingUIDisplayManager;
import com.mgs.fantasi.ui.driver.swing.SwingUINativeElementCreatorStrategy;
import com.mgs.fantasi.ui.profile.UIProfile;
import com.mgs.fantasi.ui.wireframe.Renderable;
import com.mgs.fantasi.views.View;
import com.mgs.fantasi.views.ViewRenderer;
import com.mgs.fantasi.views.ViewRendererImpl;

import javax.swing.*;
import java.awt.*;

public class UIDriver<T> {
	private final UIProfile uiProfile;
	private final UINativeElementCreatorStrategy<T> uiNativeElementCreatorStrategy;
	private final UIDisplayManager<T> uiDisplayManager;
	private final ViewRenderer viewRenderer;


	public static UIDriver<JPanel> forSwing (UIProfile uiProfile){
		return new UIDriver<JPanel>(uiProfile, new SwingUINativeElementCreatorStrategy(), new SwingUIDisplayManager(), new ViewRendererImpl());
	}

	private UIDriver(UIProfile uiProfile, UINativeElementCreatorStrategy<T> uiStrategy, UIDisplayManager<T> uiDisplayManager, ViewRenderer viewRenderer) {
		this.uiProfile = uiProfile;
		this.uiNativeElementCreatorStrategy = uiStrategy;
		this.uiDisplayManager = uiDisplayManager;
		this.viewRenderer = viewRenderer;
	}

	public void show(View view, Dimension dimension) {
		Renderable renderable = viewRenderer.createRenderable(view, uiProfile, dimension);
		T uiNativeComponent = uiNativeElementCreatorStrategy.create(renderable, uiProfile);
		uiDisplayManager.showPacked(uiNativeComponent, dimension);
	}

}
