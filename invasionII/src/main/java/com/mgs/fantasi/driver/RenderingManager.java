package com.mgs.fantasi.driver;

import com.mgs.fantasi.profile.UIProfile;
import com.mgs.fantasi.wireframe.CollocationInfo;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.tree.Tree;

public interface RenderingManager<T> {
	T render(Tree<Wireframe, CollocationInfo> tree, UIProfile uiProfile);
}
