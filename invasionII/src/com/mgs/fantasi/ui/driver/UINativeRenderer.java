package com.mgs.fantasi.ui.driver;

import com.mgs.fantasi.ui.profile.UIProfile;
import com.mgs.fantasi.ui.wireframe.Renderable;

public interface UINativeRenderer<T> {
	T render(Renderable renderable, UIProfile uiProfile);

}
