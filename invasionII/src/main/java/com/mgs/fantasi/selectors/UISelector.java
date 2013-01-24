package com.mgs.fantasi.selectors;

import com.mgs.fantasi.wireframe.CollocationInfo;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.tree.Tree;

public interface UISelector {
	public boolean appliesTo(Tree<Wireframe, CollocationInfo> renderable);
}
