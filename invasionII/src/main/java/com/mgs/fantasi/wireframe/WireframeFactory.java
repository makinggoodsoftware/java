package com.mgs.fantasi.wireframe;

import java.awt.*;

public class WireframeFactory<T> {
	private final PlaceholderFactory<T> placeholderFactory;

	public WireframeFactory(PlaceholderFactory<T> placeholderFactory) {
		this.placeholderFactory = placeholderFactory;
	}

	public static <T> Wireframe<T> createGridWireframe(PlaceholderFactory.GridPlaceholderGenerator<T> gridPlaceholderGenerator, Dimension dimension) {
		return new WireframeFactory<T>(new PlaceholderFactory<T>()).grid(gridPlaceholderGenerator, dimension);
	}

	public Wireframe<T> grid(PlaceholderFactory.GridPlaceholderGenerator<T> gridPlaceholderGenerator, Dimension dimension) {
		return new BaseWireframe<T>(placeholderFactory.gridPlaceholders(gridPlaceholderGenerator, dimension));
	}
}
