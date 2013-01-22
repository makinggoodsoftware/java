package com.mgs.fantasi.views;

import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.wireframe.Tree;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.WireframeContentFactory;

public interface WireframeTreeBuilder {
	Tree<Wireframe> build(WireframeContentFactory wireframeContentFactory);

	UIProperties getUiProperties();

	String getName();
}
