package com.mgs.fantasi.ui.wireframe;

import com.mgs.fantasi.ui.profile.UIProfile;
import com.mgs.fantasi.views.View;
import com.mgs.fantasi.views.ViewRenderer;

import java.awt.*;

public abstract class GridContentStructureStrategy implements ContentStructureStrategy{
	@Override
	public final StructureFactory.StructureType getContentStructureType() {
		return StructureFactory.StructureType.GRID;
	}

	public abstract Dimension getDimension();

	public abstract CellContent<View> getCellContentFor(int x, int y);

	public Structure<Renderable> buildFrom(final ViewRenderer viewRenderer, final UIProfile uiProfile, final Dimension dimension) {
		ArrayListGrid<Renderable> structure = new ArrayListGrid<Renderable>(getDimension());
		structure.fillCells(new CellContentGenerator<Renderable>() {
			@Override
			public CellContent<Renderable> generateContentFor(int x, int y) {
				CellContent<View> beforeTransformation = getCellContentFor(x, y);
				return new CellContent<Renderable>(
					viewRenderer.createRenderable(beforeTransformation.getContent(), uiProfile, dimension),
					beforeTransformation.getHeightSizeRatio(),
					beforeTransformation.getWidthSizeRatio()
				);
			}
		});
		return structure;
	}
}
