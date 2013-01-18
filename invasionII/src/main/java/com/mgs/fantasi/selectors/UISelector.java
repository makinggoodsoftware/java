package com.mgs.fantasi.selectors;

import com.mgs.fantasi.wireframe.WireframeTree;

public interface UISelector {
	public boolean appliesTo(WireframeTree renderable);
}
