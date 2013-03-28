package com.mgs.fantasi.driver.swing.jPanelCreation;

import com.mgs.fantasi.driver.swing.SwingUtils;
import com.mgs.fantasi.properties.UIPropertiesProvider;
import com.mgs.fantasi.properties.data.polygon.PolygonPointsIterator;
import com.mgs.fantasi.structure.CollocationInfo;
import com.mgs.fantasi.structure.treeAux.WireframeLayoutType;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class JPanelDto {
	private final UIPropertiesProvider uiProperties;
	private List<JPanelChild> children = new ArrayList<JPanelChild>();
	private final PolygonPointsIterator shape;
	private final WireframeLayoutType type;

	public JPanelDto(UIPropertiesProvider uiProperties, PolygonPointsIterator shape, WireframeLayoutType type) {
		this.uiProperties = uiProperties;
		this.shape = shape;
		this.type = type;
	}

	public static Object translate(CollocationInfo specifics, LayoutManager type) {
		if (type instanceof GridBagLayout) {
			return SwingUtils.coordinates(specifics.getCoordinateX(), specifics.getCoordinateY(), specifics.getProportionOfParentWeight(), specifics.getProportionOfParentHeight());
		} else if (type instanceof OverlayLayout) {
			return specifics.getZIndex();
		}
		throw new RuntimeException("Not expected to hit this code point");
	}


	public JPanelDto addChild(JPanelDto child, CollocationInfo collocationInfo) {
		children.add(new JPanelChild(child, collocationInfo));
		return this;
	}

	public UIPropertiesProvider getUiProperties() {
		return uiProperties;
	}

	public PolygonPointsIterator getShape() {
		return shape;
	}

	public List<JPanelChild> getChildren() {
		return children;
	}

	public WireframeLayoutType getType() {
		return type;
	}

	public class JPanelChild {
		private final JPanelDto panel;
		private final CollocationInfo collocationInfo;

		public JPanelChild(JPanelDto panel, CollocationInfo collocationInfo) {
			this.panel = panel;
			this.collocationInfo = collocationInfo;
		}


		public JPanelDto getPanel() {
			return panel;
		}

		public CollocationInfo getCollocationInfo() {
			return collocationInfo;
		}

	}
}
