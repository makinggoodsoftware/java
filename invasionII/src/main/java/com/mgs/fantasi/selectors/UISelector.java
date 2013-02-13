package com.mgs.fantasi.selectors;

import com.mgs.fantasi.wireframe.WireframeNode;

public interface UISelector {
	public boolean appliesTo(WireframeNode renderable);
}
