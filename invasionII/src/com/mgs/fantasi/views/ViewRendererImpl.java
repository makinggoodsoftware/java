package com.mgs.fantasi.views;

import com.mgs.fantasi.ui.profile.UIProfile;
import com.mgs.fantasi.ui.wireframe.Wireframe;

import java.awt.*;

public class ViewRendererImpl implements ViewRenderer {
	@Override
	public Wireframe render(View view, UIProfile uiProfile, Dimension dimension) {
		return view.render();
	}
}
