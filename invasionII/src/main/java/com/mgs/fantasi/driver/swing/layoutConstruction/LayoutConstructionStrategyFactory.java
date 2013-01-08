package com.mgs.fantasi.driver.swing.layoutConstruction;

import com.mgs.fantasi.views.View;
import com.mgs.fantasi.wireframe.Wireframe;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LayoutConstructionStrategyFactory {


	public SimpleBaseLayoutConstructionStrategyImpl simple() {
		return new SimpleBaseLayoutConstructionStrategyImpl(gridLayoutProvider());
	}

	public LayerBaseLayoutConstructionStrategyImpl layers() {
		return new LayerBaseLayoutConstructionStrategyImpl(layerLayoutProvider());
	}

	public GridBaseLayoutConstructionStrategyImpl grid() {
		return new GridBaseLayoutConstructionStrategyImpl(gridLayoutProvider());
	}

	public LayoutProvider layerLayoutProvider() {
		return new LayoutProvider() {
			@Override
			public LayoutManager getLayoutManager(JPanel container) {
				return new OverlayLayout(container);
			}

			@Override
			public boolean isEmpty() {
				return false;
			}
		};
	}

	public LayoutProvider emptyLayoutProvider() {
		return new LayoutProvider() {
			@Override
			public LayoutManager getLayoutManager(JPanel container) {
				throw new RuntimeException("This is empty!!");
			}

			@Override
			public boolean isEmpty() {
				return true;
			}
		};
	}

	public LayoutProvider gridLayoutProvider() {
		return new LayoutProvider() {
			@Override
			public LayoutManager getLayoutManager(JPanel container) {
				return new GridBagLayout();
			}

			@Override
			public boolean isEmpty() {
				return false;
			}
		};
	}

	public LayoutConstructionStrategy<Void, ? extends Wireframe<View>> empty() {
		return new LayoutConstructionStrategy<Void, Wireframe<View>>() {

			@Override
			public void queueForAddition(View child, Void specifics) {
				throw new RuntimeException("This ongoing construction is empty, nothing can be added to it");
			}

			@Override
			public LayoutConstructionStrategy<Void, Wireframe<View>> fillWith(Wireframe<View> content) {
				throw new RuntimeException("Can't fill an empty structure!");
			}

			@Override
			public List<ToBeAddedWithSpecifics<Void>> getToBeAddedWithSpecifics() {
				return new ArrayList<ToBeAddedWithSpecifics<Void>>();
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
