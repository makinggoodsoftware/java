package com.mgs.fantasi.driver.swing;

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

public class JPanelRenderingProcessFactory implements RenderingProcessFactory<JPanel> {
	private final JPanelCreationStrategyFactory jPanelCreationStrategyFactory;
	private final StyleManager styleManager;
	private final LayoutProviderFactory layoutProviderFactory;


	public JPanelRenderingProcessFactory(LayoutProviderFactory layoutProviderFactory, StyleManager styleManager, JPanelCreationStrategyFactory jPanelCreationStrategyFactory) {
		this.jPanelCreationStrategyFactory = jPanelCreationStrategyFactory;
		this.styleManager = styleManager;
		this.layoutProviderFactory = layoutProviderFactory;
	}

	@Override
	public RenderingProcess<JPanel> newRenderingProcess(View view, final UIProfile uiProfile) {
		UIProperties uiPropertiesWithStylesApplied = styleManager.applyStyles(view.getUiProperties(), uiProfile.findStylesFor(view));
		Wireframe<View> from = view.buildContent();

		JPanelCreationStrategy baseCreationStrategy = jPanelCreationStrategyFactory.forUIProperties(uiPropertiesWithStylesApplied);
		final Map<Object, RenderingProcess<JPanel>> childrenProcesses = new HashMap<Object, RenderingProcess<JPanel>>();

		switch (from.getType()) {
			case GRID:
				GridWireframe<View> grid = (GridWireframe<View>) from;
				grid.itereateCellsWith(new CellIterator<View>() {
					@Override
					public void on(int x, int y, CellContent<View> cell) {
						childrenProcesses.put(coordinates(x, y, cell.getWidthSizeRatio(), cell.getHeightSizeRatio()), newRenderingProcess(cell.getContent(), uiProfile));
					}
				});
				break;
			case SIMPLE:
				RectangleWireframe<View> rectangle = (RectangleWireframe<View>) from;
				if (rectangle.getContent() != null) {
					childrenProcesses.put(coordinates(0, 0, all(), all()), newRenderingProcess(rectangle.getContent(), uiProfile));
				}
				break;
			case LAYERS:
				LayeredWireframe<View> layers = (LayeredWireframe<View>) from;
				layers.iterateInCrescendo(new LayerIterator<View>() {
					@Override
					public void on(int zIndex, View layer) {
						childrenProcesses.put(zIndex, newRenderingProcess(layer, uiProfile));
					}
				});
				break;
			case DELEGATE:
				DelegateWireframe<View> delegate = (DelegateWireframe<View>) from;
				childrenProcesses.put(coordinates(0, 0, all(), all()), newRenderingProcess(delegate.getContent(), uiProfile));
				break;
		}

		LayoutProvider layoutProvider = layoutProviderFactory.translateTypeIntoLayoutProvider(from);
		return new JPanelRenderingProcess(baseCreationStrategy, childrenProcesses, layoutProvider);
	}

}
