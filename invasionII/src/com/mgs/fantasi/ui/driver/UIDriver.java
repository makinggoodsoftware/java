package com.mgs.fantasi.ui.driver;

import com.mgs.fantasi.ui.driver.swing.SwingUIDisplayManager;
import com.mgs.fantasi.ui.profile.UIProfile;
import com.mgs.fantasi.ui.wireframe.MyViewPreprocessor;
import com.mgs.fantasi.ui.wireframe.Renderable;
import com.mgs.fantasi.ui.wireframe.ViewPreprocessor;
import com.mgs.fantasi.views.View;

import javax.swing.*;
import java.awt.*;

public class UIDriver<T> {
	private final UIProfile uiProfile;
	private final UINativeRenderer<T> uiNativeRenderer;
	private final UIDisplayManager<T> uiDisplayManager;
	private final ViewPreprocessor viewPreprocessor;


	public static UIDriver<JPanel> forSwing (UIProfile uiProfile){
		return new UIDriver<JPanel>(uiProfile, new SwingUINativeRenderer(), new SwingUIDisplayManager(), new MyViewPreprocessor());
	}

	private UIDriver(UIProfile uiProfile, UINativeRenderer<T> uiStrategy, UIDisplayManager<T> uiDisplayManager, ViewPreprocessor viewPreprocessor) {
		this.uiProfile = uiProfile;
		this.uiNativeRenderer = uiStrategy;
		this.uiDisplayManager = uiDisplayManager;
		this.viewPreprocessor = viewPreprocessor;
	}

	public void show(View view, Dimension dimension) {
		Renderable renderable = viewPreprocessor.prepareForRendering(view);
		T uiNativeComponent = uiNativeRenderer.render(renderable, uiProfile);
		uiDisplayManager.showPacked(uiNativeComponent, dimension);
	}

}
