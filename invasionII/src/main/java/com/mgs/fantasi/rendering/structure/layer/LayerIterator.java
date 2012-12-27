package com.mgs.fantasi.rendering.structure.layer;

public interface LayerIterator<T> {
	void on(int zIndex, T layer);
}
