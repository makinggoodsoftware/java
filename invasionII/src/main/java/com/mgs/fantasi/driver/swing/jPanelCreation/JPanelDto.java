package com.mgs.fantasi.driver.swing.jPanelCreation;

import com.mgs.fantasi.driver.swing.SwingUtils;
import com.mgs.fantasi.properties.JPanelDecorator;
import com.mgs.fantasi.properties.UIPropertiesProvider;
import com.mgs.fantasi.properties.data.Padding;
import com.mgs.fantasi.properties.data.polygon.PolygonPointsIterator;
import com.mgs.fantasi.structure.CollocationInfo;
import com.mgs.fantasi.structure.treeAux.WireframeLayoutType;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class JPanelDto {
	private final UIPropertiesProvider uiProperties;
	private final JPanelLayoutTranslator jPanelLayoutTranslator;
	private List<JPanelChild> children = new ArrayList<JPanelChild>();
	private final JPanelFactory jPanelFactory;
	private final PolygonPointsIterator shape;
	private final JPanelDecorator<Padding> paddingDecorator;

	public JPanelDto(UIPropertiesProvider uiProperties, JPanelLayoutTranslator jPanelLayoutTranslator, PolygonPointsIterator shape, JPanelFactory jPanelFactory, JPanelDecorator<Padding> paddingDecorator) {
		this.uiProperties = uiProperties;
		this.jPanelLayoutTranslator = jPanelLayoutTranslator;
		this.jPanelFactory = jPanelFactory;
		this.shape = shape;
		this.paddingDecorator = paddingDecorator;
	}

	public JPanel build(WireframeLayoutType layoutType) {
		JPanel undecorated = jPanelFactory.create(layoutType, uiProperties, jPanelLayoutTranslator, children, shape);
		if (uiProperties.getPadding().isFullyDefined()) {
			return paddingDecorator.decorate(undecorated, uiProperties.getPadding().getValue());
		}
		return undecorated;
	}

	public static Object translate(CollocationInfo specifics, LayoutManager type) {
		if (type instanceof GridBagLayout) {
			return SwingUtils.coordinates(specifics.getCoordinateX(), specifics.getCoordinateY(), specifics.getProportionOfParentWeight(), specifics.getProportionOfParentHeight());
		} else if (type instanceof OverlayLayout) {
			return specifics.getZIndex();
		}
		throw new RuntimeException("Not expected to hit this code point");
	}


	public JPanelDto addChild(JPanelDto child, CollocationInfo collocationInfo, WireframeLayoutType wireframeLayoutType) {
		children.add(new JPanelChild(child, collocationInfo, wireframeLayoutType));
		return this;
	}

	class JPanelChild {
		private final JPanelDto panel;
		private final CollocationInfo collocationInfo;
		private final WireframeLayoutType wireframeLayoutType;

		public JPanelChild(JPanelDto panel, CollocationInfo collocationInfo, WireframeLayoutType wireframeLayoutType) {
			this.panel = panel;
			this.collocationInfo = collocationInfo;
			this.wireframeLayoutType = wireframeLayoutType;
		}


		public JPanelDto getPanel() {
			return panel;
		}

		public CollocationInfo getCollocationInfo() {
			return collocationInfo;
		}

		public WireframeLayoutType getWireframeLayoutType() {
			return wireframeLayoutType;
		}
	}
}
