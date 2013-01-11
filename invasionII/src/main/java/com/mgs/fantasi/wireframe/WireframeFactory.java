package com.mgs.fantasi.wireframe;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static com.mgs.fantasi.properties.measurements.Fractions.all;

public class WireframeFactory<T> {
	public <T> Wireframe<T> createEmptyWireframe() {
		return new Wireframe<T>(new ArrayList<Placeholder<T>>(), WireframeType.EMPTY);
	}

	public <T> Wireframe<T> createRectangleWireframe(T content) {
		if (content == null) return createEmptyWireframe();

		List<Placeholder<T>> placeholders = new ArrayList<Placeholder<T>>();
		placeholders.add(new Placeholder<T>(content, 0, all(), all(), 0, 0));
		return new Wireframe<T>(placeholders, WireframeType.SIMPLE);
	}

	public <T> Wireframe<T> createLayeredWireframe(List<T> layers) {
		List<Placeholder<T>> placeholders = new ArrayList<Placeholder<T>>();
		for (int i = layers.size() - 1; i >= 0; i--) {
			T layer = layers.get(i);
			placeholders.add(new Placeholder<T>(layer, i, all(), all(), 0, 0));
		}
		return new Wireframe<T>(placeholders, WireframeType.LAYERS);
	}

	public <T> Wireframe<T> createGridWireframe(TwoDimensionsIterator<Placeholder<T>> twoDimensionsIterator, Dimension dimension) {
		return new WireframeFactory<T>().grid(twoDimensionsIterator, dimension);
	}

	public Wireframe<T> grid(TwoDimensionsIterator<Placeholder<T>> twoDimensionsIterator, Dimension dimension) {
		List<Placeholder<T>> placeholders = new ArrayList<Placeholder<T>>();
		for (int x = 0; x < dimension.width; x++) {
			for (int y = 0; y < dimension.height; y++) {
				placeholders.add(twoDimensionsIterator.on(x, y));
			}
		}
		return new Wireframe<T>(placeholders, WireframeType.GRID);
	}
}
