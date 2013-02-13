package com.mgs.fantasi.driver.swing.jPanelCreation;

import com.mgs.fantasi.wireframe.CollocationInfo;
import com.mgs.fantasi.wireframe.tree.WireframeChildrenLayout;

import javax.swing.*;

public interface JPanelBuilder {
	JPanel build(WireframeChildrenLayout childrenLayout);

	JPanelBuilder withChild(JPanel child, CollocationInfo collocationInfo);
}
