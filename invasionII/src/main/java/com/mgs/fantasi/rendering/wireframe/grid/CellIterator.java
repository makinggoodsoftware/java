package com.mgs.fantasi.rendering.wireframe.grid;

public interface CellIterator<T> {

	void on(int x, int y, CellContent<T> cell);
}
