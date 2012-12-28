package com.mgs.fantasi.driver.swing.layoutConstruction;

import com.mgs.fantasi.driver.swing.SwingUINativeRenderer;
import com.mgs.fantasi.profile.UIProfileFactory;
import com.mgs.fantasi.views.View;

import javax.swing.*;
import java.awt.*;

public class OnGoingLayoutBuildingStrategyFactory {
	public GridBaseLayoutConstructionStrategyImpl grid(){
		return new GridBaseLayoutConstructionStrategyImpl(new LayoutProvider(){
			@Override
			public LayoutManager getLayoutManager(JPanel container){
				return new GridBagLayout();
			}
		});
	}

	public LayerBaseLayoutConstructionStrategyImpl layers() {
		return new LayerBaseLayoutConstructionStrategyImpl(new LayoutProvider() {
			@Override
			public LayoutManager getLayoutManager(JPanel container) {
				return new OverlayLayout(container);
			}
		});
	}


	public SimpleBaseLayoutConstructionStrategyImpl simple() {
		return new SimpleBaseLayoutConstructionStrategyImpl(new LayoutProvider() {
			@Override
			public LayoutManager getLayoutManager(JPanel container) {
				return new GridBagLayout();
			}
		});
	}

	public LayoutConstructionStrategy<Void> empty() {
		return new LayoutConstructionStrategy<Void>() {

            @Override
			public OnGoingChildAddition<Void> queueForAddition(View child) {
				throw new RuntimeException("This ongoing construction is empty, nothing can be added to it");
			}

			@Override
			public void buildInto(JPanel container, SwingUINativeRenderer renderer, UIProfileFactory uiProfileFactory) {
				//do nothing
			}
		};
	}
}
