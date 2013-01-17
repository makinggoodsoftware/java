package com.mgs.fantasi.views;

import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.WireframeContentFactory;

public interface WireframeBuilder {
	Wireframe build(WireframeContentFactory wireframeContentFactory);

	UIProperties getUiProperties();

	String getName();
}
