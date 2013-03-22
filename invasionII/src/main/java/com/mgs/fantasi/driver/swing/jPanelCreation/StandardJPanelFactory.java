package com.mgs.fantasi.driver.swing.jPanelCreation;

import com.mgs.fantasi.driver.swing.SwingUtils;
import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.structure.treeAux.WireframeLayoutType;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StandardJPanelFactory implements JPanelFactory {
	@Override
	public JPanel create(WireframeLayoutType layoutType, UIProperties uiProperties, JPanelLayoutTranslator jPanelLayoutTranslator1, List<StandardJPanelDto.JPanelChild> children) {
		JPanel jPanel = new JPanel();
		jPanel.setOpaque(false);
		SwingUtils.applyUIProperties(jPanel, uiProperties);
		if (layoutType != WireframeLayoutType.EMPTY) {
			LayoutManager layout = jPanelLayoutTranslator1.translate(layoutType, jPanel);
			jPanel.setLayout(layout);
			for (StandardJPanelDto.JPanelChild child : children) {
				jPanel.add(child.getPanel().build(child.getWireframeLayoutType()), StandardJPanelDto.translate(child.getCollocationInfo(), layout));
			}
		}
		return jPanel;
	}
}
