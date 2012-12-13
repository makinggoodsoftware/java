package com.mgs.fantasi.ui.wireframe;

import com.mgs.fantasi.ui.profile.UIProfile;
import com.mgs.fantasi.views.View;

import java.awt.*;

public abstract class SimpleContentStructureStrategy extends BaseContentStructureStrategy{
	@Override
	public final StructureFactory.StructureType getContentStructureType() {
		return StructureFactory.StructureType.SIMPLE;
	}

	public abstract View getContent();

	public Structure<Renderable> buildFrom(UIProfile uiProfile, Dimension dimension) {
		Renderable content = createRenderable(getContent(), uiProfile, dimension);
		return new SimpleStructure<Renderable>(content);
	}
}
