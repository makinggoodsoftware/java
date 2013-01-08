package com.mgs.fantasi.driver.swing;

import com.mgs.fantasi.driver.RenderingContext;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelCreationStrategy;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelCreationStrategyFactory;
import com.mgs.fantasi.driver.swing.layoutProvider.LayoutProvider;
import com.mgs.fantasi.driver.swing.layoutProvider.LayoutProviderFactory;
import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.styles.StyleManager;
import com.mgs.fantasi.styles.UIProfile;
import com.mgs.fantasi.views.View;
import com.mgs.fantasi.wireframe.*;
import com.mgs.fantasi.wireframe.grid.CellContent;
import com.mgs.fantasi.wireframe.grid.CellIterator;
import com.mgs.fantasi.wireframe.layer.LayerIterator;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

import static com.mgs.fantasi.driver.swing.SwingUtils.coordinates;
import static com.mgs.fantasi.properties.measurements.Fractions.all;

public class JPanelRenderingContext implements RenderingContext<JPanel> {
	private final UIProfile uiProfile;
	private final LayoutProviderFactory layoutProviderFactory;
	private final StyleManager styleManager;
	private final JPanelCreationStrategyFactory jPanelCreationStrategyFactory;

	public JPanelRenderingContext(UIProfile uiProfile, LayoutProviderFactory layoutProviderFactory, StyleManager styleManager, JPanelCreationStrategyFactory jPanelCreationStrategyFactory) {
		this.uiProfile = uiProfile;
		this.layoutProviderFactory = layoutProviderFactory;
		this.styleManager = styleManager;
		this.jPanelCreationStrategyFactory = jPanelCreationStrategyFactory;
	}

	public JPanelCreationStrategyFactory getJPanelCreationStrategyFactory() {
		return jPanelCreationStrategyFactory;
	}

	public LayoutProviderFactory getLayoutProviderFactory() {
		return layoutProviderFactory;
	}

	public StyleManager getStyleManager() {
		return styleManager;
	}

	public UIProfile getUiProfile() {
		return uiProfile;
	}

	@Override
	public JPanel render(View view) {
		return new JPanelRenderingProcessFactory(this).newRenderingProcess(view).render();
	}

	public static class JPanelRenderingProcessFactory {
		private final JPanelRenderingContext jPanelRenderingContext;


		public JPanelRenderingProcessFactory(JPanelRenderingContext jPanelRenderingContext) {
			this.jPanelRenderingContext = jPanelRenderingContext;
		}

		public JPanelRenderingProcess newRenderingProcess(View view) {
			UIProperties uiPropertiesWithStylesApplied = getStyleManager().applyStyles(view.getUiProperties(), getUIProfile().findStylesFor(view));
			Wireframe<View> from = view.buildContent();

			JPanelCreationStrategy baseCreationStrategy = getJPanelCreationStrategyFactory().forUIProperties(uiPropertiesWithStylesApplied);
			final Map<Object, JPanelRenderingProcess> childrenProcesses = new HashMap<Object, JPanelRenderingProcess>();

			switch (from.getType()) {
				case GRID:
					GridWireframe<View> grid = (GridWireframe<View>) from;
					grid.itereateCellsWith(new CellIterator<View>() {
						@Override
						public void on(int x, int y, CellContent<View> cell) {
							childrenProcesses.put(coordinates(x, y, cell.getWidthSizeRatio(), cell.getHeightSizeRatio()), newRenderingProcess(cell.getContent()));
						}
					});
					break;
				case SIMPLE:
					RectangleWireframe<View> rectangle = (RectangleWireframe<View>) from;
					if (rectangle.getContent() != null) {
						childrenProcesses.put(coordinates(0, 0, all(), all()), newRenderingProcess(rectangle.getContent()));
					}
					break;
				case LAYERS:
					LayeredWireframe<View> layers = (LayeredWireframe<View>) from;
					layers.iterateInCrescendo(new LayerIterator<View>() {
						@Override
						public void on(int zIndex, View layer) {
							childrenProcesses.put(zIndex, newRenderingProcess(layer));
						}
					});
					break;
				case DELEGATE:
					DelegateWireframe<View> delegate = (DelegateWireframe<View>) from;
					childrenProcesses.put(coordinates(0, 0, all(), all()), newRenderingProcess(delegate.getContent()));
					break;
			}

			LayoutProvider layoutProvider = getLayoutConstructionManager().translateTypeIntoLayoutProvider(from);
			return new JPanelRenderingProcess(baseCreationStrategy, childrenProcesses, layoutProvider);
		}

		private UIProfile getUIProfile() {
			return jPanelRenderingContext.getUiProfile();
		}

		private JPanelCreationStrategyFactory getJPanelCreationStrategyFactory() {
			return jPanelRenderingContext.getJPanelCreationStrategyFactory();
		}

		private StyleManager getStyleManager() {
			return jPanelRenderingContext.getStyleManager();
		}

		private LayoutProviderFactory getLayoutConstructionManager() {
			return jPanelRenderingContext.getLayoutProviderFactory();
		}
	}

	public static class JPanelRenderingProcess {
		private final JPanelCreationStrategy baseCreationStrategy;
		private final Map<Object, JPanelRenderingProcess> childrenProcesses;
		private final LayoutProvider layoutProvider;

		public JPanelRenderingProcess(JPanelCreationStrategy baseCreationStrategy, Map<Object, JPanelRenderingProcess> childrenProcesses, LayoutProvider layoutProvider) {
			this.baseCreationStrategy = baseCreationStrategy;
			this.childrenProcesses = childrenProcesses;
			this.layoutProvider = layoutProvider;
		}

		public JPanel render() {
			JPanel container = baseCreationStrategy.create();
			if (layoutProvider.isEmpty()) return container;

			container.setLayout(layoutProvider.getLayoutManager(container));

			for (Object specifics : childrenProcesses.keySet()) {
				JPanelRenderingProcess childRenderingProcessFactory = childrenProcesses.get(specifics);
				container.add(childRenderingProcessFactory.render(), specifics);
			}
			return container;
		}
	}
}
