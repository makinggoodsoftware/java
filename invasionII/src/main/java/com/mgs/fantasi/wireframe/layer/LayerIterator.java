package com.mgs.fantasi.wireframe.layer;

public interface LayerIterator<T> {
	void on(int zIndex, T layer);
}
