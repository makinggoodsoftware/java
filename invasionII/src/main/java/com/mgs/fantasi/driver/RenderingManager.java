package com.mgs.fantasi.driver;

import com.mgs.fantasi.styles.UIProfile;
import com.mgs.fantasi.wireframe.WireframeTree;

public interface RenderingManager<T> {
	T render(WireframeTree wireframeTree, UIProfile uiProfile);
}
