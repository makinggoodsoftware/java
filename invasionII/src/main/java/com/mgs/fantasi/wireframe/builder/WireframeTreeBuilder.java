package com.mgs.fantasi.wireframe.builder;

import com.mgs.fantasi.properties.UIPropertiesBuilder;
import com.mgs.fantasi.wireframe.CollocationInfo;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.WireframeContentFactory;
import com.mgs.tree.Tree;

public interface WireframeTreeBuilder {
	Tree<Wireframe, CollocationInfo> build(WireframeContentFactory wireframeContentFactory);

	UIPropertiesBuilder getUiPropertiesBuilder();

	String getName();
}
