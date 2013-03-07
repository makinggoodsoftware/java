package com.mgs.fantasi.selectors;

import com.mgs.fantasi.structure.treeAux.WireframeNode;

public interface UISelector {
	public boolean appliesTo(WireframeNode renderable);
}
