package com.mgs.fantasi.views;

import com.mgs.fantasi.Structurable;
import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.rendering.wireframe.Wireframe;

public interface View extends Structurable {
	View newCopy();

	Wireframe<View> buildChildViews();

	UIProperties getUiProperties ();

	UIProperties takeUiPropertiesSnapshot();
}