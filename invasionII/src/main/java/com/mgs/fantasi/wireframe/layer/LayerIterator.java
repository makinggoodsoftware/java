package com.mgs.fantasi.wireframe.layer;

import com.mgs.fantasi.views.View;

public interface LayerIterator {
	void on(int zIndex, View layer);
}
