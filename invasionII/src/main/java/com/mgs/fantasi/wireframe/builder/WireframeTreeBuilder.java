package com.mgs.fantasi.wireframe.builder;

import com.mgs.fantasi.properties.UIPropertiesBuilder;
import com.mgs.fantasi.wireframe.WireframeContainer;
import com.mgs.fantasi.wireframe.WireframeContentFactory;

public interface WireframeTreeBuilder {
	WireframeContainer build(WireframeContentFactory wireframeContentFactory);

	UIPropertiesBuilder getUiPropertiesBuilder();

	String getName();
}
