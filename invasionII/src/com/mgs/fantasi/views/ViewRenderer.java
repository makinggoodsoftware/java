package com.mgs.fantasi.views;

import com.mgs.fantasi.profile.UIProfile;
import com.mgs.fantasi.rendering.Renderable;

import java.awt.*;

public interface ViewRenderer {
	Renderable createRenderable(View view, UIProfile uiProfile, Dimension dimension);
}
