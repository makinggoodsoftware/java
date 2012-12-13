package com.mgs.fantasi.ui.wireframe;

import com.mgs.fantasi.ui.profile.UIProfile;

import java.awt.*;

public class EmptyContentStructureStrategy extends BaseContentStructureStrategy {
	@Override
	public StructureFactory.StructureType getContentStructureType() {
		return StructureFactory.StructureType.EMPTY;
	}

	@Override
	public Structure<Renderable> buildFrom(UIProfile uiProfile, Dimension dimension) {
		return new EmptyStructure ();
	}
}
