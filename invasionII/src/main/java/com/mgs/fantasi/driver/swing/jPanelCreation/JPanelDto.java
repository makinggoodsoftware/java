package com.mgs.fantasi.driver.swing.jPanelCreation;

import com.mgs.fantasi.structure.CollocationInfo;
import com.mgs.fantasi.structure.treeAux.WireframeLayoutType;

import javax.swing.*;

public interface JPanelDto {
	JPanel build(WireframeLayoutType layoutType);

	JPanelDto addChild(JPanelDto child, CollocationInfo collocationInfo, WireframeLayoutType wireframeLayoutType);
}
