package com.mgs.fantasi.ui.wireframe;

import com.mgs.fantasi.views.View;

import java.awt.*;

public class GridStructureBuilder  implements StructureBuilder{
	private Dimension dimension;
	private CellContentGenerator<View> cellContentGenerator;

	public GridStructureBuilder withDimension(int x, int y) {
		this.dimension = new Dimension(x, y);
		return this;
	}

	public GridStructureBuilder withContent(CellContentGenerator<View> cellContentGenerator) {
		this.cellContentGenerator = cellContentGenerator;
		return this;
	}

	@Override
	public ReadyForRendering produce() {
		return new ReadyForRendering(this);
	}
}
