package com.mgs.fantasi.driver.swing.layoutConstruction;

import com.mgs.fantasi.driver.swing.SwingUINativeRenderer;
import com.mgs.fantasi.rendering.Renderable;
import com.mgs.fantasi.rendering.structure.grid.GridStructure;

import javax.swing.*;
import java.awt.*;

public class OnGoingLayoutBuildingStrategyFactory {
	public OnGoingLayoutConstruction<GridBagConstraints> grid(){
		return new OnGoingLayoutConstructionImpl<GridBagConstraints>(new LayoutProvider(){
			@Override
			public LayoutManager getLayoutManager(JPanel container){
				return new GridBagLayout();
			}
		});
	}

	public OnGoingLayoutConstruction<Integer> layers() {
		return new OnGoingLayoutConstructionImpl<Integer>(new LayoutProvider() {
			@Override
			public LayoutManager getLayoutManager(JPanel container) {
				return new OverlayLayout(container);
			}
		});
	}


	public OnGoingLayoutConstruction<Void> empty() {
		return new OnGoingLayoutConstruction<Void>() {
			public OnGoingLayoutConstruction<Void> processGridStructure(GridStructure<Renderable> structure) {
				throw new RuntimeException("This ongoing construction is empty, nothing can be added to it");
			}

			@Override
			public OnGoingChildContentConstruction<Void> add(Renderable child) {
				throw new RuntimeException("This ongoing construction is empty, nothing can be added to it");
			}

			@Override
			public void buildInto(JPanel container, SwingUINativeRenderer renderer) {
				//do nothing
			}
		};
	}
}
