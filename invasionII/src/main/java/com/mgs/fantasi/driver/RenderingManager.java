package com.mgs.fantasi.driver;

import com.mgs.fantasi.profile.UIProfile;
import com.mgs.fantasi.structure.Structure;

public interface RenderingManager<T> {
	T render(Structure tree, UIProfile uiProfile);
}
