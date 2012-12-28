package com.mgs.fantasi.rendering.wireframe.layer;

import com.mgs.fantasi.Structurable;

public interface LayerIterator<T extends Structurable> {
	void on(int zIndex, T layer);
}
