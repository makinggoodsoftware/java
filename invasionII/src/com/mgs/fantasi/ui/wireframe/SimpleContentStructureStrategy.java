package com.mgs.fantasi.ui.wireframe;

import com.mgs.fantasi.ui.profile.UIProfile;
import com.mgs.fantasi.views.View;
import com.mgs.fantasi.views.ViewRenderer;

import java.awt.*;

public abstract class SimpleContentStructureStrategy implements ContentStructureStrategy{
	@Override
	public final StructureFactory.StructureType getContentStructureType() {
		return StructureFactory.StructureType.SIMPLE;
	}

	public abstract View getContent();

	public Structure<Renderable> buildFrom(ViewRenderer viewRenderer, UIProfile uiProfile, Dimension dimension) {
		Renderable content = viewRenderer.createRenderable(getContent(), uiProfile, dimension);
		return new SimpleStructure<Renderable>(content);
	}
}
