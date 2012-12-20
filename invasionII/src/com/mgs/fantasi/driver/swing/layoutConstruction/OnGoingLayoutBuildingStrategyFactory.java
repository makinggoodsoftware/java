package com.mgs.fantasi.driver.swing.layoutConstruction;

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
			@Override
			public OnGoingChildContentConstruction<Void> add(JPanel childAsNativeComponent) {
				throw new RuntimeException("This ongoing construction is empty, nothing can be added to it");
			}

			@Override
			public void buildInto(JPanel container) {
				//do nothing
			}
		};
	}
}
