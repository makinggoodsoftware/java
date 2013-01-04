package com.mgs.fantasi.wireframe.grid;

public interface CellContentGenerator<T> {
	CellContent<T> generateContentFor(int x, int y);
}
