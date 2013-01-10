package com.mgs.fantasi.wireframe;

import com.mgs.fantasi.views.View;
import com.mgs.fantasi.wireframe.grid.CellContentGenerator;

import java.util.List;

public class PlaceholderFactory {
	public List<Placeholder<View>> gridPlaceholders(CellContentGenerator<View> cellContentGenerator) {
		return null;
	}

	public Wireframe<View> empty() {
		return null;
	}

	public List<Placeholder<View>> rectanglePlaceholder(View content) {
		return null;
	}

	public List<Placeholder<View>> layerPlaceholders(List<View> layers) {
		return null;
	}
}
