package com.mgs.fantasi.rendering.structure.layer;

import com.mgs.fantasi.Structurable;

public interface LayerIterator<T extends Structurable> {
	void on(int zIndex, T layer);
}
