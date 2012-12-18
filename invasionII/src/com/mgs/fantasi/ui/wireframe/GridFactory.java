package com.mgs.fantasi.ui.wireframe;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class GridFactory {
	public static <T extends Structurable> Grid<T> empty(Class<T> type) {
		return new EmptyGrid<T>();
	}

	public static <T extends Structurable>Grid<T> withDimensions(int x, int y) {
		return new ArrayListGrid<T>(x, y);
	}

	public static class EmptyGrid<T extends Structurable> implements Grid<T> {
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

		@Override
		public StructureFactory.StructureType getType() {
			return StructureFactory.StructureType.EMPTY;
		}
	}
}
