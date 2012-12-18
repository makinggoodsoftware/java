package com.mgs.fantasi.views;

import com.mgs.fantasi.ui.wireframe.Structurable;
import com.mgs.fantasi.ui.wireframe.Wireframe;

public interface View extends Structurable{
	View newCopy();

	Wireframe<View> toWireframe();

	UIProperties getUiProperties ();
}
