package com.mgs.fantasi.ui.wireframe;

import com.mgs.fantasi.views.View;

public interface ViewPreprocessor {
	Renderable prepareForRendering(View view);
}
