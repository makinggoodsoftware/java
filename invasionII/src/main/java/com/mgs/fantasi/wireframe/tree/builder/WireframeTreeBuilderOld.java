package com.mgs.fantasi.wireframe.tree.builder;

import com.mgs.fantasi.properties.UIPropertiesBuilder;
import com.mgs.fantasi.wireframe.tree.WireframeTree;

public interface WireframeTreeBuilderOld {
	WireframeTree build();

	UIPropertiesBuilder getUiPropertiesBuilder();

	String getName();
}
