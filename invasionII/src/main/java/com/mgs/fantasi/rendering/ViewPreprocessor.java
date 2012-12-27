package com.mgs.fantasi.rendering;

import com.mgs.fantasi.profile.UIProfile;
import com.mgs.fantasi.views.View;

public interface ViewPreprocessor {
	Renderable prepareForRendering(View view, UIProfile uiProfile);
}
