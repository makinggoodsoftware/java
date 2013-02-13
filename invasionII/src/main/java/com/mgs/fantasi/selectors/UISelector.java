package com.mgs.fantasi.selectors;

import com.mgs.fantasi.wireframe.tree.WireframeNode;

public interface UISelector {
	public boolean appliesTo(WireframeNode renderable);
}
