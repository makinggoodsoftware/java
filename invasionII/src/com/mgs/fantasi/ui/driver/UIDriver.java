package com.mgs.fantasi.ui.driver;

import com.mgs.fantasi.ui.driver.swing.SwingUIDisplayManager;
import com.mgs.fantasi.ui.driver.swing.SwingUINativeElementCreatorStrategy;
import com.mgs.fantasi.ui.profile.UIProfile;
import com.mgs.fantasi.ui.wireframe.Wireframe;
import com.mgs.fantasi.views.View;
import com.mgs.fantasi.views.ViewRenderer;
import com.mgs.fantasi.views.ViewRendererImpl;

import javax.swing.*;
import java.awt.*;

public class UIDriver<T> {
	private final UIProfile uiProfile;
	private final UINativeElementCreatorStrategy<T> uiNativeElementCreatorStrategy;
	private final UIDIsplayManager<T> uiDIsplayManager;
	private final ViewRenderer viewRenderer;


	public static UIDriver<JPanel> forSwing (UIProfile uiProfile){
		return new UIDriver<JPanel>(uiProfile, new SwingUINativeElementCreatorStrategy(), new SwingUIDisplayManager(), new ViewRendererImpl());
	}

	private UIDriver(UIProfile uiProfile, UINativeElementCreatorStrategy<T> uiStrategy, UIDIsplayManager<T> uiDIsplayManager, ViewRenderer viewRenderer) {
		this.uiProfile = uiProfile;
		this.uiNativeElementCreatorStrategy = uiStrategy;
		this.uiDIsplayManager = uiDIsplayManager;
		this.viewRenderer = viewRenderer;
	}

	public void show(View view, Dimension dimension) {
		Wireframe wireframe = viewRenderer.render(view, uiProfile, dimension);
		T uiNativeComponent = uiNativeElementCreatorStrategy.create(wireframe, uiProfile);
		uiDIsplayManager.showPacked(uiNativeComponent, dimension);
	}

}
