package com.mgs.fantasi.rendering.structure.grid;

import com.mgs.fantasi.Structurable;
import com.mgs.fantasi.rendering.structure.Structure;

public interface GridStructure<T extends Structurable> extends Structure<T> {
	void setCell(int x, int y, CellContent<T> content);

	CellContent<T> getCell(int x, int y);

	void fillCells(CellContentGenerator<T> cellContentGenerator);

	void itereateCellsWith(CellIterator<T> cellIterator);


}
