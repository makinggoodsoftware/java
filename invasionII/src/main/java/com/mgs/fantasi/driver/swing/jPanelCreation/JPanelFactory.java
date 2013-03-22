package com.mgs.fantasi.driver.swing.jPanelCreation;

import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.structure.treeAux.WireframeLayoutType;

import javax.swing.*;
import java.util.List;

public interface JPanelFactory {
	JPanel create(WireframeLayoutType layoutType, UIProperties uiProperties, JPanelLayoutTranslator jPanelLayoutTranslator1, List<StandardJPanelDto.JPanelChild> children);
}
