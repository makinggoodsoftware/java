package com.mgs.fantasi.ui.driver;

import com.mgs.fantasi.ui.driver.swing.SwingUIDisplayManager;
import com.mgs.fantasi.ui.driver.swing.SwingUINativeElementCreatorStrategy;
import com.mgs.fantasi.ui.profile.UIProfile;
import com.mgs.fantasi.ui.profile.UIStyle;
import com.mgs.fantasi.ui.wireframe.CellContent;
import com.mgs.fantasi.ui.wireframe.CellIterator;
import com.mgs.fantasi.ui.wireframe.Grid;
import com.mgs.fantasi.ui.wireframe.Wireframe;

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

	private T buildNativeElement(final Wireframe wireframe) {
		Set<UIStyle> uiStyles = uiProfile.findStylesFor(wireframe);
		final T parent = uiNativeElementCreatorStrategy.createSkeleton(wireframe, uiStyles);
		final Grid<Wireframe> content = wireframe.getContent();
		content.itereateCellsWith(new CellIterator<Wireframe>() {
			@Override
			public void on(int x, int y, CellContent<Wireframe> cell) {
				if (cell == null){
					throw new RuntimeException
							("Error building the UI native element when inspecting the content of the original" +
							" wireframe. This should not happen ever! There must have been an error on the" +
							" build call previous to the transformation into a native UI element" +
							wireframe + " must be badly constructed");
				}
				Wireframe child = cell.getContent();
				T childAsNativeComponent = buildNativeElement(child);
				uiNativeElementCreatorStrategy.compose(parent, childAsNativeComponent, child.getSizeStrategy(), x, y);
			}
		});
		return parent;
	}

}
