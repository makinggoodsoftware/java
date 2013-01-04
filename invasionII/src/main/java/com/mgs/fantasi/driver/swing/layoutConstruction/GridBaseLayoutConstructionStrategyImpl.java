package com.mgs.fantasi.driver.swing.layoutConstruction;

import com.mgs.fantasi.driver.swing.SwingUtils;
import com.mgs.fantasi.views.View;
import com.mgs.fantasi.wireframe.GridWireframe;
import com.mgs.fantasi.wireframe.grid.CellContent;
import com.mgs.fantasi.wireframe.grid.CellIterator;

import java.awt.*;

public class GridBaseLayoutConstructionStrategyImpl extends BaseLayoutConstructionStrategy<GridBagConstraints, GridWireframe<View>> {

	public GridBaseLayoutConstructionStrategyImpl(LayoutProvider layoutProvider) {
		super(layoutProvider);
	}


	@Override
	public LayoutConstructionStrategy<GridBagConstraints, GridWireframe<View>> fillWith(GridWireframe<View> content) {
		content.itereateCellsWith(new CellIterator<View>() {
			@Override
			public void on(int x, int y, CellContent<View> cell) {
				queueForAddition(cell.getContent(), SwingUtils.coordinates(x, y, cell.getWidthSizeRatio(), cell.getHeightSizeRatio()));
			}
		});
		return this;
	}

}
