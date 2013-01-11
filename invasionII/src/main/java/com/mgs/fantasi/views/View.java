package com.mgs.fantasi.views;

import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.WireframeFactory;

public interface View {
	Wireframe<View> buildContent(WireframeFactory<View> wireframeFactory);

	UIProperties getUiProperties();

	String getName();
}
