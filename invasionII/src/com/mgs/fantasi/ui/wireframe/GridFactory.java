package com.mgs.fantasi.ui.wireframe;

import com.mgs.fantasi.views.View;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class GridFactory {
	public static <T extends Structurable> Grid<T> empty(Class<T> type) {
		return new EmptyGrid<T>();
	}

	public static Grid<View> withDimensions(int x, int y) {
		return new ArrayListGrid<View>(x, y);
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
		public <Z extends Structurable> Structure<Z> transformInto(StructureTransformer<T, Z> tzStructureTransformer, Structure<Z> into) {
			return null;
		}
	}
}
