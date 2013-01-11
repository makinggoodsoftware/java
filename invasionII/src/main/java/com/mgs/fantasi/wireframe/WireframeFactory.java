package com.mgs.fantasi.wireframe;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static com.mgs.fantasi.properties.measurements.Fractions.all;

public class WireframeFactory<T> {
	private final PlaceholderFactory<T> placeholderFactory;

	public WireframeFactory(PlaceholderFactory<T> placeholderFactory) {
		this.placeholderFactory = placeholderFactory;
	}

	public static <T> Wireframe<T> createGridWireframe(PlaceholderFactory.GridPlaceholderGenerator<T> gridPlaceholderGenerator, Dimension dimension) {
		return new WireframeFactory<T>(new PlaceholderFactory<T>()).grid(gridPlaceholderGenerator, dimension);
	}

	public static <T> Wireframe<T> createLayeredWireframe(java.util.List<T> layers) {
		List<Placeholder<T>> placeholders = new ArrayList<Placeholder<T>>();
		for (int i = layers.size() - 1; i >= 0; i--) {
			T layer = layers.get(i);
			placeholders.add(new Placeholder<T>(layer, i, all(), all(), 0, 0));
		}
		return new Wireframe<T>(placeholders, WireframeType.LAYERS);
	}

	public static <T> Wireframe<T> createRectangleWireframe(T content) {
		if (content == null) return createEmptyWireframe();

		List<Placeholder<T>> placeholders = new ArrayList<Placeholder<T>>();
		placeholders.add(new Placeholder<T>(content, 0, all(), all(), 0, 0));
		return new Wireframe<T>(placeholders, WireframeType.SIMPLE);
	}

	public static <T> Wireframe<T> createEmptyWireframe() {
		return new Wireframe<T>(new ArrayList<Placeholder<T>>(), WireframeType.EMPTY);
	}

	public Wireframe<T> grid(PlaceholderFactory.GridPlaceholderGenerator<T> gridPlaceholderGenerator, Dimension dimension) {
		return new Wireframe<T>(placeholderFactory.gridPlaceholders(gridPlaceholderGenerator, dimension), WireframeType.GRID);
	}
}
