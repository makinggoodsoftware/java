package com.mgs.fantasi.wireframe.builder;

import com.mgs.fantasi.properties.UIPropertiesBuilder;
import com.mgs.fantasi.wireframe.WireframeContainer;

public interface WireframeTreeBuilder {
	WireframeContainer build();

	UIPropertiesBuilder getUiPropertiesBuilder();

	String getName();
}
