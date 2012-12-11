package com.mgs.fantasi.views;

import com.mgs.fantasi.ui.profile.UIProfile;
import com.mgs.fantasi.ui.wireframe.Wireframe;

import java.awt.*;

public interface ViewRenderer {
	Wireframe render(View view, UIProfile uiProfile, Dimension dimension);
}
