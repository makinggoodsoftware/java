package com.mgs.fantasi.ui.wireframe;

import com.mgs.fantasi.ui.profile.UIProfile;
import com.mgs.fantasi.views.ViewRenderer;

import java.awt.*;

public interface ContentStructureStrategy {
	public StructureFactory.StructureType getContentStructureType();

	Structure<Renderable> buildFrom(ViewRenderer viewRenderer, UIProfile uiProfile, Dimension dimension);
}
