package com.mgs.fantasi.ui.driver;

import com.mgs.fantasi.ui.driver.swing.SwingUIDisplayManager;
import com.mgs.fantasi.ui.driver.swing.SwingUINativeElementCreatorStrategy;
import com.mgs.fantasi.ui.profile.UIProfile;
import com.mgs.fantasi.ui.wireframe.MyRenderer;
import com.mgs.fantasi.ui.wireframe.Renderable;
import com.mgs.fantasi.views.View;

import javax.swing.*;
import java.awt.*;

public class UIDriver<T> {
	private final UIProfile uiProfile;
	private final UINativeElementCreatorStrategy<T> uiNativeElementCreatorStrategy;
	private final UIDisplayManager<T> uiDisplayManager;
	private final MyRenderer myRenderer;


	public static UIDriver<JPanel> forSwing (UIProfile uiProfile){
		return new UIDriver<JPanel>(uiProfile, new SwingUINativeElementCreatorStrategy(), new SwingUIDisplayManager());
	}

	private UIDriver(UIProfile uiProfile, UINativeElementCreatorStrategy<T> uiStrategy, UIDisplayManager<T> uiDisplayManager) {
		this.uiProfile = uiProfile;
		this.uiNativeElementCreatorStrategy = uiStrategy;
		this.uiDisplayManager = uiDisplayManager;
		myRenderer = new MyRenderer();
	}

	public void show(View view, Dimension dimension) {
		Renderable renderable = myRenderer.render(view);
		T uiNativeComponent = uiNativeElementCreatorStrategy.create(renderable, uiProfile);
		uiDisplayManager.showPacked(uiNativeComponent, dimension);
	}

}
