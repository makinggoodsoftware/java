package com.mgs.fantasi.rendering.wireframe.grid;

public interface CellContentGenerator<T> {
	CellContent<T> generateContentFor(int x, int y);
}
