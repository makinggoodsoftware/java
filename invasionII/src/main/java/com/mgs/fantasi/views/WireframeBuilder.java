package com.mgs.fantasi.views;

import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.wireframe.WireframeContentFactory;
import com.mgs.fantasi.wireframe.WireframeTree;

public interface WireframeBuilder {
	WireframeTree build(WireframeContentFactory wireframeContentFactory);

	UIProperties getUiProperties();

	String getName();
}
