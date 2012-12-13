package com.mgs.fantasi.views;

import com.mgs.fantasi.ui.profile.UIProfile;
import com.mgs.fantasi.ui.wireframe.Renderable;

import java.awt.*;

public interface ViewRenderer {
	Renderable createRenderable(View view, UIProfile uiProfile, Dimension dimension);
}
