package com.mgs.fantasi.driver.swing.layoutConstruction;

import com.mgs.fantasi.driver.swing.SwingUINativeRenderer;
import com.mgs.fantasi.rendering.wireframe.GridWireframe;
import com.mgs.fantasi.rendering.wireframe.grid.CellContent;
import com.mgs.fantasi.rendering.wireframe.grid.CellIterator;
import com.mgs.fantasi.views.View;

import java.awt.*;

public class GridBaseLayoutConstructionStrategyImpl extends BaseLayoutConstructionStrategyStrategy<GridBagConstraints> {

	public GridBaseLayoutConstructionStrategyImpl(LayoutProvider layoutProvider) {
		super(layoutProvider);
	}

	public LayoutConstructionStrategy<GridBagConstraints> from(GridWireframe<View> grid) {
		grid.itereateCellsWith(new CellIterator<View>() {
			@Override
			public void on(int x, int y, CellContent<View> cell) {
			    queueForAddition(cell.getContent()).into(SwingUINativeRenderer.coordinates(x, y, cell.getWidthSizeRatio(), cell.getHeightSizeRatio()));
			}
		});
		return this;
	}

}
