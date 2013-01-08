package com.mgs.fantasi.driver;

import com.mgs.fantasi.styles.UIProfile;
import com.mgs.fantasi.views.View;

public interface RenderingProcessFactory<T> {
	RenderingProcess<T> newRenderingProcess(View view, UIProfile uiProfile);
}
