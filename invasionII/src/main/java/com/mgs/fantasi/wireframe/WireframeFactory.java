package com.mgs.fantasi.wireframe;

import com.mgs.fantasi.views.View;

import java.awt.*;

public class WireframeFactory<T> {
	private final PlaceholderFactory placeholderFactory;

	public WireframeFactory(PlaceholderFactory placeholderFactory) {
		this.placeholderFactory = placeholderFactory;
	}

	public Wireframe<T> grid(PlaceholderFactory.GridPlaceholderGenerator<View> gridPlaceholderGenerator, Dimension dimension) {
		return new BaseWireframe<T>(placeholderFactory.gridPlaceholders(gridPlaceholderGenerator, dimension));
	}
}
