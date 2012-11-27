package com.mgs.fantasi.ui.driver;

import com.mgs.fantasi.ui.wireframe.Wireframe;
import com.mgs.fantasi.ui.driver.swing.SwingUIDisplayManager;
import com.mgs.fantasi.ui.driver.swing.SwingUINativeElementCreatorStrategy;
import com.mgs.fantasi.ui.profile.UIProfile;
import com.mgs.fantasi.ui.profile.UIStyle;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class UIDriver<T> {
	private final UIProfile uiProfile;
	private final UINativeElementCreatorStrategy<T> uiNativeElementCreatorStrategy;
	private final UIDIsplayManager<T> uiDIsplayManager;


	public static UIDriver<JPanel> forSwing (UIProfile uiProfile){
		return new UIDriver<JPanel>(uiProfile, new SwingUINativeElementCreatorStrategy(), new SwingUIDisplayManager());
	}

	private UIDriver(UIProfile uiProfile, UINativeElementCreatorStrategy<T> uiStrategy, UIDIsplayManager<T> uiDIsplayManager) {
		this.uiProfile = uiProfile;
		this.uiNativeElementCreatorStrategy = uiStrategy;
		this.uiDIsplayManager = uiDIsplayManager;
	}

	public void show(final Wireframe wireframe, Dimension dimension) {
		T uiNativeComponent = buildNativeElement(wireframe);
		uiDIsplayManager.showPacked(uiNativeComponent, dimension);
	}

	private T buildNativeElement(Wireframe wireframe) {
		Set<UIStyle> uiStyles = uiProfile.findStylesFor(wireframe);
		T parent = uiNativeElementCreatorStrategy.create(wireframe, uiStyles);

		Wireframe child = wireframe.getContent();
		if (child != null) {
			T childAsNativeComponent = buildNativeElement(wireframe.getContent());
			uiNativeElementCreatorStrategy.compose(parent, childAsNativeComponent, child.getSizeStrategy());
		}

		return parent;
	}

}
