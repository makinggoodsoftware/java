package com.mgs.fantasi.ui.wireframe;

import java.util.ArrayList;
import java.util.List;

public class ArrayListGrid<T> implements Grid<T> {
	private final int divisionsX;
	private final int divisionsY;
	private final List<List<T>> cells;

	public ArrayListGrid(int divisionsX, int divisionsY) {
		this.divisionsX = divisionsX;
		this.divisionsY = divisionsY;
		cells = new ArrayList<List<T>>();
		for (int i=1; i<=divisionsX; i++){
			List<T> columns = new ArrayList<T>();
			for (int j=1; j<=divisionsY; j++){
				columns.add(null);
			}
			cells.add(columns);
		}
	}

	@Override
	public void setCell(int x, int y, T content) {
		if (x > divisionsX || y > divisionsY) throw new IllegalArgumentException();
		cells.get(x).set(y, content);
	}

	@Override
	public T getCell(int x, int y) {
		if (x > divisionsX || y > divisionsY) throw new IllegalArgumentException();
		return cells.get(x).get(y);
	}

	@Override
	public void fillCells(CellContentGenerator<T> cellContentGenerator) {
		for (int x=0; x < divisionsX; x++){
			for (int y=0; y < divisionsY; y++){
				T content = cellContentGenerator.generateContentFor(x, y);
				setCell(x, y, content);
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

}
