package com.mgs.fantasi.wireframe;

import com.mgs.fantasi.wireframe.grid.CellContentGenerator;
import com.mgs.fantasi.wireframe.grid.CellIterator;

import java.awt.*;

public class GridWireframe implements Wireframe {
	private Dimension dimension;
	private CellContentGenerator cellContentGenerator;

	public GridWireframe withDimension(int x, int y) {
		this.dimension = new Dimension(x, y);
		return this;
	}

	public GridWireframe withContent(CellContentGenerator cellContentGenerator) {
		this.cellContentGenerator = cellContentGenerator;
		return this;
	}

	@Override
	public WireframeType getType() {
		return WireframeType.GRID;
	}

	public void itereateCellsWith(CellIterator cellIterator) {
		for (int x = 0; x < dimension.width; x++) {
			for (int y = 0; y < dimension.height; y++) {
				cellIterator.on(x, y, cellContentGenerator.generateContentFor(x, y));
			}
		}
	}
}
