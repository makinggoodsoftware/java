package com.mgs.fantasi.ui.wireframe;

import com.mgs.fantasi.views.View;

public abstract class SimpleContentStructureStrategy implements ContentStructureStrategy{
	@Override
	public final StructureFactory.StructureType getContentStructureType() {
		return StructureFactory.StructureType.SIMPLE;
	}

	public abstract View getContent();
}
