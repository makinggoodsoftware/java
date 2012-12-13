package com.mgs.fantasi.ui.wireframe;

import com.mgs.fantasi.views.View;

import java.util.List;

public abstract class LayeredContentStructureStrategy implements ContentStructureStrategy{
	@Override
	public final StructureFactory.StructureType getContentStructureType() {
		return StructureFactory.StructureType.LAYERS;
	}

	public abstract List<View> getLayers();
}
