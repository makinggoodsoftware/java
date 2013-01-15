package com.mgs.fantasi.driver;

import com.mgs.fantasi.styles.UIProfile;
import com.mgs.fantasi.wireframe.Wireframe;

public interface RenderingProcessFactory<T> {
	RenderingProcess<T> newRenderingProcess(Wireframe<Wireframe> wireframe, UIProfile uiProfile);
}
