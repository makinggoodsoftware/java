package com.mgs.fantasi.ui.wireframe;

import com.mgs.fantasi.ui.profile.UIProfile;
import com.mgs.fantasi.views.ViewRenderer;

import java.awt.*;

public interface ContentStructureStrategy extends ViewRenderer{
	public StructureFactory.StructureType getContentStructureType();

	Structure<Renderable> buildFrom(UIProfile uiProfile, Dimension dimension);
}
