package com.mgs.fantasi.driver.swing.jPanelCreation;

import com.mgs.fantasi.driver.swing.SwingUtils;
import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.properties.data.polygon.PolygonPointsIterator;
import com.mgs.fantasi.structure.CollocationInfo;
import com.mgs.fantasi.structure.treeAux.WireframeLayoutType;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StandardJPanelDto implements JPanelDto {
	private final UIProperties uiProperties;
	private final JPanelLayoutTranslator jPanelLayoutTranslator;
	private List<JPanelChild> children = new ArrayList<JPanelChild>();
	private final JPanelFactory jPanelFactory;
	private final PolygonPointsIterator shape;

	public StandardJPanelDto(UIProperties uiProperties, JPanelLayoutTranslator jPanelLayoutTranslator, PolygonPointsIterator shape, JPanelFactory jPanelFactory) {
		this.uiProperties = uiProperties;
		this.jPanelLayoutTranslator = jPanelLayoutTranslator;
		this.jPanelFactory = jPanelFactory;
		this.shape = shape;
	}

	@Override
	public JPanel build(WireframeLayoutType layoutType) {
		return jPanelFactory.create(layoutType, uiProperties, jPanelLayoutTranslator, children, shape);
	}

	public static Object translate(CollocationInfo specifics, LayoutManager type) {
		if (type instanceof GridBagLayout) {
			return SwingUtils.coordinates(specifics.getCoordinateX(), specifics.getCoordinateY(), specifics.getProportionOfParentWeight(), specifics.getProportionOfParentHeight());
		} else if (type instanceof OverlayLayout) {
			return specifics.getZIndex();
		}
		throw new RuntimeException("Not expected to hit this code point");
	}


	@Override
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
