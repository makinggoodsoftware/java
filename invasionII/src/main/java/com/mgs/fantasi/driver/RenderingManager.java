package com.mgs.fantasi.driver;

import com.mgs.fantasi.styles.UIProfile;
import com.mgs.fantasi.wireframe.Tree;
import com.mgs.fantasi.wireframe.Wireframe;

public interface RenderingManager<T> {
	T render(Tree<Wireframe> tree, UIProfile uiProfile);
}
