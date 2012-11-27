package com.mgs.fantasi.ui.wireframe;

import java.util.ArrayList;
import java.util.List;

public class Grid<T> {
	private final int divisionsX;
	private final int divisionsY;
	private final List<List<T>> cells;

	public Grid(int divisionsX, int divisionsY) {
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

	private void setCell(int x, int y, T content) {
		if (x > divisionsX || y > divisionsY) throw new IllegalArgumentException();
		cells.get(x).set(y, content);
	}

	public static <T> Grid<T> withOnlyOneCell(T content) {
		Grid<T> grid = new Grid<T>(0, 0);
		grid.setCell (0, 0, content);
		return grid;				
	}
}
