package com.mgs.fantasi.driver;

import com.mgs.fantasi.profile.UIProfile;
import com.mgs.fantasi.wireframe.WireframeContainer;

public interface RenderingManager<T> {
	T render(WireframeContainer tree, UIProfile uiProfile);
}
