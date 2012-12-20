package com.mgs.fantasi.driver.swing.layoutConstruction;

import com.mgs.fantasi.driver.swing.SwingUINativeRenderer;
import com.mgs.fantasi.rendering.Renderable;
import com.mgs.fantasi.rendering.structure.grid.CellContent;
import com.mgs.fantasi.rendering.structure.grid.CellIterator;
import com.mgs.fantasi.rendering.structure.grid.GridStructure;

import java.awt.*;

public class GridBaseLayoutConstructionStrategyImpl extends BaseLayoutConstructionStrategyStrategy<GridBagConstraints> {

	public GridBaseLayoutConstructionStrategyImpl(LayoutProvider layoutProvider) {
		super(layoutProvider);
	}

	public LayoutConstructionStrategy<GridBagConstraints> from(GridStructure<Renderable> structure) {
		structure.itereateCellsWith(new CellIterator<Renderable>() {
			@Override
			public void on(int x, int y, CellContent<Renderable> cell) {
				queueForAddition(cell.getContent()).into(SwingUINativeRenderer.coordinates(x, y, cell.getWidthSizeRatio(), cell.getHeightSizeRatio()));
			}
		});
		return this;
	}

}
