package com.mgs.fantasi.driver.swing.layoutConstruction;

import com.mgs.fantasi.views.View;
import com.mgs.fantasi.wireframe.Wireframe;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LayoutConstructionStrategyFactory {


	public SimpleBaseLayoutConstructionStrategyImpl simple() {
		return new SimpleBaseLayoutConstructionStrategyImpl(new LayoutProvider() {
			@Override
			public LayoutManager getLayoutManager(JPanel container) {
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

	public GridBaseLayoutConstructionStrategyImpl grid() {
		return new GridBaseLayoutConstructionStrategyImpl(new LayoutProvider() {
			@Override
			public LayoutManager getLayoutManager(JPanel container) {
				return new GridBagLayout();
			}
		});
	}

	public LayoutConstructionStrategy<Void, ? extends Wireframe> empty() {
		return new LayoutConstructionStrategy<Void, Wireframe>() {

			@Override
			public void queueForAddition(View child, Void specifics) {
				throw new RuntimeException("This ongoing construction is empty, nothing can be added to it");
			}

			@Override
			public LayoutConstructionStrategy<Void, Wireframe> fillWith(Wireframe content) {
				throw new RuntimeException("Can't fill an empty structure!");
			}

			@Override
			public List<OnGoingChildAddition<Void>> getToBeAdded() {
				return new ArrayList<OnGoingChildAddition<Void>>();
			}

			@Override
			public LayoutManager getLayoutManager(JPanel container) {
				throw new RuntimeException("An empty structure doesn't have a layout!");
			}

			@Override
			public boolean isEmpty() {
				return true;
			}
		};
	}
}
