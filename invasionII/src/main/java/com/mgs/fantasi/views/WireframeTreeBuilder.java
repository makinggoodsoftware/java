package com.mgs.fantasi.views;

import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.wireframe.CollocationInfo;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.WireframeContentFactory;
import com.mgs.tree.Tree;

public interface WireframeTreeBuilder {
	Tree<Wireframe, CollocationInfo> build(WireframeContentFactory wireframeContentFactory);

	UIProperties getUiProperties();

	String getName();
}
