package com.mgs.fantasi.driver.swing.layoutProvider;

import com.mgs.fantasi.views.View;
import com.mgs.fantasi.wireframe.DelegateWireframe;
import com.mgs.fantasi.wireframe.Wireframe;

import javax.swing.*;
import java.awt.*;

public class LayoutProviderFactory {

	public LayoutProvider translateTypeIntoLayoutProvider(Wireframe<View> from) {
		switch (from.getType()) {
			case GRID:
			case SIMPLE:
				return new LayoutProvider() {
					@Override
					public LayoutManager getLayoutManager(JPanel container) {
						return new GridBagLayout();
					}

					@Override
					public boolean isEmpty() {
						return false;
					}
				};
			case LAYERS:
				return new LayoutProvider() {
					@Override
					public LayoutManager getLayoutManager(JPanel container) {
						return new OverlayLayout(container);
					}

					@Override
					public boolean isEmpty() {
						return false;
					}
				};
			case DELEGATE:
				return translateTypeIntoLayoutProvider(((DelegateWireframe<View>) from).getContent().buildContent());
			case EMPTY:
				return new LayoutProvider() {
					@Override
					public LayoutManager getLayoutManager(JPanel container) {
						throw new RuntimeException("This is empty!!");
					}

					@Override
					public boolean isEmpty() {
						return true;
					}
				};
			default:
				throw new RuntimeException("Can't process the structure: " + from);
		}
	}
}
