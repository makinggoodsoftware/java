package com.mgs.fantasi.ui.wireframe;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ArrayListGrid<T extends Structurable> extends BaseStructure<T> implements Grid<T> {
	private final int divisionsX;
	private final int divisionsY;
	private final List<List<CellContent<T>>> cells;

	public ArrayListGrid(int divisionsX, int divisionsY) {
		this.divisionsX = divisionsX;
		this.divisionsY = divisionsY;
		cells = new ArrayList<List<CellContent<T>>>();
		for (int x=1; x<=divisionsX; x++){
			List<CellContent<T>> columns = new ArrayList<CellContent<T>>();
			for (int y=1; y<=divisionsY; y++){
				columns.add(null);
			}
			cells.add(columns);
		}
	}

	public ArrayListGrid(Dimension dimension) {
		this (dimension.width, dimension.height);
	}

	@Override
	public void setCell(int x, int y, CellContent<T> content) {
		if (x > divisionsX || y > divisionsY) throw new IllegalArgumentException();
		cells.get(x).set(y, content);
	}

	@Override
	public CellContent<T> getCell(int x, int y) {
		if (x > divisionsX || y > divisionsY) throw new IllegalArgumentException();
		return cells.get(x).get(y);
	}

	@Override
	public void fillCells(CellContentGenerator<T> cellContentGenerator) {
		for (int x=0; x < divisionsX; x++){
			for (int y=0; y < divisionsY; y++){
				CellContent<T> cellContent = cellContentGenerator.generateContentFor(x, y);
				setCell(x, y, cellContent);
			}
		}
	}

	@Override
	public void itereateCellsWith(CellIterator<T> cellIterator) {
		for (int x=0; x < divisionsX; x++){
			for (int y=0; y < divisionsY; y++){
				cellIterator.on(x, y, getCell(x, y));
			}
		}
	}

	@Override
	public String toString() {
		return "ArrayListGrid{" +
				"cells=" + cells +
				", divisionsX=" + divisionsX +
				", divisionsY=" + divisionsY +
				'}';
	}

	public Dimension getDimension() {
		return new Dimension(divisionsX, divisionsY);
	}
}
