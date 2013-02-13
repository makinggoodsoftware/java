package com.mgs.fantasi.driver;

import com.mgs.fantasi.profile.UIProfile;
import com.mgs.fantasi.wireframe.tree.WireframeTree;

public interface RenderingManager<T> {
	T render(WireframeTree tree, UIProfile uiProfile);
}
