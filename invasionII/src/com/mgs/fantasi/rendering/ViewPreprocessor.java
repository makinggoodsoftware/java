package com.mgs.fantasi.rendering;

import com.mgs.fantasi.views.View;

public interface ViewPreprocessor {
	Renderable prepareForRendering(View view);
}
