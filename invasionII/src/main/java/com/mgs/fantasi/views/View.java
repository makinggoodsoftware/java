package com.mgs.fantasi.views;

import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.wireframe.PlaceholderFactory;
import com.mgs.fantasi.wireframe.Wireframe;

public interface View {
	Wireframe<View> buildContent(PlaceholderFactory placeholderFactory);

	UIProperties getUiProperties();

	String getName();
}
