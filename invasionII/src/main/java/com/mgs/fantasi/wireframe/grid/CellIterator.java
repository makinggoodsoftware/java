package com.mgs.fantasi.wireframe.grid;

public interface CellIterator<T> {
	void on(int x, int y, CellContent<T> cell);
}
