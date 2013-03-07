package com.mgs.fantasi.driver.swing.jPanelCreation;

import com.mgs.fantasi.structure.CollocationInfo;
import com.mgs.fantasi.structure.treeAux.WireframeLayoutType;

import javax.swing.*;

public interface JPanelBuilder {
	JPanel build(WireframeLayoutType layoutType);

	JPanelBuilder withChild(JPanel child, CollocationInfo collocationInfo);
}
