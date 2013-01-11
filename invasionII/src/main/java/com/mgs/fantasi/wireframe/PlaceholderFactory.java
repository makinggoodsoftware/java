package com.mgs.fantasi.wireframe;

import com.mgs.fantasi.properties.measurements.Fractions;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PlaceholderFactory<T> {
	public List<Placeholder<T>> gridPlaceholders(GridPlaceholderGenerator<T> gridPlaceholderGenerator, Dimension dimension) {
		List<Placeholder<T>> placeholders = new ArrayList<Placeholder<T>>();
		for (int x = 0; x < dimension.width; x++) {
			for (int y = 0; y < dimension.height; y++) {
				placeholders.add(gridPlaceholderGenerator.generateContentFor(x, y));
			}
		}
		return placeholders;
	}

	public List<Placeholder<T>> rectanglePlaceholder(T content) {
		List<Placeholder<T>> placeholders = new ArrayList<Placeholder<T>>();
		if (content != null) {
			placeholders.add(singleContent(content, 0));
		}
		return placeholders;
	}

	public List<Placeholder<T>> layerPlaceholders(List<T> layers) {
		List<Placeholder<T>> placeholders = new ArrayList<Placeholder<T>>();
		for (int i = 0; i < layers.size(); i++) {
			placeholders.add(singleContent(layers.get(i), i));
		}
		return placeholders;
	}

	private Placeholder<T> singleContent(T content, int zIndex) {
		return new Placeholder<T>(content, zIndex, Fractions.all(), Fractions.all(), 0, 0);
	}

	public static interface GridPlaceholderGenerator<T> {
		public Placeholder<T> generateContentFor(int x, int y);
	}
}
