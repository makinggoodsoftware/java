package com.mgs.fantasi.views;

import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.rendering.wireframe.Wireframe;

public interface View{
	Wireframe buildContent();

	UIProperties getUiProperties ();

    String getName();
}
