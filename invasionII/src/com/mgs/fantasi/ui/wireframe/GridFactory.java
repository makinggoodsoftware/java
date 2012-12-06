package com.mgs.fantasi.ui.wireframe;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class GridFactory {
	public static <T> Grid<T> empty(Class<T> type) {
		return new EmptyGrid<T>();
	}

	public static Grid<Wireframe> withDimensions(int x, int y) {
		return new ArrayListGrid<Wireframe>(x, y);
	}

	private static class EmptyGrid<T> implements Grid<T> {
		@Override
		public void setCell(int x, int y, CellContent<T> content) {
			throw new NotImplementedException();
		}

		@Override
		public CellContent<T> getCell(int x, int y) {
			throw new NotImplementedException();
		}

		@Override
		public void fillCells(CellContentGenerator<T> cellContentGenerator) {
			return;
		}

		@Override
		public void itereateCellsWith(CellIterator<T> cellIterator) {
			return;
		}
	}
}
