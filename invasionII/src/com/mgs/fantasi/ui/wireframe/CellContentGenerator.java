package com.mgs.fantasi.ui.wireframe;

public interface CellContentGenerator<T> {
	CellContent<T> generateContentFor(int x, int y);
}
