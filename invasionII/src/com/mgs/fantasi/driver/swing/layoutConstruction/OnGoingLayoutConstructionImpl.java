package com.mgs.fantasi.driver.swing.layoutConstruction;

import com.mgs.fantasi.driver.swing.SwingUINativeRenderer;
import com.mgs.fantasi.rendering.Renderable;
import com.mgs.fantasi.rendering.structure.grid.CellContent;
import com.mgs.fantasi.rendering.structure.grid.CellIterator;
import com.mgs.fantasi.rendering.structure.grid.GridStructure;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class OnGoingLayoutConstructionImpl<T> implements OnGoingLayoutConstruction<T> {
	private final LayoutProvider layoutProvider;
	private List<OnGoingChildContentConstruction<T>> toBeAdded = new ArrayList<OnGoingChildContentConstruction<T>>();

	public OnGoingLayoutConstructionImpl(LayoutProvider layoutProvider) {
		this.layoutProvider = layoutProvider;
	}

	public OnGoingLayoutConstruction<T> processGridStructure(GridStructure<Renderable> structure) {
		structure.itereateCellsWith(new CellIterator<Renderable>() {
			@Override
			public void on(int x, int y, CellContent<Renderable> cell) {
				GridBagConstraints coordinates = SwingUINativeRenderer.coordinates(x, y, cell.getWidthSizeRatio(), cell.getHeightSizeRatio());
				add(cell.getContent()).into((T) coordinates);
			}
		});
		return this;
	}

	@Override
	public OnGoingChildContentConstruction<T> add(Renderable child) {
		return new OnGoingChildContentConstruction<T>(this, child);
	}

	@Override
	public void buildInto(JPanel container, SwingUINativeRenderer renderer){
		container.setLayout(layoutProvider.getLayoutManager(container));
		for (OnGoingChildContentConstruction onGoingChildContentConstruction : toBeAdded) {
			Renderable content = onGoingChildContentConstruction.getCellContent();
			JPanel uiNativeElement = renderer.render(content);
			container.add(uiNativeElement, onGoingChildContentConstruction.getSpecifics());
		}
	}

	public void doAdd(OnGoingChildContentConstruction<T> onGoingCellLayoutConstruction) {
		toBeAdded.add(onGoingCellLayoutConstruction);
	}
}
