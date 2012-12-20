package com.mgs.fantasi.driver.swing.layoutConstruction;

import com.mgs.fantasi.driver.swing.SwingUINativeRenderer;
import com.mgs.fantasi.rendering.Renderable;
import com.mgs.fantasi.rendering.structure.grid.CellContent;
import com.mgs.fantasi.rendering.structure.grid.CellIterator;
import com.mgs.fantasi.rendering.structure.grid.GridStructure;

import java.awt.*;

public class GridLayoutConstructionImpl extends OnGoingLayoutConstructionBaseImpl<GridBagConstraints> {

	public GridLayoutConstructionImpl(LayoutProvider layoutProvider) {
		super(layoutProvider);
	}

	public OnGoingLayoutConstruction<GridBagConstraints> from(GridStructure<Renderable> structure) {
		structure.itereateCellsWith(new CellIterator<Renderable>() {
			@Override
			public void on(int x, int y, CellContent<Renderable> cell) {
				add(cell.getContent()).into(SwingUINativeRenderer.coordinates(x, y, cell.getWidthSizeRatio(), cell.getHeightSizeRatio()));
			}
		});
		return this;
	}

}
