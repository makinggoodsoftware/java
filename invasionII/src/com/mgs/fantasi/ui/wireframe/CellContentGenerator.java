package com.mgs.fantasi.ui.wireframe;

public interface CellContentGenerator<T> {
	T generateContentFor(int x, int y);
}
