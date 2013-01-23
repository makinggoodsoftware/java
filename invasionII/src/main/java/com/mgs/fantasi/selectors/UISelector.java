package com.mgs.fantasi.selectors;

import com.mgs.fantasi.wireframe.CollocationInfo;
import com.mgs.fantasi.wireframe.Tree;
import com.mgs.fantasi.wireframe.Wireframe;

public interface UISelector {
	public boolean appliesTo(Tree<Wireframe, CollocationInfo> renderable);
}
