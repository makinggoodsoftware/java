package com.mgs.fantasi.ui.wireframe;

import com.mgs.fantasi.views.View;

import java.awt.*;

public abstract class GridContentStructureStrategy implements ContentStructureStrategy{
	@Override
	public final StructureFactory.StructureType getContentStructureType() {
		return StructureFactory.StructureType.GRID;
	}

	public abstract Dimension getDimension();

	public abstract CellContent<View> getCellContentFor(int x, int y);
}
