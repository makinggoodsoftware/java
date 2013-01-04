package com.mgs.fantasi.wireframe;

import com.mgs.fantasi.wireframe.grid.CellContentGenerator;
import com.mgs.fantasi.wireframe.grid.CellIterator;

import java.awt.*;

public class GridWireframe<T> implements Wireframe<T> {
	private Dimension dimension;
	private CellContentGenerator<T> cellContentGenerator;

	public GridWireframe<T> withDimension(int x, int y) {
		this.dimension = new Dimension(x, y);
		return this;
	}

	public GridWireframe<T> withContent(CellContentGenerator<T> cellContentGenerator) {
		this.cellContentGenerator = cellContentGenerator;
		return this;
	}

	@Override
	public WireframeType getType() {
		return WireframeType.GRID;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	public void itereateCellsWith(CellIterator<T> cellIterator) {
		for (int x = 0; x < dimension.width; x++) {
			for (int y = 0; y < dimension.height; y++) {
				cellIterator.on(x, y, cellContentGenerator.generateContentFor(x, y));
			}
		}
	}
}
