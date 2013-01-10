package com.mgs.fantasi.wireframe;

import com.mgs.fantasi.properties.measurements.Fractions;
import com.mgs.fantasi.views.View;
import com.mgs.fantasi.wireframe.grid.CellContent;
import com.mgs.fantasi.wireframe.grid.CellContentGenerator;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PlaceholderFactory {
	public List<Placeholder<View>> gridPlaceholders(CellContentGenerator<View> cellContentGenerator, Dimension dimension) {
		List<Placeholder<View>> placeholders = new ArrayList<Placeholder<View>>();
		for (int x = 0; x <= dimension.width; x++) {
			for (int y = 0; y <= dimension.height; y++) {
				CellContent<View> viewCellContent = cellContentGenerator.generateContentFor(x, y);
				placeholders.add(fromCellContent(viewCellContent));
			}
		}
		return placeholders;
	}

	public List<Placeholder<View>> rectanglePlaceholder(View content) {
		List<Placeholder<View>> placehoders = new ArrayList<Placeholder<View>>();
		if (content != null) {
			placehoders.add(singleContent(content, 0));
		}
		return placehoders;
	}

	public List<Placeholder<View>> layerPlaceholders(List<View> layers) {
		List<Placeholder<View>> placehoders = new ArrayList<Placeholder<View>>();
		for (int i = 0; i < layers.size(); i++) {
			placehoders.add(singleContent(layers.get(i), i));
		}
		return placehoders;
	}

	private Placeholder<View> fromCellContent(CellContent<View> viewCellContent) {
		return new Placeholder<View>(viewCellContent.getContent(), 0, viewCellContent.getWidthSizeRatio(), viewCellContent.getHeightSizeRatio());
	}

	private Placeholder<View> singleContent(View content, int zIndex) {
		return new Placeholder<View>(content, zIndex, Fractions.all(), Fractions.all());
	}
}
