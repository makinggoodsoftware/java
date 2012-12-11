package com.mgs.fantasi.ui.driver;

import com.mgs.fantasi.structures.StrictSizeConstraintsResolver;
import com.mgs.fantasi.structures.StrictSizeConstraintsResolverImpl;
import com.mgs.fantasi.ui.driver.swing.SwingUIDisplayManager;
import com.mgs.fantasi.ui.driver.swing.SwingUINativeElementCreatorStrategy;
import com.mgs.fantasi.ui.profile.UIProfile;
import com.mgs.fantasi.ui.wireframe.*;

import javax.swing.*;
import java.awt.*;

public class UIDriver<T> {
	private final UIProfile uiProfile;
	private final UINativeElementCreatorStrategy<T> uiNativeElementCreatorStrategy;
	private final UIDIsplayManager<T> uiDIsplayManager;
	private final StrictSizeConstraintsResolver strictSizeConstraintsResolver;


	public static UIDriver<JPanel> forSwing (UIProfile uiProfile){
		return new UIDriver<JPanel>(uiProfile, new SwingUINativeElementCreatorStrategy(), new SwingUIDisplayManager(), new StrictSizeConstraintsResolverImpl());
	}

	private UIDriver(UIProfile uiProfile, UINativeElementCreatorStrategy<T> uiStrategy, UIDIsplayManager<T> uiDIsplayManager, StrictSizeConstraintsResolver strictSizeConstraintsResolver) {
		this.uiProfile = uiProfile;
		this.uiNativeElementCreatorStrategy = uiStrategy;
		this.uiDIsplayManager = uiDIsplayManager;
		this.strictSizeConstraintsResolver = strictSizeConstraintsResolver;
	}

	public void show(final Wireframe wireframe, Dimension dimension) {
		Wireframe wireframeWithStrictSizeConstraintsResolved = strictSizeConstraintsResolver.resolve(wireframe, dimension);
		T uiNativeComponent = uiNativeElementCreatorStrategy.create(wireframeWithStrictSizeConstraintsResolved, uiProfile);
		uiDIsplayManager.showPacked(uiNativeComponent, dimension);
	}

}
