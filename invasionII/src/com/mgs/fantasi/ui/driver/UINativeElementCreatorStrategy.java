package com.mgs.fantasi.ui.driver;

import com.mgs.fantasi.ui.profile.UIProfile;
import com.mgs.fantasi.ui.wireframe.Wireframe;

public interface UINativeElementCreatorStrategy<T> {
	T create(Wireframe wireframe, UIProfile uiProfile);

}
