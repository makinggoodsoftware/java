package com.mgs.fantasi.rendering.structure.grid;

public interface CellIterator<T> {

	void on(int x, int y, CellContent<T> cell);
}
