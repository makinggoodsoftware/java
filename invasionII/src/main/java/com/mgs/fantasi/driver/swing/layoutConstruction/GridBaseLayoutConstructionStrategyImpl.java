package com.mgs.fantasi.driver.swing.layoutConstruction;

import com.mgs.fantasi.driver.swing.SwingUtils;
import com.mgs.fantasi.rendering.wireframe.GridWireframe;
import com.mgs.fantasi.rendering.wireframe.grid.CellContent;
import com.mgs.fantasi.rendering.wireframe.grid.CellIterator;

import java.awt.*;

public class GridBaseLayoutConstructionStrategyImpl extends BaseLayoutConstructionStrategyStrategy<GridBagConstraints, GridWireframe> {

	public GridBaseLayoutConstructionStrategyImpl(LayoutProvider layoutProvider) {
		super(layoutProvider);
	}


    @Override
    public GridBaseLayoutConstructionStrategyImpl fillWith(GridWireframe content) {
        content.itereateCellsWith(new CellIterator() {
            @Override
            public void on(int x, int y, CellContent cell) {
                queueForAddition(cell.getContent()).into(SwingUtils.coordinates(x, y, cell.getWidthSizeRatio(), cell.getHeightSizeRatio()));
            }
        });
        return this;
    }
}
