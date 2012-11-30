package com.mgs.fantasi.ui.wireframe;

public interface Grid<T> {
	void setCell(int x, int y, T content);

	T getCell(int x, int y);

	void fillCells(CellContentGenerator<T> cellContentGenerator);

	void itereateCellsWith(CellIterator<T> cellIterator);


}
