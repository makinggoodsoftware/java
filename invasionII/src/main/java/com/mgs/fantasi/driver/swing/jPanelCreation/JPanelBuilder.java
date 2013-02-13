package com.mgs.fantasi.driver.swing.jPanelCreation;

import com.mgs.fantasi.wireframe.CollocationInfo;
import com.mgs.fantasi.wireframe.tree.WireframeLayoutType;

import javax.swing.*;

public interface JPanelBuilder {
	JPanel build(WireframeLayoutType layoutType);

	JPanelBuilder withChild(JPanel child, CollocationInfo collocationInfo);
}
