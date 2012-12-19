package com.mgs.fantasi.rendering.structure.grid;

public interface CellContentGenerator<T> {
	CellContent<T> generateContentFor(int x, int y);
}
