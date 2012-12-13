package com.mgs.fantasi.ui.wireframe;

public interface Grid<T extends Structurable> extends Structure<T> {
	void setCell(int x, int y, CellContent<T> content);

	CellContent<T> getCell(int x, int y);

	void fillCells(CellContentGenerator<T> cellContentGenerator);

	void itereateCellsWith(CellIterator<T> cellIterator);


}
