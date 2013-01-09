package com.mgs.fantasi.driver.swing;

import com.mgs.fantasi.views.View;
import com.mgs.fantasi.wireframe.GridWireframe;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.grid.CellContent;
import com.mgs.fantasi.wireframe.grid.CellIterator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static com.mgs.fantasi.driver.swing.SwingUtils.coordinates;

public class GridToBeAddedManager implements ToBeAddedManager {

	@Override
	public List<ToBeAddedBuilder<?, JPanel>> process(Wireframe<View> grid) {
		final List<ToBeAddedBuilder<?, JPanel>> toBeAddedBuilderList = new ArrayList<ToBeAddedBuilder<?, JPanel>>();
		((GridWireframe<View>) grid).itereateCellsWith(new CellIterator<View>() {
			@Override
			public void on(int x, int y, CellContent<View> cell) {
				ToBeAddedBuilder<GridBagConstraints, JPanel> toBeAddedBuilder = new ToBeAddedBuilder<GridBagConstraints, JPanel>(coordinates(x, y, cell.getWidthSizeRatio(), cell.getHeightSizeRatio()), cell.getContent());
				toBeAddedBuilderList.add(toBeAddedBuilder);
			}
		});
		return toBeAddedBuilderList;
	}

}
