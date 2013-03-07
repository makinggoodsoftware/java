package com.mgs.fantasi.driver.swing.jPanelCreation;

import com.mgs.fantasi.driver.swing.SwingUtils;
import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.structure.CollocationInfo;
import com.mgs.fantasi.structure.treeAux.WireframeLayoutType;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StandardJPanelBuilder implements JPanelBuilder {
	private final UIProperties uiProperties;
	private final JPanelLayoutTranslator jPanelLayoutTranslator;
	private List<JPanelChild> children = new ArrayList<JPanelChild>();

	public StandardJPanelBuilder(UIProperties uiProperties, JPanelLayoutTranslator jPanelLayoutTranslator) {
		this.uiProperties = uiProperties;
		this.jPanelLayoutTranslator = jPanelLayoutTranslator;
	}

	@Override
	public JPanel build(WireframeLayoutType layoutType) {
		JPanel jPanel = new JPanel();
		jPanel.setOpaque(false);
		SwingUtils.applyUIProperties(jPanel, uiProperties);
		if (layoutType != WireframeLayoutType.EMPTY) {
			LayoutManager layout = jPanelLayoutTranslator.translate(layoutType, jPanel);
			jPanel.setLayout(layout);
			for (JPanelChild child : children) {
				jPanel.add(child.getPanel(), translate(child.getCollocationInfo(), layout));
			}
		}
		return jPanel;
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
	public JPanelBuilder withChild(JPanel child, CollocationInfo collocationInfo) {
		children.add(new JPanelChild(child, collocationInfo));
		return this;
	}

	private class JPanelChild {
		private final JPanel panel;
		private final CollocationInfo collocationInfo;

		public JPanelChild(JPanel panel, CollocationInfo collocationInfo) {
			this.panel = panel;
			this.collocationInfo = collocationInfo;
		}


		public JPanel getPanel() {
			return panel;
		}

		public CollocationInfo getCollocationInfo() {
			return collocationInfo;
		}
	}
}
