package com.mgs.fantasi.wireframe.tree.builder;

import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.tree.WireframeTree;

public interface WireframeTreeBuilder {
	WireframeTree build();

	Wireframe getRootWireframe();

	String getName();
}
