package com.mgs.fantasi.wireframe;

import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.views.WireframeBuilder;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static com.mgs.fantasi.properties.measurements.Fractions.all;

public class WireframeFactory<T> {
	public Wireframe<T> createEmptyWireframe(UIProperties uiProperties, String name, Class<? extends WireframeBuilder> builderClass) {
		return new Wireframe<T>(new ArrayList<Placeholder<T>>(), WireframeType.EMPTY, uiProperties, name, builderClass);
	}

	public Wireframe<T> createRectangleWireframe(T content, UIProperties uiProperties, String name, Class<? extends WireframeBuilder> builderClass) {
		if (content == null) return createEmptyWireframe(uiProperties, name, builderClass);

		List<Placeholder<T>> placeholders = new ArrayList<Placeholder<T>>();
		placeholders.add(new Placeholder<T>(content, 0, all(), all(), 0, 0));
		return new Wireframe<T>(placeholders, WireframeType.SIMPLE, uiProperties, name, builderClass);
	}

	public Wireframe<T> createLayeredWireframe(List<T> layers, UIProperties uiProperties, String name, Class<? extends WireframeBuilder> builderClass) {
		List<Placeholder<T>> placeholders = new ArrayList<Placeholder<T>>();
		for (int i = layers.size() - 1; i >= 0; i--) {
			T layer = layers.get(i);
			placeholders.add(new Placeholder<T>(layer, i, all(), all(), 0, 0));
		}
		return new Wireframe<T>(placeholders, WireframeType.LAYERS, uiProperties, name, builderClass);
	}

	public Wireframe<T> createGridWireframe(TwoDimensionsIterator<Placeholder<T>> twoDimensionsIterator, Dimension dimension, UIProperties uiProperties, String name, Class<? extends WireframeBuilder> builderClass) {
		return new WireframeFactory<T>().grid(twoDimensionsIterator, dimension, uiProperties, name, builderClass);
	}

	public Wireframe<T> grid(TwoDimensionsIterator<Placeholder<T>> twoDimensionsIterator, Dimension dimension, UIProperties uiProperties, String name, Class<? extends WireframeBuilder> builderClass) {
		List<Placeholder<T>> placeholders = new ArrayList<Placeholder<T>>();
		for (int x = 0; x < dimension.width; x++) {
			for (int y = 0; y < dimension.height; y++) {
				placeholders.add(twoDimensionsIterator.on(x, y));
			}
		}
		return new Wireframe<T>(placeholders, WireframeType.GRID, uiProperties, name, builderClass);
	}
}
