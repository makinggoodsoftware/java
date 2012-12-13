package com.mgs.fantasi.ui.wireframe;

import com.mgs.fantasi.ui.profile.UIProfile;
import com.mgs.fantasi.views.ViewRenderer;

import java.awt.*;

public class EmptyContentStructureStrategy implements ContentStructureStrategy {
	@Override
	public StructureFactory.StructureType getContentStructureType() {
		return StructureFactory.StructureType.EMPTY;
	}

	@Override
	public Structure<Renderable> buildFrom(ViewRenderer viewRenderer, UIProfile uiProfile, Dimension dimension) {
		return new EmptyStructure ();
	}
}
