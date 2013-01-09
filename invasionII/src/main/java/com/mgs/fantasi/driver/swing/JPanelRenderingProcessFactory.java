package com.mgs.fantasi.driver.swing;

import com.mgs.fantasi.driver.RenderingProcess;
import com.mgs.fantasi.driver.RenderingProcessFactory;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelCreationStrategy;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelCreationStrategyFactory;
import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.styles.StyleManager;
import com.mgs.fantasi.styles.UIProfile;
import com.mgs.fantasi.views.View;
import com.mgs.fantasi.wireframe.*;
import com.mgs.fantasi.wireframe.grid.CellContent;
import com.mgs.fantasi.wireframe.grid.CellIterator;
import com.mgs.fantasi.wireframe.layer.LayerIterator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static com.mgs.fantasi.driver.swing.SwingUtils.coordinates;
import static com.mgs.fantasi.driver.swing.ToBeAddedBuilder.ToBeAdded;
import static com.mgs.fantasi.properties.measurements.Fractions.all;

public class JPanelRenderingProcessFactory implements RenderingProcessFactory<JPanel> {
	private final JPanelCreationStrategyFactory jPanelCreationStrategyFactory;
	private final StyleManager styleManager;


	public JPanelRenderingProcessFactory(StyleManager styleManager, JPanelCreationStrategyFactory jPanelCreationStrategyFactory) {
		this.jPanelCreationStrategyFactory = jPanelCreationStrategyFactory;
		this.styleManager = styleManager;
	}

	@Override
	public RenderingProcess<JPanel> newRenderingProcess(View view, final UIProfile uiProfile) {
		Wireframe<View> from = view.buildContent();
		UIProperties uiPropertiesWithStylesApplied = styleManager.applyStyles(view.getUiProperties(), uiProfile.findStylesFor(view));

		JPanelCreationStrategy baseCreationStrategy = jPanelCreationStrategyFactory.forUIProperties(uiPropertiesWithStylesApplied);
		Content content = createContent(uiProfile, from);

		return new JPanelRenderingProcess(baseCreationStrategy, content);
	}

	private Content createContent(UIProfile uiProfile, Wireframe<View> from) {
		List<ToBeAddedBuilder> childObjectsBuiler = findChildObjects(uiProfile, from);
		List<ToBeAdded> childObjects = new ArrayList<ToBeAdded>();
		for (ToBeAddedBuilder<?, JPanel> toBeAddedBuilder : childObjectsBuiler) {
			RenderingProcess<JPanel> jPanelRenderingProcess = newRenderingProcess(toBeAddedBuilder.getView(), uiProfile);
			childObjects.add(toBeAddedBuilder.build(jPanelRenderingProcess));
		}
		return new Content(childObjects, from.getType());
	}

	private List<ToBeAddedBuilder> findChildObjects(final UIProfile uiProfile, Wireframe<View> from) {
		switch (from.getType()) {
			case GRID:
				return processGrid(uiProfile, (GridWireframe<View>) from);
			case SIMPLE:
				return processSimpleWireframe(uiProfile, (RectangleWireframe<View>) from);
			case LAYERS:
				return processLayers(uiProfile, (LayeredWireframe<View>) from);
			default:
				return new ArrayList<ToBeAddedBuilder>();
		}
	}

	private List<ToBeAddedBuilder> processLayers(final UIProfile uiProfile, LayeredWireframe<View> from) {
		final List<ToBeAddedBuilder> toBeAddedBuilderList2 = new ArrayList<ToBeAddedBuilder>();
		LayeredWireframe<View> layers = (LayeredWireframe<View>) from;
		layers.iterateInCrescendo(new LayerIterator<View>() {
			@Override
			public void on(int zIndex, View layer) {
				ToBeAddedBuilder<Integer, JPanel> toBeAddedBuilder = new ToBeAddedBuilder<Integer, JPanel>(zIndex, layer);
				toBeAddedBuilderList2.add(toBeAddedBuilder);
			}
		});
		return toBeAddedBuilderList2;
	}

	private List<ToBeAddedBuilder> processSimpleWireframe(UIProfile uiProfile, RectangleWireframe<View> from) {
		final List<ToBeAddedBuilder> toBeAddedBuilderList1 = new ArrayList<ToBeAddedBuilder>();
		RectangleWireframe<View> rectangle = (RectangleWireframe<View>) from;
		if (rectangle.getContent() != null) {
			ToBeAddedBuilder<GridBagConstraints, JPanel> toBeAddedBuilder = new ToBeAddedBuilder<GridBagConstraints, JPanel>(coordinates(0, 0, all(), all()), rectangle.getContent());
			toBeAddedBuilderList1.add(toBeAddedBuilder);
		}
		return toBeAddedBuilderList1;
	}

	private List<ToBeAddedBuilder> processGrid(final UIProfile uiProfile, GridWireframe<View> grid) {
		final List<ToBeAddedBuilder> toBeAddedBuilderList = new ArrayList<ToBeAddedBuilder>();
		grid.itereateCellsWith(new CellIterator<View>() {
			@Override
			public void on(int x, int y, CellContent<View> cell) {
				ToBeAddedBuilder<GridBagConstraints, JPanel> toBeAddedBuilder = new ToBeAddedBuilder<GridBagConstraints, JPanel>(coordinates(x, y, cell.getWidthSizeRatio(), cell.getHeightSizeRatio()), cell.getContent());
				toBeAddedBuilderList.add(toBeAddedBuilder);
			}
		});
		return toBeAddedBuilderList;
	}

	public static class Content {
		private final List<ToBeAddedBuilder.ToBeAdded> childrenProcesses;
		private final WireframeType wireframeType;

		public Content(List<ToBeAdded> childrenProcesses, WireframeType wireframeType) {
			this.childrenProcesses = childrenProcesses;
			this.wireframeType = wireframeType;
		}

		public List<ToBeAdded> getChildrenProcesses() {
			return childrenProcesses;
		}

		public boolean isEmpty() {
			return childrenProcesses.size() == 0;
		}

		public WireframeType getType() {
			return wireframeType;
		}
	}
}
